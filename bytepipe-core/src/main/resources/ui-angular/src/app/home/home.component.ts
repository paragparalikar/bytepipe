import { Component, OnInit } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { SidebarComponent } from '../sidebar/sidebar.component';
import { NavbarComponent } from '../navbar/navbar.component';
import { LoginResponse, OidcSecurityService } from 'angular-auth-oidc-client';

@Component({
  selector: 'app-home',
  imports: [RouterOutlet, SidebarComponent, NavbarComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

  constructor (
    private router: Router,
    private securityService: OidcSecurityService) { }
  
  ngOnInit(): void {
    this.securityService.checkAuthMultiple(window.location.toString())
    .subscribe((loginResponse: LoginResponse[]) => {
      let loggedInResponse = loginResponse.find(response => response.isAuthenticated);
      if(loggedInResponse){
        console.log('configId ' + loggedInResponse.configId);
        console.log("User Data : " + JSON.stringify(loggedInResponse.userData));
      } else {
        this.router.navigateByUrl('/signin');
      }
    });
  }

}
