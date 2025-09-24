import { Component } from '@angular/core';
import { LogoComponent } from "../../logo/logo.component";
import { OidcSecurityService } from 'angular-auth-oidc-client';

@Component({
  selector: 'app-login',
  imports: [LogoComponent],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  
  constructor(private oidcSecurityService: OidcSecurityService){}

  login(configId: string): void {
    this.oidcSecurityService.authorize(configId);
  }

}
