import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class CatalogueService {
 
  public host:String="http://localhost:8080";
  constructor(private http : HttpClient , private authService:AuthenticationService) { }

  getAllcategories(){
    return this.http.get(this.host+"/categories");
  }
  getResource(url){
    return this.http.get(url);
  }
  deleteResource(url){
      let headers=new HttpHeaders({'authorization':'Bearer '+this.authService.jwt})
    return this.http.delete(url,{headers : headers});
  }
  postResource(url,data){
    let headers=new HttpHeaders({'authorization':'Bearer '+this.authService.jwt})
  return this.http.post(url,data,{headers : headers});
}
putResource(url,data) {
  let headers=new HttpHeaders({'authorization':'Bearer '+this.authService.jwt})
  return this.http.patch(url,data,{headers : headers});
}

}
