import { Component } from '@angular/core';
import { FormGroup, FormControl, ReactiveFormsModule, Validators } from '@angular/forms';
import { ErrorComponent } from '../../../common/form-error/form-error.component';
import { Connector } from '../../connector.model';
import { ConnectorService } from '../../connector.service';
import { MessageService } from '../../../navbar/message-bar/message.service';
import { Observable, Subject } from 'rxjs';

@Component({
  selector: 'app-oracle-connector-editor',
  imports: [ErrorComponent, ReactiveFormsModule],
  templateUrl: './oracle-connector-editor.component.html',
  styleUrl: './oracle-connector-editor.component.css',
  
})
export class OracleConnectorEditorComponent {

  id: number = 0;
  private isOpenSubject: Subject<boolean>;
  isOpen: Observable<boolean>;

  constructor(
    private connectorService: ConnectorService,
    private messageService: MessageService
  ) { 
    this.isOpenSubject = new Subject();
    this.isOpen = this.isOpenSubject.asObservable();
  }

  oracleConnectorForm = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(255)]),
    description: new FormControl('', Validators.maxLength(255)),
    url: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(255)]),
    username: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(255)]),
    password: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(255)])
  });

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

  }

}
function next(value: Connector): void {
  throw new Error('Function not implemented.');
}

