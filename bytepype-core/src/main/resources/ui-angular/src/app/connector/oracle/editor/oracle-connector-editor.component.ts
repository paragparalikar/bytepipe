import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ErrorComponent } from '../../../common/form-error/form-error.component';
import { Connector } from '../../connector.model';
import { ConnectorService } from '../../connector.service';
import { MessageService } from '../../../navbar/message-bar/message.service';
import { Observable, Subject } from 'rxjs';

interface AuthTypeOption {
  value: 'BASIC_PASSWORD' | 'KERBEROS' | 'TLS_SSL_PKI' | 'PROXY';
  label: string;
  icon: string;
  description: string;
}

@Component({
  selector: 'app-oracle-connector-editor',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './oracle-connector-editor.component.html',
  styleUrl: './oracle-connector-editor.component.css',
  
})
export class OracleConnectorEditorComponent {

  id: number = 0;
  private isOpenSubject: Subject<boolean>;
  isOpen: Observable<boolean>;
  
  // Tab management
  activeTab: 'basic' | 'credentials' = 'basic';
  
  // Form controls with proper typing
  get nameControl(): FormControl { return this.oracleConnectorForm.get('name') as FormControl; }
  get authTypeControl(): FormControl { return this.oracleConnectorForm.get('authType') as FormControl; }
  
  // Auth types for the form
  authTypes: AuthTypeOption[] = [
    { value: 'BASIC_PASSWORD', label: 'Basic Password Authentication', icon: 'fa-key', description: 'Standard database authentication using username and password' },
    { value: 'KERBEROS', label: 'Kerberos Authentication', icon: 'fa-ticket-alt', description: 'Network-based SSO using Kerberos tickets' },
    { value: 'TLS_SSL_PKI', label: 'TLS/SSL with Certificate (PKI)', icon: 'fa-certificate', description: 'Strong authentication using X.509 certificates' },
    { value: 'PROXY', label: 'Proxy Authentication', icon: 'fa-user-shield', description: 'Middle-tier application connecting on behalf of client' }
  ];

  oracleConnectorForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private connectorService: ConnectorService,
    private messageService: MessageService
  ) { 
    this.isOpenSubject = new Subject();
    this.isOpen = this.isOpenSubject.asObservable();
    this.initializeForm();
  }
  
  setActiveTab(tab: 'basic' | 'credentials'): void {
    this.activeTab = tab;
  }

  private initializeForm(): void {
    this.oracleConnectorForm = this.formBuilder.group({
      // Basic Information
      name: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(255)]],
      description: ['', Validators.maxLength(255)],
      url: ['', [Validators.required, Validators.minLength(1)]],
      authType: ['BASIC_PASSWORD'],
      
      // Basic Password Authentication
      username: [''],
      password: [''],
      sqlnetAllowedLogonVersionClient: [''],
      
      // Kerberos Authentication
      kerberosPrincipal: [''],
      keytabFile: [null],
      kerberosConfigFile: [null],
      realm: [''],
      sqlnetAuthenticationServices: [''],
      
      // TLS/SSL PKI Authentication
      distinguishedName: [''],
      walletFile: [null],
      walletPassword: [''],
      caCertificateFile: [null],
      sslVersion: ['TLSv1.2'],
      sslClientAuthentication: [true],
      
      // Proxy Authentication
      proxyUsername: [''],
      proxyPassword: [''],
      clientUsername: [''],
      clientPassword: [''],
      roles: [''],
      certificateFile: [null]
    });
    
    this.setupAuthTypeControls();
  }

  show(id: number) {
    this.isOpenSubject.next(true);
    this.id = id;
    const editor = document.getElementById("oracle-connector-editor");
    if(editor){
      editor.classList.add('show');
      editor.style.opacity = "1";
      if (0 < id) {
        this.connectorService.findById(id).subscribe(connector => {
          this.oracleConnectorForm.patchValue(connector);
        });
      } else {
        // Reset form with default values for new connector
        this.oracleConnectorForm.patchValue({
          authType: 'BASIC_PASSWORD'
        });
      }
    }
  }

  onSave() {
    let connector: Connector = new Connector(this.oracleConnectorForm.value);
    connector.type = "ORACLE";
    if (0 === this.id) {
      this.connectorService.create(connector).subscribe({
        next: (result) => {
          this.messageService.addMessage(`Oracle Connector ${result.name} successfuly created`, 'success');
          this.onClose();
        },
        error: (error) => {
          this.messageService.addMessage(error, 'danger');
        }
      });
    } else {
      this.connectorService.update(this.id, connector).subscribe({
        next: (result) => {
          this.messageService.addMessage(`Oracle Connector ${result.name} successfuly updated`, 'success');
          this.onClose();
        },
        error: (error) => {
          this.messageService.addMessage(error, 'danger');
        }
      });
    }
  }

  onClose() {
    this.oracleConnectorForm.reset();
    const editor = document.getElementById("oracle-connector-editor");
    if(editor){
      editor.classList.remove('show');
      editor.style.opacity = "0";
      this.isOpenSubject.next(false);
    }
  }

  onTest() {
    if (this.oracleConnectorForm.invalid) {
      this.messageService.addMessage('Please fill in all required fields', 'danger');
      return;
    }
    this.messageService.addMessage('Test connection functionality to be implemented', 'info');
  }
  
  private setupAuthTypeControls(): void {
    const authTypeControl = this.oracleConnectorForm.get('authType');

    if (authTypeControl) {
      authTypeControl.valueChanges.subscribe(authType => {
        this.updateAuthValidators(authType);
      });
      // Initialize validators for the default auth type
      this.updateAuthValidators(authTypeControl.value);
    }
  }
  
  private updateAuthValidators(authType: string): void {
    // Clear all validators first
    const fieldsToUpdate = [
      'username', 'password', 'sqlnetAllowedLogonVersionClient',
      'kerberosPrincipal', 'keytabFile', 'kerberosConfigFile', 'realm', 'sqlnetAuthenticationServices',
      'distinguishedName', 'walletFile', 'walletPassword', 'caCertificateFile', 'sslVersion',
      'proxyUsername', 'proxyPassword', 'clientUsername', 'clientPassword', 'roles', 'certificateFile'
    ];
    
    fieldsToUpdate.forEach(field => {
      const control = this.oracleConnectorForm.get(field);
      if (control) {
        control.clearValidators();
        control.updateValueAndValidity();
      }
    });
    
    // Set validators based on auth type
    switch (authType) {
      case 'BASIC_PASSWORD':
        this.setRequiredValidator('username');
        this.setRequiredValidator('password');
        break;
        
      case 'KERBEROS':
        this.setRequiredValidator('kerberosPrincipal');
        this.setRequiredValidator('keytabFile');
        this.setRequiredValidator('sqlnetAuthenticationServices');
        break;
        
      case 'TLS_SSL_PKI':
        this.setRequiredValidator('distinguishedName');
        this.setRequiredValidator('walletFile');
        this.setRequiredValidator('sqlnetAuthenticationServices');
        break;
        
      case 'PROXY':
        this.setRequiredValidator('proxyUsername');
        this.setRequiredValidator('clientUsername');
        break;
    }
  }
  
  private setRequiredValidator(fieldName: string): void {
    const control = this.oracleConnectorForm.get(fieldName);
    if (control) {
      control.setValidators([Validators.required]);
      control.updateValueAndValidity();
    }
  }
  
  onFileChange(event: Event, fieldName: string): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      this.oracleConnectorForm.patchValue({
        [fieldName]: file
      });
    }
  }

}

