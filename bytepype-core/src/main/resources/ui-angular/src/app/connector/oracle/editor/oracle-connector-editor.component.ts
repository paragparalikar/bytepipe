import { Component, Input, numberAttribute, Signal, signal } from '@angular/core';
import {FormGroup, FormControl, ReactiveFormsModule, Validators } from '@angular/forms';
import { ErrorComponent } from '../../../common/form-error/form-error.component';


@Component({
  selector: 'app-oracle-connector-editor',
  imports: [ErrorComponent, ReactiveFormsModule],
  templateUrl: './oracle-connector-editor.component.html',
  styleUrl: './oracle-connector-editor.component.css'
})
export class OracleConnectorEditorComponent {

  id: number = 0;

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
    
  }

  onClose(){
    const editor = document.getElementById("oracle-connector-editor");
    editor?.classList.add('hidden');
  }

}
