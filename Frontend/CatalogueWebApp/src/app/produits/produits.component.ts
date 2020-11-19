import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { CatalogueService } from '../services/catalogue.service';

@Component({
  selector: 'app-produits',
  templateUrl: './produits.component.html',
  styleUrls: ['./produits.component.css']
})
export class ProduitsComponent implements OnInit {
  products;
  constructor(private service:CatalogueService ,
     private route:ActivatedRoute ,
     private router :Router) { 
    router.events.subscribe(event=>{
        if(event instanceof NavigationEnd){
          let url =atob(route.snapshot.params.urlProds);
          this.getProducts(url)
        }
    })
    
  }
  
  ngOnInit(): void {
  }
  getProducts(url){
    this.service.getResource(url).subscribe((data)=>{
      this.products=data;
      console.log(this.products);
    },err=>{
      console.log(err)
    })
  }
}
