import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { Recipient } from 'src/app/models/recipient';
import { environment } from '../environments/environment';
import { RecipientWrapper } from './models/recipient-wrapper';

@Injectable({
  providedIn: 'root'
})
export class RecipientService {

  private url = environment.baseUrl + 'api/recipients';

  create(recipient) {
    return this.http.post<RecipientWrapper>(this.url, recipient)
    .pipe(
      catchError((err: any) => {
       console.log(err);
       return throwError('KABOOM');
      })
    );
  }

  index(recipientName) {
    return this.http.get<Recipient>(this.url + '/' + recipientName)
    .pipe(
      catchError((err: any) => {
       console.log(err);
       return throwError('KABOOM');
      })
    );
  }


  constructor(private http: HttpClient) { }
}
