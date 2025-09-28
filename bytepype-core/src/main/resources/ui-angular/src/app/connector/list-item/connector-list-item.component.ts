import { Component, Input } from '@angular/core';
import { Connector } from '../connector.model';

@Component({
  selector: 'app-connector-list-item',
  imports: [],
  templateUrl: './connector-list-item.component.html',
  styleUrl: './connector-list-item.component.css'
})
export class ConnectorListItemComponent {

  @Input() connector?: Connector;

  

}
