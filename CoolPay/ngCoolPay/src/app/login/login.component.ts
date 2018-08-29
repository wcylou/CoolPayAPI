import { LoginService } from './../login.service';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  login(form: NgForm) {
    this.loginService.login(form.value.username, form.value.password).subscribe(
      data => {
        this.router.navigateByUrl('todo');
      },
      err => console.error('Register error' + err)
    );
  }


  constructor(private loginService: LoginService) { }

  ngOnInit() {
  }

}
