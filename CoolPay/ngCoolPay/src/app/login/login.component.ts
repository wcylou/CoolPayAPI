import { LoginService } from './../login.service';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { User } from '../models/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  newUser: User = new User();

  login(form: NgForm) {
    this.newUser.apikey = form.value.apikey;
    this.newUser.username = form.value.username;
    this.loginService.login(this.newUser).subscribe(
      data => {
        console.error('Logged in');
      },
      err => console.error('Register error' + err)
    );
  }


  constructor(private loginService: LoginService) { }

  ngOnInit() {
  }

}
