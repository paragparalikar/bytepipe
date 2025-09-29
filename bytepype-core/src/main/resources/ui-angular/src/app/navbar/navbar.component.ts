import { Component } from '@angular/core';
import { LogoComponent } from "../logo/logo.component";
import { UserProfileComponent } from "./user-profile/user-profile.component";
import { MessageBarComponent } from "./message-bar/message-bar.component";

@Component({
  selector: 'app-navbar',
  imports: [LogoComponent, UserProfileComponent, MessageBarComponent],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

}
