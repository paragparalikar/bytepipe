import { Component, OnInit } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { SidebarComponent } from '../sidebar/sidebar.component';
import { NavbarComponent } from '../navbar/navbar.component';
import { LoginResponse, OidcSecurityService } from 'angular-auth-oidc-client';
import { AuthService } from '../auth/auth.service';
import { MessageBarComponent } from "../navbar/message-bar/message-bar.component";

@Component({
  selector: 'app-home',
  imports: [RouterOutlet, SidebarComponent, NavbarComponent, MessageBarComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

  constructor (
    private router: Router,
    private authService: AuthService,
    private securityService: OidcSecurityService) { }
  
  ngOnInit(): void {
    this.securityService.checkAuthMultiple(window.location.toString())
    .subscribe((loginResponse: LoginResponse[]) => {
      let loggedInResponse = loginResponse.find(response => response.isAuthenticated);
      this.authService.setLoginResponse(loggedInResponse);
      if(!loggedInResponse){
        this.router.navigateByUrl('/signin');
      }
    });
  }

}
