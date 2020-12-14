import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { from } from 'rxjs';
import {map} from 'rxjs/operators';

import { LoginRequest } from './../shared/model/login-request';
import { LoginResponse } from './../shared/model/login-response';

export const TOKEN = 'token';
export const AUTHENTICATED_USER = 'authenticatedUser';
export const API_BASE_URL: string = 'http://localhost:8080/api/v1';

@Injectable({
  providedIn: 'root'
})
export class BasicAuthenticationService {

  constructor(private http: HttpClient) { }

  executeAuthenticationService(username, password) {
    
    let basicAuthHeaderString = 'Basic ' + window.btoa(username + ':' + password);

    let headers = new HttpHeaders({
        Authorization: basicAuthHeaderString
      })

    return this.http.get<AuthenticationBean>(
      `${API_BASE_URL}/login`,
      {headers}).pipe(
        map(
          data => {
            sessionStorage.setItem(AUTHENTICATED_USER, username);
            sessionStorage.setItem(TOKEN, basicAuthHeaderString);
            return data;
          }
        )
      );
  }
  login(loginRequest : LoginRequest){
    return  this.http.post<LoginResponse>(`${API_BASE_URL}/auth/login`, loginRequest)
    .pipe(
      map(
        data => {
          console.log("Login data", data);
          sessionStorage.setItem(AUTHENTICATED_USER, data.username);
          sessionStorage.setItem(TOKEN, `${data.tokenType} ${data.token}`);
          return data;
        }
      )
    );
  }

  getAuthenticatedUser() {
    return sessionStorage.getItem(AUTHENTICATED_USER)
  }

  getAuthenticatedToken() {
    if(this.getAuthenticatedUser())
      return sessionStorage.getItem(TOKEN)
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem(AUTHENTICATED_USER)
    return !(user === null)
  }

  logout(){
    sessionStorage.removeItem(AUTHENTICATED_USER)
    sessionStorage.removeItem(TOKEN)
  }

}

export class AuthenticationBean{
  constructor(public message:string) { }
}
