package be.ehb.projecttime4spring.model.DAO;

import be.ehb.projecttime4spring.model.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductDAO extends CrudRepository<Product, Integer> {

    public Iterable<Product> findProductByNaamContaining(String keyword);

}
