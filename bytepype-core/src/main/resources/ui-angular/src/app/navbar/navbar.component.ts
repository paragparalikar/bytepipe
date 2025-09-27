import { Component } from '@angular/core';
import { LogoComponent } from "../logo/logo.component";
import { UserProfileComponent } from "./user-profile/user-profile.component";

@Component({
  selector: 'app-navbar',
  imports: [LogoComponent, UserProfileComponent],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

}
