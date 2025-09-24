import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { LogoComponent } from "../logo/logo.component";

@Component({
  selector: 'app-sidebar',
  imports: [LogoComponent],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {

}
