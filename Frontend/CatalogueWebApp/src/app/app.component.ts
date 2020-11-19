import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../app/services/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'CatalogueWebApp';
  constructor(private service:AuthenticationService) { }

  ngOnInit(): void {
    this.service.loadToken();
  }

  isAdmin(){
    return this.service.isAdmin();
  }
  isUser(){
    return this.service.isUser();
  }
  isAuthenticated(){
    return this.service.isAuthenticated();
  }
  logOut(){
    this.service.logOut();
  }
}
