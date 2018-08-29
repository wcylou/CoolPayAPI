export class Payment {

  id: string;
  amount: number;
  currency: string;
  name: string;
  status: string;
  recipient_id: string;

  constructor( id?: string, amount?: number, currency?: string, name?: string, status?: string,  recipient_id?: string) {
    this.id = id;
    this.amount = amount;
    this.currency = currency;
    this.name = name;
    this.status = status;
    this. recipient_id =  recipient_id;
  }

}
