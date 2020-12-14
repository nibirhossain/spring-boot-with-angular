import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { from } from 'rxjs';
import { BasicAuthenticationService } from 'src/app/service/basic-authentication.service';
import { SignupRequest } from './../../shared/model/signup-request';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signupForm : any;

  constructor(
    private router: Router,
    private basicAuthenticationService: BasicAuthenticationService,
    private formBuilder: FormBuilder 
  ) { 
    this.signupForm = this.formBuilder.group({
      username: ['nibir', Validators.required],
      email: ['nibir.hossain@hossain.de', Validators.required],
      password: ['hossain', Validators.required],
      repassword: ["hossain", Validators.required]
  });
  }

  ngOnInit(): void {
  }

  onSubmit(signupData : SignupRequest) {
    this.basicAuthenticationService.signup(signupData)
        .subscribe(
          data => {
            // this.router.navigate(['todos'])
            console.log(data);
          },
          error => {
            console.log(error)
          }
        )
  }

}
