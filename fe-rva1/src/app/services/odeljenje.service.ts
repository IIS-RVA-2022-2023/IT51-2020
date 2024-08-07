import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ODELJENJE_URL } from '../app.constants';
import { ODELJENJA_URL } from '../app.constants';
import { Observable } from 'rxjs';
import { Odeljenje } from '../models/odeljenje';

@Injectable({
  providedIn: 'root'
})
export class OdeljenjeService {

  constructor(private httpClient: HttpClient) { }

  public getAllOdeljenje(): Observable<any>{
    return this.httpClient.get(`${ODELJENJE_URL}`);
  }

  public getAllOdeljenja(bolnicaId: number): Observable<any>{
    return this.httpClient.get(ODELJENJA_URL + '/' + bolnicaId);
  }

  public addOdeljenje(odeljenje: Odeljenje): Observable<any>{
    return this.httpClient.post(ODELJENJE_URL, odeljenje);
  }

  public updateOdeljenje(odeljenje: Odeljenje): Observable<any>{
    return this.httpClient.put(ODELJENJE_URL + '/' + odeljenje.id, odeljenje);
  }

  public deleteOdeljenje(odeljenjeId: Number): Observable<any>{
    return this.httpClient.delete(ODELJENJE_URL + '/' + odeljenjeId);
  }

}
