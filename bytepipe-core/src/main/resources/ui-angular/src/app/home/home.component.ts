import { Component, OnInit } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { SidebarComponent } from '../sidebar/sidebar.component';
import { NavbarComponent } from '../navbar/navbar.component';
import { LoginResponse, OidcSecurityService } from 'angular-auth-oidc-client';
import { UserService } from '../security/user.service';

@Component({
  selector: 'app-home',
  imports: [RouterOutlet, SidebarComponent, NavbarComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

  constructor (
    private router: Router,
    private userService: UserService,
    private securityService: OidcSecurityService) { }
  
  ngOnInit(): void {
    this.securityService.checkAuthMultiple(window.location.toString())
    .subscribe((loginResponse: LoginResponse[]) => {
      let loggedInResponse = loginResponse.find(response => response.isAuthenticated);
      if(loggedInResponse){
        console.log('configId ' + loggedInResponse.configId);
        console.log("User Data : " + JSON.stringify(loggedInResponse.userData));
        localStorage.setItem('oauth2-config-id', loggedInResponse.configId ?? "");
        this.userService.create(loggedInResponse.userData).subscribe(response => {
          console.log(JSON.stringify(response));
        });
      } else {
        this.router.navigateByUrl('/signin');
      }
    });
  }

}
