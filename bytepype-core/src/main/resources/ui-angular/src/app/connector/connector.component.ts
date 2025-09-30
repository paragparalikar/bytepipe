import { AfterViewInit, Component, CUSTOM_ELEMENTS_SCHEMA, OnInit, ViewChild } from '@angular/core';
import { OracleConnectorEditorComponent } from './oracle/editor/oracle-connector-editor.component';
import { ConnectorService } from './connector.service';
import { Connector } from './connector.model';
import { NgFor } from '@angular/common';
import { ConnectorTypeComponent } from './connector-type/connector-type.component';
import { CreatedByComponent } from "../user/created-by/created-by.component";

@Component({
  selector: 'app-connector',
  imports: [NgFor, OracleConnectorEditorComponent, ConnectorTypeComponent, CreatedByComponent],
  templateUrl: './connector.component.html',
  styleUrl: './connector.component.css',
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ConnectorComponent implements OnInit, AfterViewInit  {

  connectors: Connector[] = [];
  @ViewChild('oracleConnectorEditor') oracleConnectorEditor!: OracleConnectorEditorComponent;

  constructor(private connectorService: ConnectorService){}

  ngOnInit(): void {
    this.refresh();
  }

  ngAfterViewInit(): void {
    this.oracleConnectorEditor.isOpen.subscribe(value => {
      if(!value) this.refresh();
    });
  }

  private refresh(){
    this.connectorService.findAll().subscribe(connectors => {
      this.connectors = connectors;
    });
  }

  onCreate(){
    this.oracleConnectorEditor.show(0);
  }

  onEdit(id: number){
    this.oracleConnectorEditor.show(id);
  }

}
