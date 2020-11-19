package tn.esprit.spring.entity;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Category {
	@Id
private String id;
private String name;
/*dans un documant Catégorie je ne vais enregistrer que les Id des produit*/
@DBRef
private Collection<Produit> produits =new ArrayList<>();






public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Collection<Produit> getProduits() {
	return produits;
}
public void setProduits(Collection<Produit> produits) {
	this.produits = produits;
}
public Category(String id, String name, Collection<Produit> produits) {
	super();
	this.id = id;
	this.name = name;
	this.produits = produits;
}
public Category() {
	super();

}
@Override
public String toString() {
	return "Catalogue [id=" + id + ", name=" + name + "]";
}


}
