import { Injectable, signal } from '@angular/core';
import { Message, MessageType } from './message.interface';

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  private _messageIdCounter = 0;
  private readonly _messages = signal<Message[]>([]);
  public readonly messages = this._messages.asReadonly();

  // Displays a new message
  addMessage(text: string, type: MessageType): void {
    const newMessage: Message = { text, type, id: this._messageIdCounter++ };
    this._messages.update(currentMessages => [...currentMessages, newMessage]);

    // Automatically remove message after a duration
    setTimeout(() => this.removeMessage(newMessage.id), 5000);
  }

  // Removes a message by its ID
  removeMessage(id: number): void {
    this._messages.update(currentMessages => currentMessages.filter(msg => msg.id !== id));
  }
}