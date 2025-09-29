import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../auth/auth.service';

@Component({
  selector: 'app-user-profile',
  imports: [],
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.css'
})
export class UserProfileComponent implements OnInit {
  name: string|undefined;
  picture: string|undefined;

  constructor (private authService: AuthService) { }

   ngOnInit(): void {
    this.authService.getLoginResponse().subscribe(loginResponse => {
      if(loginResponse){
        this.name = loginResponse.userData.name;
        this.picture = loginResponse.userData.picture;
      } else {
        this.name = undefined;
        this.picture = undefined;
      }
    });
   }

}
