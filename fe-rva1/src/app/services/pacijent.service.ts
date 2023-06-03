import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PACIJENT_URL } from '../app.constants';
import { Pacijent } from '../models/pacijent';

@Injectable({
  providedIn: 'root'
})
export class PacijentService {

  constructor(private httpClient: HttpClient) { }

  public getAllPacijent(): Observable<any>{
    return this.httpClient.get(`${PACIJENT_URL}`);
  }

  public addPacijent(pacijent: Pacijent): Observable<any> {
    return this.httpClient.post(PACIJENT_URL, pacijent);
  }

  public updatePacijent(pacijent: Pacijent): Observable<any> {
    return this.httpClient.put(PACIJENT_URL + '/' + pacijent.id, pacijent);
  }

  public deletePacijent(pacijentId: number): Observable<any> {
    return this.httpClient.delete(PACIJENT_URL + '/' + pacijentId)
  };
  
}
