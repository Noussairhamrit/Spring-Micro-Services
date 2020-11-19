import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProduitsComponent } from './produits/produits.component';
import { LoginComponent } from './login/login.component';
import { AdminCategoriesComponent } from './admin-categories/admin-categories.component';
import { AdminProduitsComponent } from './admin-produits/admin-produits.component';
import { AdminUsersComponent } from './admin-users/admin-users.component';


const routes: Routes = [
    {path:"products/:urlProds" , component:ProduitsComponent},
    {path:"login" , component:LoginComponent},
    {path:"adminCategories" , component:AdminCategoriesComponent},
    {path:"adminProuits" , component:AdminProduitsComponent},
    {path:"adminUsers" , component:AdminUsersComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
