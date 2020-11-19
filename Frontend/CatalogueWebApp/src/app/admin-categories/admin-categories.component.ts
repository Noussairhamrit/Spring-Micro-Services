import { Component, OnInit } from '@angular/core';
import { CatalogueService } from '../services/catalogue.service';

@Component({
  selector: 'app-admin-categories',
  templateUrl: './admin-categories.component.html',
  styleUrls: ['./admin-categories.component.css']
})
export class AdminCategoriesComponent implements OnInit {

  constructor(private service:CatalogueService) { }
 
  categories;
  mode='list';
  currentCategorie;
  ngOnInit(): void {
   this.onGetAllCategories();
  }
  onGetAllCategories(){
    this.service.getAllcategories().subscribe(data=>{
      this.categories=data;
    },err=>{
      console.log(err);
    })
  }
  onDeleteCat(c){
    let alert=confirm("Etes vous sure?");
    if(!alert) return;
    this.service.deleteResource(c._links.self.href).subscribe(data=>{
      this.onGetAllCategories();
    },err=>{
      console.log(err)
    })
  }

  onNewCat(){
    this.mode='new-cat';
  }
  onSaveCat(data){
    let url=this.service.host+"/categories";
    this.service.postResource(url,data).subscribe(data=>{
      this.mode='list';
      this.onGetAllCategories();
    },err=>{
      console.log(err);
    })
  }

  onEditCat(c){
    this.service.getResource(c._links.self.href)
    .subscribe(data=>{
      this.currentCategorie=data;
      this.mode='edit-cat';

    },err=>{
      
    })
  }
  onUpdateCat(data){
    let url=this.service.host+"/categories";
    this.service.putResource(this.currentCategorie._links.self.href,data).subscribe(data=>{
      this.mode='list';
      this.onGetAllCategories();
    },err=>{
      console.log(err);
    })
  }

}
