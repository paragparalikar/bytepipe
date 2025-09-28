import { Component, CUSTOM_ELEMENTS_SCHEMA, ViewChild } from '@angular/core';
import { RouterLink } from "@angular/router";
import { OracleConnectorEditorComponent } from './oracle/editor/oracle-connector-editor.component';

@Component({
  selector: 'app-connector',
  imports: [OracleConnectorEditorComponent],
  templateUrl: './connector.component.html',
  styleUrl: './connector.component.css',
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ConnectorComponent {

  id?: number|null;
  @ViewChild('oracleConnectorEditor') oracleConnectorEditor!: OracleConnectorEditorComponent;

  onCreate(){
    this.oracleConnectorEditor.show(0);
  }

}
