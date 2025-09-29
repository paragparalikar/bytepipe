import { Component } from '@angular/core';
import { FormGroup, FormControl, ReactiveFormsModule, Validators } from '@angular/forms';
import { ErrorComponent } from '../../../common/form-error/form-error.component';
import { Connector } from '../../connector.model';
import { ConnectorService } from '../../connector.service';
import { MessageService } from '../../../navbar/message-bar/message.service';
import { MessageType } from '../../../navbar/message-bar/message.interface'
import { OracleConnector } from '../../connector-oracle.model';


@Component({
  selector: 'app-oracle-connector-editor',
  imports: [ErrorComponent, ReactiveFormsModule],
  templateUrl: './oracle-connector-editor.component.html',
  styleUrl: './oracle-connector-editor.component.css'
})
export class OracleConnectorEditorComponent {

  id: number = 0;

  constructor(
    private connectorService: ConnectorService,
    private messageService: MessageService
  ) { }

  oracleConnectorForm = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(255)]),
    description: new FormControl('', Validators.maxLength(255)),
    url: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(255)]),
    username: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(255)]),
    password: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(255)])
  });

  show(id: number) {
    this.id = id;
    const editor = document.getElementById("oracle-connector-editor");
    editor?.classList.remove('hidden');
    if (0 < id) {
      this.connectorService.findById(id).subscribe(connector => {
        this.oracleConnectorForm.patchValue(connector);
      });
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
    editor?.classList.add('hidden');
  }

  onTest() {

  }

}
function next(value: Connector): void {
  throw new Error('Function not implemented.');
}

