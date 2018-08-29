import { Component, OnInit } from '@angular/core';
import { PaymentService } from '../payment.service';
import { NgForm } from '@angular/forms';
import { PaymentWrapper } from '../models/payment-wrapper';
import { Payment } from '../models/payment';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {
  newPaymentWrapper = null;
  showPayments = [];
  newPayment = null;

  addPayment(form: NgForm) {
    this.newPayment = new Payment(undefined, form.value.amount, form.value.currency, undefined, form.value.recipientId);
    this.newPaymentWrapper = new PaymentWrapper(this.newPayment);
    console.log(this.newPaymentWrapper);

    this.paymentService.createPayment(this.newPaymentWrapper).subscribe(
            data => {
              console.log(data);
              this.newPaymentWrapper = new PaymentWrapper();
              form.reset();
            },
            err => console.error('Tracker error' + err)
          );
  }

reload() {
    this.paymentService.index().subscribe(
      (data) => {
              console.log(data);
              this.showPayments = data;
                },
      (err) => console.log('Tracker error' + err)
    );
}

  constructor(private paymentService: PaymentService) { }

  ngOnInit() {
    this.reload();
  }

}
