import { Component, signal } from '@angular/core';
import { ConnectorService } from '../connector.service';
import { MessageService } from '../../navbar/message-bar/message.service';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-delete-confirm',
  imports: [],
  templateUrl: './delete-confirm.component.html',
  styleUrl: './delete-confirm.component.css'
})
export class DeleteConfirmComponent {

  id?: number;
  name?: string;
  isOpen: Subject<boolean> = new Subject();

  constructor(
    private messageService: MessageService,
    private connectorService: ConnectorService){}

  show(id: number, name: string){
    this.id = id;
    this.name = name;
    this.isOpen.next(true);
    const editor = document.getElementById("connector-delete-confirm");
    if(editor){
      editor.classList.add('show');
      editor.style.opacity = "1";
    }
  }

  onClose(){
    const editor = document.getElementById("connector-delete-confirm");
    if(editor){
      editor.classList.remove('show');
      editor.style.opacity = "0";
    }
    this.isOpen.next(false);
  }

  onConfirm(){
    this.connectorService.deleteById(this.id!).subscribe({
      next: (result) => {
        this.messageService.addMessage(`Oracle Connector ${this.name} successfuly deleted`, 'success');
        this.onClose();
      },
      error: (error) => {
        this.messageService.addMessage(error, 'danger');
      }
    });
  }


}
