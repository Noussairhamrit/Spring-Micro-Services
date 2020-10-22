package tn.esprit.spring.DAO;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import tn.esprit.spring.entity.Category;
@RepositoryRestResource
public interface CategoryRepository extends MongoRepository<Category,String>{

}
