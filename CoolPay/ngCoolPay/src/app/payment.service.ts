import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PaymentWrapper } from './models/payment-wrapper';
import { environment } from '../environments/environment';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { Payment } from './models/payment';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  private url = environment.baseUrl + 'api/payments';

  createPayment(payment) {
    return this.http.post<PaymentWrapper>(this.url, payment)
    .pipe(
      catchError((err: any) => {
       console.log(err);
       return throwError('KABOOM');
      })
    );
  }

  index() {
    return this.http.get<Payment[]>(this.url)
    .pipe(
      catchError((err: any) => {
       console.log(err);
       return throwError('KABOOM');
      })
    );
  }

  constructor(private http: HttpClient) { }
}
