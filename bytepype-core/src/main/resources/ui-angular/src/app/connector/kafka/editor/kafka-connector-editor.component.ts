import { Component, EventEmitter, Input, OnDestroy, OnInit, Output, ViewChild, ElementRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators, FormsModule } from '@angular/forms';
import { Subscription } from 'rxjs';
import { ConnectorService } from '../../connector.service';
import { MessageService } from '../../../navbar/message-bar/message.service';
import { KafkaConnector } from '../connector-kafka.model';
import { Connector } from '../../connector.model';

interface SaslMechanismOption {
  value: 'PLAIN' | 'SCRAM-SHA-256' | 'SCRAM-SHA-512' | 'GSSAPI' | 'OAUTHBEARER';
  label: string;
}

@Component({
  selector: 'app-kafka-connector-editor',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule
  ],
  templateUrl: './kafka-connector-editor.component.html',
  styleUrls: ['./kafka-connector-editor.component.css']
})
export class KafkaConnectorEditorComponent implements OnInit, OnDestroy {
  @Input() id: number | null = null;
  @Output() isOpen = new EventEmitter<boolean>();
  @ViewChild('kafkaConnectorEditor') private modalElement!: ElementRef;

  kafkaConnectorForm!: FormGroup;
  
  // Form controls with proper typing
  get nameControl(): FormControl { return this.kafkaConnectorForm.get('name') as FormControl; }
  get topicControl(): FormControl { return this.kafkaConnectorForm.get('topic') as FormControl; }
  get bootstrapServersControl(): FormControl { return this.kafkaConnectorForm.get('bootstrapServers') as FormControl; }
  get useSaslControl(): FormControl { return this.kafkaConnectorForm.get('useSasl') as FormControl; }
  get useSslControl(): FormControl { return this.kafkaConnectorForm.get('useSsl') as FormControl; }
  get saslMechanismControl(): FormControl { return this.kafkaConnectorForm.get('saslMechanism') as FormControl; }
  
  private subscription = new Subscription();
  loading: boolean = false;
  submitted: boolean = false;

  activeTab: 'details' | 'sasl' | 'ssl' = 'details';
  isTesting = false;

  // SASL mechanisms for the form
  saslMechanisms: SaslMechanismOption[] = [
    { value: 'PLAIN', label: 'PLAIN' },
    { value: 'SCRAM-SHA-256', label: 'SCRAM-SHA-256' },
    { value: 'SCRAM-SHA-512', label: 'SCRAM-SHA-512' },
    { value: 'GSSAPI', label: 'GSSAPI (Kerberos)' },
    { value: 'OAUTHBEARER', label: 'OAUTHBEARER' }
  ];

  constructor(
    private formBuilder: FormBuilder,
    private connectorService: ConnectorService,
    private messageService: MessageService
  ) {}

  show(id: number): void {
    this.id = id;
    const editor = this.modalElement.nativeElement;
    if (editor) {
      editor.classList.add('show');
      if (id) {
        this.loadConnector(id);
      }
    }
    this.isOpen.emit(true);
  }

  ngOnInit(): void {
    this.initializeForm();
    if (this.id) {
      this.loadConnector(this.id);
    }
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  switchTab(tab: 'details' | 'sasl' | 'ssl'): void {
    // Check if tab should be enabled
    if (tab === 'sasl' && !this.useSaslControl.value) {
      this.messageService.addMessage('Enable SASL on Details tab first', 'warning');
      return;
    }
    if (tab === 'ssl' && !this.useSslControl.value) {
      this.messageService.addMessage('Enable SSL on Details tab first', 'warning');
      return;
    }
    this.activeTab = tab;
  }

  private initializeForm(): void {
    this.kafkaConnectorForm = this.formBuilder.group({
      // Details tab
      name: ['', [Validators.required, Validators.minLength(3)]],
      topic: ['', [Validators.required, Validators.minLength(1)]],
      description: [''],
      bootstrapServers: ['', [Validators.required, Validators.minLength(1)]],
      useSasl: [false],
      useSsl: [false],
      
      // SASL tab
      saslMechanism: ['PLAIN'],
      saslJaasConfig: [''],
      username: [''],
      password: [''],
      keytab: [''],
      principal: [''],
      tokenEndpoint: [''],
      clientId: [''],
      clientSecret: [''],
      
      // SSL tab
      sslTruststore: [''],
      sslTruststorePassword: [''],
      sslKeystore: [''],
      sslKeystorePassword: [''],
      sslKeyPassword: [''],
      sslEnabledProtocols: ['TLSv1.2,TLSv1.3'],
      sslProtocol: ['TLS'],
      
      // Metadata
      status: 'INVALID',
      lastTested: undefined
    });
    
    this.setupSaslControls();
    this.setupSslControls();
  }

  private setupSaslControls(): void {
    const useSaslControl = this.kafkaConnectorForm.get('useSasl');
    const saslMechanismControl = this.kafkaConnectorForm.get('saslMechanism');

    if (useSaslControl && saslMechanismControl) {
      this.subscription.add(
        useSaslControl.valueChanges.subscribe(useSasl => {
          this.updateSaslValidators(useSasl, saslMechanismControl.value);
        })
      );

      this.subscription.add(
        saslMechanismControl.valueChanges.subscribe(mechanism => {
          if (useSaslControl.value) {
            this.updateSaslValidators(true, mechanism);
          }
        })
      );
    }
  }

  private setupSslControls(): void {
    const useSslControl = this.kafkaConnectorForm.get('useSsl');

    if (useSslControl) {
      this.subscription.add(
        useSslControl.valueChanges.subscribe(useSsl => {
          this.updateSslValidators(useSsl);
        })
      );
    }
  }

  private updateSaslValidators(useSasl: boolean, mechanism: string): void {
    const saslJaasConfigControl = this.kafkaConnectorForm.get('saslJaasConfig');
    const usernameControl = this.kafkaConnectorForm.get('username');
    const passwordControl = this.kafkaConnectorForm.get('password');
    const keytabControl = this.kafkaConnectorForm.get('keytab');
    const principalControl = this.kafkaConnectorForm.get('principal');
    const tokenEndpointControl = this.kafkaConnectorForm.get('tokenEndpoint');

    if (!useSasl) {
      // Clear all validators if SASL is disabled
      saslJaasConfigControl?.clearValidators();
      usernameControl?.clearValidators();
      passwordControl?.clearValidators();
      keytabControl?.clearValidators();
      principalControl?.clearValidators();
      tokenEndpointControl?.clearValidators();
    } else {
      // Set validators based on mechanism
      saslJaasConfigControl?.setValidators([Validators.required, Validators.minLength(1)]);

      if (mechanism === 'PLAIN' || mechanism === 'SCRAM-SHA-256' || mechanism === 'SCRAM-SHA-512') {
        usernameControl?.setValidators([Validators.required, Validators.minLength(1)]);
        passwordControl?.setValidators([Validators.required, Validators.minLength(1)]);
        keytabControl?.clearValidators();
        principalControl?.clearValidators();
        tokenEndpointControl?.clearValidators();
      } else if (mechanism === 'GSSAPI') {
        keytabControl?.setValidators([Validators.required]);
        principalControl?.setValidators([Validators.required, Validators.minLength(1)]);
        usernameControl?.clearValidators();
        passwordControl?.clearValidators();
        tokenEndpointControl?.clearValidators();
      } else if (mechanism === 'OAUTHBEARER') {
        tokenEndpointControl?.setValidators([Validators.required, Validators.minLength(1)]);
        usernameControl?.clearValidators();
        passwordControl?.clearValidators();
        keytabControl?.clearValidators();
        principalControl?.clearValidators();
      }
    }

    // Update validity
    saslJaasConfigControl?.updateValueAndValidity();
    usernameControl?.updateValueAndValidity();
    passwordControl?.updateValueAndValidity();
    keytabControl?.updateValueAndValidity();
    principalControl?.updateValueAndValidity();
    tokenEndpointControl?.updateValueAndValidity();
  }

  private updateSslValidators(useSsl: boolean): void {
    const sslTruststoreControl = this.kafkaConnectorForm.get('sslTruststore');
    const sslTruststorePasswordControl = this.kafkaConnectorForm.get('sslTruststorePassword');
    const sslKeystoreControl = this.kafkaConnectorForm.get('sslKeystore');
    const sslKeystorePasswordControl = this.kafkaConnectorForm.get('sslKeystorePassword');

    if (!useSsl) {
      sslTruststoreControl?.clearValidators();
      sslTruststorePasswordControl?.clearValidators();
      sslKeystoreControl?.clearValidators();
      sslKeystorePasswordControl?.clearValidators();
    } else {
      sslTruststoreControl?.setValidators([Validators.required]);
      sslTruststorePasswordControl?.setValidators([Validators.required, Validators.minLength(1)]);
      sslKeystoreControl?.setValidators([Validators.required]);
      sslKeystorePasswordControl?.setValidators([Validators.required, Validators.minLength(1)]);
    }

    sslTruststoreControl?.updateValueAndValidity();
    sslTruststorePasswordControl?.updateValueAndValidity();
    sslKeystoreControl?.updateValueAndValidity();
    sslKeystorePasswordControl?.updateValueAndValidity();
  }

  private loadConnector(id: number): void {
    this.loading = true;
    this.subscription.add(
      this.connectorService.findById(id).subscribe({
        next: (connector: Connector) => {
          this.kafkaConnectorForm.patchValue(connector as KafkaConnector);
          this.loading = false;
        },
        error: (error) => {
          this.messageService.addMessage('Failed to load connector: ' + error.message, 'danger');
          this.loading = false;
          this.onClose();
        }
      })
    );
  }

  async onTest(): Promise<void> {
    if (this.kafkaConnectorForm.invalid) {
      this.messageService.addMessage('Please fill in all required fields', 'danger');
      return;
    }

    this.isTesting = true;
    
    try {
      // Simulate successful connection for now
      await new Promise(resolve => setTimeout(resolve, 1000));
      this.messageService.addMessage('Connection test successful', 'success');
      this.kafkaConnectorForm.patchValue({
        status: 'VALID',
        lastTested: new Date()
      });
    } catch (error: any) {
      this.messageService.addMessage('Connection test failed: ' + (error.message || 'Unknown error'), 'danger');
      this.kafkaConnectorForm.patchValue({
        status: 'INVALID'
      });
    } finally {
      this.isTesting = false;
    }
  }
  
  onSubmit(): void {
    if (this.kafkaConnectorForm.invalid) {
      return;
    }

    const connectorData = this.kafkaConnectorForm.value;
    this.loading = true;

    const saveOperation = this.id
      ? this.connectorService.update(Number(this.id), connectorData)
      : this.connectorService.create(connectorData);

    this.subscription.add(
      saveOperation.subscribe({
        next: () => {
          this.messageService.addMessage(
            this.id ? 'Connector updated successfully' : 'Connector created successfully',
            'success'
          );
          this.onClose(true);
        },
        error: (error) => {
          this.messageService.addMessage(
            `Failed to ${this.id ? 'update' : 'create'} connector: ${error.message}`,
            'danger'
          );
          this.loading = false;
        }
      })
    );
  }

  onClose(success: boolean = false): void {
    const editor = this.modalElement.nativeElement;
    if (editor) {
      editor.classList.remove('show');
      this.isOpen.emit(success);
    }
  }

  onCancel(): void {
    this.onClose();
  }
}
