export class Payment {

  id: string;
  amount: number;
  currency: string;
  name: string;
  status: string;

  constructor( id: string, amount: number, currency: string, name: string, status: string) {
    this.id = id;
    this.amount = amount;
    this.currency = currency;
    this.name = name;
    this.status = status;
  }

}
