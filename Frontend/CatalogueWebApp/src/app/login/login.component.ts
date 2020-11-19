import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
  import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private service:AuthenticationService,
    private route:Router) { }

  ngOnInit(): void {
  }
  onLogin(data){
    this.service.login(data).subscribe(resp=>{
      /**recupere le tocken */

      console.log(resp.headers.get('Authorization'));
      let jwt=resp.headers.get('Authorization');
      this.service.saveToken(jwt);
      this.route.navigateByUrl("/");
    })
    }

    isAdmin(){
      return this.service.isAdmin();
    }
    isUser(){
      return this.service.isUser();
    }


}
