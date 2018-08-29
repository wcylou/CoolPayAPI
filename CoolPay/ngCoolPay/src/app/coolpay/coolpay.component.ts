import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { NgForm } from '@angular/forms';
import { LoginService } from 'src/app/login.service';
import { PaymentService } from 'src/app/payment.service';
import { PaymentWrapper } from 'src/app/models/payment-wrapper';
import { Payment } from 'src/app/models/payment';
import { RecipientService } from 'src/app/recipient.service';
import { Recipient } from 'src/app/models/recipient';
import { RecipientWrapper } from 'src/app/models/recipient-wrapper';

@Component({
  selector: 'app-coolpay',
  templateUrl: './coolpay.component.html',
  styleUrls: ['./coolpay.component.css']
})
export class CoolpayComponent implements OnInit {

  logInForm = true;
  recipientForms = false;
  paymentForms = false;

  newUser: User = new User();
  newPaymentWrapper = null;
  showPayments = [];
  newPayment = null;
  newRecipientWrapper = null;
  showSingleRecipient = null;

  showRecipientOptions() {
    this.recipientForms = true;
    this.paymentForms = false;
    this.logInForm = false;
  }

  showPaymentOptions() {
    this.recipientForms = false;
    this.paymentForms = true;
    this.logInForm = false;
  }


  login(form: NgForm) {
    this.newUser.apikey = form.value.apikey;
    this.newUser.username = form.value.username;
    this.loginService.login(this.newUser).subscribe(
      data => {
        console.error('Logged in');
        this.logInForm = false;
      },
      err => console.error('Register error' + err)
    );
  }

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

  addRecipient(form: NgForm) {
    this.newRecipientWrapper = new RecipientWrapper(form.value.name);
    this.recipientService.create(this.newRecipientWrapper).subscribe(
            data => {
              this.newRecipientWrapper = new RecipientWrapper();
              form.reset();
            },
            err => console.error('Tracker error' + err)
          );
  }

  index(form: NgForm) {
    this.recipientService.index(form.value.recipientName).subscribe(
      (data) => {
              console.log(data);
                this.showSingleRecipient = new Recipient(data.id, data.name);
                },
      (err) => console.log('Tracker error' + err)
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

  constructor(private loginService: LoginService, private paymentService: PaymentService, private recipientService: RecipientService) { }

  ngOnInit() {
    this.reload();
  }

}
