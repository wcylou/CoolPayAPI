import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginService } from './login.service';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login/login.component';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { RecipientComponent } from './recipient/recipient.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import { PaymentComponent } from './payment/payment.component';
import { CoolpayComponent } from './coolpay/coolpay.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RecipientComponent,
    PaymentComponent,
    CoolpayComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule.forRoot(),
    MatFormFieldModule
  ],
  providers: [
    LoginService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
