import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, ReactiveFormsModule, Validators } from '@angular/forms';
import { NgFor } from '@angular/common';
import { ErrorComponent } from '../../common/form-error/form-error.component';
import { Dataflow } from '../data-flow.model';
import { DataFlowService } from '../data-flow.service';
import { ConnectorService } from '../../connector/connector.service';
import { Connector } from '../../connector/connector.model';
import { MessageService } from '../../navbar/message-bar/message.service';
import { Observable, Subject } from 'rxjs';

@Component({
  selector: 'app-dataflow-editor',
  imports: [ErrorComponent, ReactiveFormsModule],
  templateUrl: './dataflow-editor.component.html',
  styleUrl: './dataflow-editor.component.css'
})
export class DataflowEditorComponent implements OnInit {

  id: string = '';
  private isOpenSubject: Subject<boolean>;
  isOpen: Observable<boolean>;
  connectors: Connector[] = [];

  constructor(
    private dataFlowService: DataFlowService,
    private connectorService: ConnectorService,
    private messageService: MessageService
  ) { 
    this.isOpenSubject = new Subject();
    this.isOpen = this.isOpenSubject.asObservable();
  }

  ngOnInit(): void {
    // Load connectors for dropdowns
    this.loadConnectors();
  }

  dataflowForm = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(255)]),
    description: new FormControl('', Validators.maxLength(500)),
    enabled: new FormControl(true),
    sourceConnectorId: new FormControl<number | null>(null, [Validators.required]),
    destinationConnectorId: new FormControl<number | null>(null, [Validators.required])
  });

  private loadConnectors() {
    this.connectorService.findAll().subscribe(connectors => {
      this.connectors = connectors;
      
      // Add sample connectors for UI testing if none exist
      if (this.connectors.length === 0) {
        this.connectors = [
          new Connector({
            id: 1,
            name: 'Oracle Production DB',
            description: 'Production Oracle database',
            type: 'ORACLE'
          }),
          new Connector({
            id: 2,
            name: 'Kafka Cluster',
            description: 'Main Kafka cluster',
            type: 'KAFKA'
          }),
          new Connector({
            id: 3,
            name: 'REST API Endpoint',
            description: 'External REST API',
            type: 'HTTP'
          }),
          new Connector({
            id: 4,
            name: 'Analytics DB',
            description: 'Analytics Oracle database',
            type: 'ORACLE'
          })
        ];
      }
    });
  }

  show(id: string) {
    this.isOpenSubject.next(true);
    this.id = id;
    const editor = document.getElementById("dataflow-editor");
    if(editor){
      editor.classList.add('show');
      editor.style.opacity = "1";
      if (id && id !== '0') {
        this.dataFlowService.findById(id).subscribe(dataflow => {
          this.dataflowForm.patchValue({
            name: dataflow.name || '',
            description: dataflow.description || '',
            enabled: dataflow.enabled || false,
            sourceConnectorId: dataflow.sourceConnectorId || null,
            destinationConnectorId: dataflow.destinationConnectorId || null
          });
        });
      }
    }
  }

  onSave() {
    if (this.dataflowForm.valid) {
      let dataflow: Dataflow = new Dataflow(this.dataflowForm.value);
      
      if (!this.id || this.id === '0') {
        this.dataFlowService.create(dataflow).subscribe({
          next: (result) => {
            this.messageService.addMessage(`Dataflow ${result.name} successfully created`, 'success');
            this.onClose();
          },
          error: (error) => {
            this.messageService.addMessage(error, 'danger');
          }
        });
      } else {
        this.dataFlowService.update(this.id, dataflow).subscribe({
          next: (result) => {
            this.messageService.addMessage(`Dataflow ${result.name} successfully updated`, 'success');
            this.onClose();
          },
          error: (error) => {
            this.messageService.addMessage(error, 'danger');
          }
        });
      }
    }
  }

  onClose() {
    this.dataflowForm.reset();
    this.dataflowForm.patchValue({ enabled: true }); // Reset enabled to default true
    const editor = document.getElementById("dataflow-editor");
    if(editor){
      editor.classList.remove('show');
      editor.style.opacity = "0";
      this.isOpenSubject.next(false);
    }
  }

  onTest() {
    // TODO: Implement dataflow testing functionality
    this.messageService.addMessage('Dataflow test functionality not yet implemented', 'info');
  }

  getConnectorName(connectorId: number | null): string {
    if (!connectorId) return '';
    const connector = this.connectors.find(c => c.id === connectorId);
    return connector ? `${connector.name} (${connector.type})` : '';
  }
}
