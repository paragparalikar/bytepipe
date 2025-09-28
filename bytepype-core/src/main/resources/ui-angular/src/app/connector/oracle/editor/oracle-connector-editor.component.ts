import { Component, Input, numberAttribute } from '@angular/core';
import {FormGroup, FormControl, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-oracle-connector-editor',
  imports: [ReactiveFormsModule],
  templateUrl: './oracle-connector-editor.component.html',
  styleUrl: './oracle-connector-editor.component.css'
})
export class OracleConnectorEditorComponent {

  @Input({transform: numberAttribute}) id = 0;
  oracleConnectorForm = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(255)]),
    description: new FormControl('', Validators.maxLength(255)),
    url: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(255)]),
    username: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(255)]),
    password: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(255)])
  });

  onSave() {
    
  }

}
