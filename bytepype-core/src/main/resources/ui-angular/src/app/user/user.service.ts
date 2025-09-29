import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { Observable } from 'rxjs';
import { User } from './user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private readonly baseUrl: string = environment.baseUrl + '/api/users';

  constructor(private httpClient: HttpClient) { }

  findById(id: string): Observable<User> {
    return this.httpClient.get(this.baseUrl + '/' + id);
  }

}
