import { Component } from '@angular/core';
import { LogoComponent } from "../../logo/logo.component";
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faGoogle, faMicrosoft, faLinkedinIn, faApple, faGithub, faGitlab, faTwitter, faFacebook, faAmazon } from '@fortawesome/free-brands-svg-icons';

@Component({
  selector: 'app-login',
  imports: [LogoComponent, FontAwesomeModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  google = faGoogle;
  microsoft = faMicrosoft;
  linkedin = faLinkedinIn;
  apple = faApple;
  github = faGithub;
  gitlab = faGitlab;
  facebook = faFacebook;
  twitter = faTwitter;
  amazon = faAmazon;
}
