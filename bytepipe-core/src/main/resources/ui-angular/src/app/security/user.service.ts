import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private readonly baseUrl: string = 'http://localhost:8080/api';

  constructor(private httpClient: HttpClient) { }

  public create(user: any): Observable<Object> {
    return this.httpClient.post(this.baseUrl + '/users', user);
  }

}
