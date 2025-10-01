import { AfterViewInit, Component, CUSTOM_ELEMENTS_SCHEMA, effect, OnInit, ViewChild } from '@angular/core';
import { OracleConnectorEditorComponent } from './oracle/editor/oracle-connector-editor.component';
import { HttpConnectorEditorComponent } from './http/editor/http-connector-editor.component';
import { KafkaConnectorEditorComponent } from './kafka/editor/kafka-connector-editor.component';
import { ConnectorService } from './connector.service';
import { Connector } from './connector.model';
import { NgFor } from '@angular/common';
import { ConnectorTypeComponent } from './connector-type/connector-type.component';
import { CreatedByComponent } from "../user/created-by/created-by.component";
import { DeleteConfirmComponent } from './delete-confirm/delete-confirm.component';

@Component({
  selector: 'app-connector',
  imports: [
    NgFor, 
    OracleConnectorEditorComponent, 
    HttpConnectorEditorComponent,
    KafkaConnectorEditorComponent,
    DeleteConfirmComponent, 
    ConnectorTypeComponent, 
    CreatedByComponent
  ],
  templateUrl: './connector.component.html',
  styleUrl: './connector.component.css',
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ConnectorComponent implements OnInit, AfterViewInit  {

  connectors: Connector[] = [];
  @ViewChild('connectorDeleteConfirm') connectorDeleteConfirm!: DeleteConfirmComponent;
  @ViewChild('oracleConnectorEditor') oracleConnectorEditor!: OracleConnectorEditorComponent;
  @ViewChild('httpConnectorEditor') httpConnectorEditor!: HttpConnectorEditorComponent;
  @ViewChild('kafkaConnectorEditor') kafkaConnectorEditor!: KafkaConnectorEditorComponent;

  constructor(private connectorService: ConnectorService){}

  ngOnInit(): void {
    this.refresh();
  }

  ngAfterViewInit(): void {
    this.oracleConnectorEditor.isOpen.subscribe(value => {
      if(!value) this.refresh();
    });
    this.httpConnectorEditor.isOpen.subscribe(value => {
      if(!value) this.refresh();
    });
    this.kafkaConnectorEditor.isOpen.subscribe(value => {
      if(!value) this.refresh();
    });
    this.connectorDeleteConfirm.isOpen.subscribe(value => {
      if(!value) this.refresh();
    });
  }

  private refresh(){
    this.connectorService.findAll().subscribe(connectors => {
      this.connectors = connectors;
    });
  }

  onCreateConnector(){
    const menu = document.getElementById('dropdownMenu');
    menu?.classList.toggle('hidden');
  }

  onCreateOracleConnector(e: Event){
    e.preventDefault();
    this.onCreateConnector();
    this.oracleConnectorEditor.show(0);
  }

  onCreateHttpConnector(e: Event){
    e.preventDefault();
    this.onCreateConnector();
    this.httpConnectorEditor.show(0);
  }

  onCreateKafkaConnector(e: Event){
    e.preventDefault();
    this.onCreateConnector();
    this.kafkaConnectorEditor.show(0);
  }

  onEdit(connector: Connector){
    if (connector.type === 'ORACLE') {
      this.oracleConnectorEditor.show(connector.id!);
    } else if (connector.type === 'HTTP') {
      this.httpConnectorEditor.show(connector.id!);
    } else if (connector.type === 'KAFKA') {
      this.kafkaConnectorEditor.show(connector.id!);
    }
  }

  onDelete(connector: Connector){
    this.connectorDeleteConfirm.show(connector.id!, connector.name!);
  }

}
