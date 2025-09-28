import { Component, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterLink } from "@angular/router";

@Component({
  selector: 'app-connector',
  imports: [RouterLink],
  templateUrl: './connector.component.html',
  styleUrl: './connector.component.css',
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ConnectorComponent {

}
