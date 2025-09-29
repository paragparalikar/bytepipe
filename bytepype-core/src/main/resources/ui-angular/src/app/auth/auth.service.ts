import { Injectable } from '@angular/core';
import { LoginResponse } from 'angular-auth-oidc-client';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginResponseSubject: BehaviorSubject<LoginResponse|undefined> = new BehaviorSubject<LoginResponse|undefined>(undefined);
  private loginResponseObservable: Observable<LoginResponse|undefined> = this.loginResponseSubject.asObservable();

  public getLoginResponse(): Observable<LoginResponse|undefined>{
    return this.loginResponseObservable;
  }

  public setLoginResponse(loginResponse: LoginResponse|undefined){
    this.loginResponseSubject.next(loginResponse);
  }
  
}
