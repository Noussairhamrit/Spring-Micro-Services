package tn.esprit.spring;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import tn.esprit.spring.DAO.CategoryRepository;
import tn.esprit.spring.DAO.ProduitRepository;
import tn.esprit.spring.entity.Category;
import tn.esprit.spring.entity.Produit;

@SpringBootApplication
public class ServiceProduitCatalogueApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceProduitCatalogueApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(CategoryRepository categoryRepository,ProduitRepository produitRepository){
		return args->{
			categoryRepository.deleteAll();
			Stream.of("C1 ordinateur","C2 imprimante").forEach(c->{
				categoryRepository.save(new Category (c.split(" ")[0],c.split(" ")[1],new ArrayList<>()));
			});
			categoryRepository.findAll().forEach(System.out::println);
		/////////////////////////////
			produitRepository.deleteAll();
			Category c1=categoryRepository.findById("C1").get();
			Stream.of("P1","P2","P3","P4").forEach(name->{
				Produit p=produitRepository.save(new Produit (null,name,Math.random()*1000,c1));
				c1.getProduits().add(p);
				categoryRepository.save(c1);
			});
			Category c2=categoryRepository.findById("C2").get();
			Stream.of("P5","P6","P7","P8").forEach(name->{
			Produit p=produitRepository.save(new Produit (null,name,Math.random()*1000,c2));
			c2.getProduits().add(p);
			categoryRepository.save(c2);
			});
			produitRepository.findAll().forEach(p->{
				System.out.println(p.toString());
			});
			
		};
		
	}

}
