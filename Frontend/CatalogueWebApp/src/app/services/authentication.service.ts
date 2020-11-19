import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Local } from 'protractor/built/driverProviders';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  
  
  host:string="http://localhost:8881";
  jwt: string;
  username:string;
  roles:Array<string>;

  constructor(private http:HttpClient) { }

  login(data){
    return this.http.post(this.host+"/login",data,{observe:'response'});
  }

  saveToken(jwt) {
    localStorage.setItem('token',jwt)
    this.jwt=jwt;
    this.parseJwt();
  }

  parseJwt(){
    let jwtHelper=new JwtHelperService();
    let objJWT=jwtHelper.decodeToken(this.jwt)
    this.username=objJWT.obj;
    this.roles=objJWT.roles;
  }

  isAdmin(){
    return this.roles.indexOf('ADMIN')>=0;
  }
  isUser(){
    return this.roles.indexOf('USER')>=0;
  }
  isAuthenticated(){
    return this.roles && (this.isAdmin() || this.isUser())
  }

  loadToken() {
    this.jwt=localStorage.getItem('token')
    this.parseJwt();
  }
  logOut() {
    localStorage.getItem('token');
    this.intParams();
  }
  intParams(){
    this.jwt=undefined;
    this.username=undefined;
    this.roles=undefined;
  }
 
}
