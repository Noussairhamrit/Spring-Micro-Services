import { Route } from '@angular/compiler/src/core';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CatalogueService } from '../services/catalogue.service';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit {

  constructor(private srevice:CatalogueService ,private router:Router) { }
  categories;
  currentCategory;
  ngOnInit(): void {
    this.srevice.getAllcategories().subscribe((data)=>{
      this.categories=data;
    },err=>{
      console.log(err);
    });
  }
 
  onGetProducts(c){
  this.currentCategory=c;
    let url=c._links.produits.href;
    this.router.navigateByUrl("/products/"+btoa(url));
  }
}
