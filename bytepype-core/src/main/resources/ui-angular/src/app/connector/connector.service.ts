import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Connector } from './connector.model';

@Injectable({
  providedIn: 'root'
})
export class ConnectorService {

  baseUrl = window.location.origin + '/api/connectors';

  constructor(private http:HttpClient) { }

  findAll(): Observable<Connector[]> {
    return this.http.get<Connector[]>(this.baseUrl);
  }

}
