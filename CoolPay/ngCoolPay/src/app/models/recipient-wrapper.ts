import { Recipient } from 'src/app/models/recipient';

export class RecipientWrapper {

  recipient: Recipient;

  constructor(recipient?: Recipient) {
    this.recipient = recipient;
  }

}
