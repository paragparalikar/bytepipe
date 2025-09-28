import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Connector } from './connector.model';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class ConnectorService {

  baseUrl = environment.baseUrl + '/api/connectors';

  constructor(private http:HttpClient) { }

  findAll(): Observable<Connector[]> {
    return this.http.get<Connector[]>(this.baseUrl);
  }

  create(connector: Connector): Observable<Connector>{
    return this.http.post<Connector>(this.baseUrl, connector);
  }
}
