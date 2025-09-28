import { Component, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
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

  edit(id?: number|null){
    this.id = id;
    const editor = document.getElementById('oracle-connector-editor');
    editor?.classList.toggle('hidden');
  }

}
