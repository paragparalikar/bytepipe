import { Component } from '@angular/core';
import { NgClass } from '@angular/common';
import { MessageService } from './message.service';

@Component({
  selector: 'app-message-bar',
  standalone: true,
  imports: [NgClass],
  templateUrl: './message-bar.component.html'
})
export class MessageBarComponent {

  constructor(public messageService: MessageService) {}

  getClassForMessageType(type: 'success' | 'warning' | 'info' | 'danger'): string {
    switch (type) {
      case 'danger' :
        return 'bg-red-100 text-red-800 border-red-400';
      case 'success':
        return 'bg-green-100 text-green-800 border-green-400';
      case 'warning':
        return 'bg-yellow-100 text-yellow-800 border-yellow-400';
      case 'info':
        return 'bg-blue-100 text-blue-800 border-blue-400';
      default:
        return '';
    }
  }

  onClose(id: number): void {
    this.messageService.removeMessage(id);
  }
}