import { RecipientWrapper } from './../models/recipient-wrapper';
import { RecipientService } from './../recipient.service';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Recipient } from '../models/recipient';

@Component({
  selector: 'app-recipient',
  templateUrl: './recipient.component.html',
  styleUrls: ['./recipient.component.css']
})
export class RecipientComponent implements OnInit {
  newRecipientWrapper = null;
  showSingleRecipient = null;

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

  constructor(private recipientService: RecipientService) { }

  ngOnInit() {
  }


}
