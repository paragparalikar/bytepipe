import { Component, Input, numberAttribute, Signal, signal } from '@angular/core';
import {FormGroup, FormControl, ReactiveFormsModule, Validators } from '@angular/forms';
import { ErrorComponent } from '../../../common/form-error/form-error.component';
import { ConnectConfig } from 'rxjs';
import { Connector } from '../../connector.model';
import { ConnectorType } from '../../connector-type.enum';
import { ConnectorService } from '../../connector.service';


@Component({
  selector: 'app-oracle-connector-editor',
  imports: [ErrorComponent, ReactiveFormsModule],
  templateUrl: './oracle-connector-editor.component.html',
  styleUrl: './oracle-connector-editor.component.css'
})
export class OracleConnectorEditorComponent {

  id: number = 0;

  constructor(private connectorService: ConnectorService){}

  oracleConnectorForm = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(255)]),
    description: new FormControl('', Validators.maxLength(255)),
    url: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(255)]),
    username: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(255)]),
    password: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(255)])
  });

  show(id: number){
    this.id = id;
    const editor = document.getElementById("oracle-connector-editor");
    editor?.classList.remove('hidden');
  }

  onSave() {
    let connector: Connector = new Connector(this.oracleConnectorForm.value);
    connector.type = "ORACLE";
    this.connectorService.create(connector).subscribe(result => {
      console.log(result);
      this.oracleConnectorForm.reset();
      this.onClose();
    });
  }

  onClose(){
    const editor = document.getElementById("oracle-connector-editor");
    editor?.classList.add('hidden');
  }

  onTest(){
    
  }

}
