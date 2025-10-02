import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Dataflow } from './data-flow.model';

@Injectable({
  providedIn: 'root'
})
export class DataFlowService {

  private baseUrl = '/api/dataflows';

  constructor(private http: HttpClient) { }

  findAll(): Observable<Dataflow[]> {
    // TODO: Replace with actual API call
    // return this.http.get<Dataflow[]>(this.baseUrl);
    
    // Sample data for UI testing
    const sampleDataflows: Dataflow[] = [
      new Dataflow({
        id: '1',
        name: 'Oracle to Kafka Pipeline',
        description: 'Data pipeline from Oracle database to Kafka topic',
        enabled: true,
        sourceConnectorId: 1,
        destinationConnectorId: 2,
        createdBy: 'admin',
        createdDate: new Date('2024-01-15'),
        lastUpdatedBy: 'admin',
        lastUpdatedDate: new Date('2024-01-20')
      }),
      new Dataflow({
        id: '2',
        name: 'HTTP to Oracle Sync',
        description: 'Synchronize HTTP API data to Oracle database',
        enabled: false,
        sourceConnectorId: 3,
        destinationConnectorId: 1,
        createdBy: 'user1',
        createdDate: new Date('2024-01-10'),
        lastUpdatedBy: 'user1',
        lastUpdatedDate: new Date('2024-01-18')
      })
    ];
    
    return of(sampleDataflows);
  }

  findById(id: string): Observable<Dataflow> {
    // TODO: Replace with actual API call
    // return this.http.get<Dataflow>(`${this.baseUrl}/${id}`);
    
    // Sample data for UI testing
    const sampleDataflow = new Dataflow({
      id: id,
      name: 'Sample Dataflow',
      description: 'Sample dataflow for testing',
      enabled: true,
      sourceConnectorId: 1,
      destinationConnectorId: 2,
      createdBy: 'admin',
      createdDate: new Date(),
      lastUpdatedBy: 'admin',
      lastUpdatedDate: new Date()
    });
    
    return of(sampleDataflow);
  }

  create(dataflow: Dataflow): Observable<Dataflow> {
    // TODO: Replace with actual API call
    // return this.http.post<Dataflow>(this.baseUrl, dataflow);
    
    // Mock response for UI testing
    const createdDataflow = new Dataflow({
      ...dataflow,
      id: Math.random().toString(36).substr(2, 9),
      createdBy: 'current-user',
      createdDate: new Date(),
      lastUpdatedBy: 'current-user',
      lastUpdatedDate: new Date()
    });
    
    return of(createdDataflow);
  }

  update(id: string, dataflow: Dataflow): Observable<Dataflow> {
    // TODO: Replace with actual API call
    // return this.http.put<Dataflow>(`${this.baseUrl}/${id}`, dataflow);
    
    // Mock response for UI testing
    const updatedDataflow = new Dataflow({
      ...dataflow,
      id: id,
      lastUpdatedBy: 'current-user',
      lastUpdatedDate: new Date()
    });
    
    return of(updatedDataflow);
  }

  delete(id: string): Observable<void> {
    // TODO: Replace with actual API call
    // return this.http.delete<void>(`${this.baseUrl}/${id}`);
    
    // Mock response for UI testing
    return of(void 0);
  }
}
