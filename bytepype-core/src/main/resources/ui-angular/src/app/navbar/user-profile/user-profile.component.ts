import { Component, OnInit } from '@angular/core';
import { OidcSecurityService } from 'angular-auth-oidc-client';

@Component({
  selector: 'app-user-profile',
  imports: [],
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.css'
})
export class UserProfileComponent implements OnInit {
  name: string|undefined;
  picture: string|undefined;

  constructor (private securityService: OidcSecurityService) { }

   ngOnInit(): void {
    const userDataSignal = this.securityService.userData;
    const userData = userDataSignal().allUserData.find(entry => entry.userData)?.userData;
    this.name = userData?.name;
    this.picture = userData?.picture;
   }

}
