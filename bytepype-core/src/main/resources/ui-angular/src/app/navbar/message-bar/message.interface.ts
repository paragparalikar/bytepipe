export type MessageType = 'success' | 'warning' | 'info' | 'danger';

export interface Message {
  text: string;
  type: MessageType;
  id: number;
}