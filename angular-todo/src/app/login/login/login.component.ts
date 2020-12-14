import { BasicAuthenticationService } from './../../service/basic-authentication.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginRequest } from 'src/app/shared/model/login-request';
import { LoginResponse } from 'src/app/shared/model/login-response';
import { FormBuilder, Validators } from '@angular/forms';

export const TOKEN = 'token';
export const AUTHENTICATED_USER = 'authenticatedUser';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
  loginForm: any;

  constructor(
    private router: Router,
    private basicAuthenticationService: BasicAuthenticationService,
    private formBuilder: FormBuilder) { 
        this.loginForm = this.formBuilder.group({
          username: ['nibir', Validators.required],
          password: ['hossain', Validators.required]
      });

    }

  ngOnInit() {
    
  }

  onSubmit(loginData : LoginRequest) {
    this.basicAuthenticationService.login(loginData)
        .subscribe(
          data => {
            this.router.navigate(['todos'])
          },
          error => {
            console.log(error)
          }
        )
  }

  /**
  handleJWTAuthLogin() {
    this.basicAuthenticationService.executeJWTAuthenticationService(this.username, this.password)
        .subscribe(
          data => {
            console.log(data)
            this.router.navigate(['welcome', this.username])
            this.invalidLogin = false      
          },
          error => {
            console.log(error)
            this.invalidLogin = true
          }
        )
  }
 */
}