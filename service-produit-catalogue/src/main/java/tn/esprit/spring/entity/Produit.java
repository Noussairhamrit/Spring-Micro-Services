package tn.esprit.spring.entity;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class Produit {
	@Id
private String id;
private String name;
private double price;
@DBRef
private Category category;

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

public double getPrice() {
	return price;
}

public void setPrice(double price) {
	this.price = price;
}

public Category getCategory() {
	return category;
}

public void setCategory(Category category) {
	this.category = category;
}

public Produit(String id, String name, double price, Category category) {
	super();
	this.id = id;
	this.name = name;
	this.price = price;
	this.category = category;
}

public Produit() {
	super();
	// TODO Auto-generated constructor stub
}

@Override
public String toString() {
	return "Produit [id=" + id + ", name=" + name + ", price=" + price + ", category=" + category + "]";
}





}
