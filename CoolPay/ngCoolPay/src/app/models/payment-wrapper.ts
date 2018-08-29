import { Payment } from 'src/app/models/payment';

export class PaymentWrapper {

  payment: Payment;

  constructor(payment?: Payment) {
    this.payment = payment;
  }
}
