package upd.dev.bbfp.logistic.service;

import org.springframework.stereotype.Service;
import upd.dev.bbfp.logistic.model.Product;
import upd.dev.bbfp.logistic.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    public ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return  productRepository.save(product);
    }
    public Product getProduct(Long id) {
        return productRepository.findById(id).get();
    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

}