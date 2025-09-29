import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-connector-type',
  imports: [],
  templateUrl: './connector-type.component.html',
  styleUrl: './connector-type.component.css'
})
export class ConnectorTypeComponent implements OnChanges {

  @Input({ required: true }) type?: string | null;
  iconPath?: string;
  text?: string;

  ngOnChanges(changes: SimpleChanges): void {
    if (this.type) {
      if ('ORACLE' === this.type) {
        this.iconPath = "oracle.svg";
        this.text = 'ORACLE';
      } else if ('ELASTICSEARCH' === this.type) {
        this.iconPath = "elasticsearch.svg";
        this.text = 'Elastic Search';
      } else if ('KAFKA' === this.type) {
        this.iconPath = 'kafka.svg';
        this.text = "Kafka";
      } else if ('HTTP' === this.type) {
        this.iconPath = 'http.svg';
        this.text = 'Http';
      }
    }

  }

}
