package com.gy.ecommerce.business.concretes;

import com.gy.ecommerce.business.abstracts.ProductService;
import com.gy.ecommerce.entities.Product;
import com.gy.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductManager implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(int id) {
        checkIfProductExists(id);
        return productRepository.findById(id).get();
    }

    @Override
    public Product add(Product product) {
        validProduct(product);
        return productRepository.save(product);
    }

    @Override
    public Product update(int id, Product product) {
        checkIfProductExists(id);
        validProduct(product);
        product.setId(id);
        return productRepository.save(product);
    }

    @Override
    public void delete(int id) {
        checkIfProductExists(id);
        productRepository.deleteById(id);
    }


    // Business Rules

    private void validProduct(Product product){
        checkIfUnitPriceValid(product);
        checkIfQuantityValid(product);
        checkIfDescriptionValid(product);
    }

    private void checkIfProductExists(int id) {
        if (!productRepository.existsById(id)) throw new
                IllegalArgumentException("Böyle bir ürün mevcut değil!");
    }
    private void checkIfUnitPriceValid(Product product){
        if (product.getUnitPrice() <= 0)
            throw new IllegalArgumentException("Price cannot be less than or equal to zero");
    }
    private void checkIfQuantityValid(Product product){
        if (product.getQuantity() < 0)
            throw new IllegalArgumentException("Quantity cannot be less than zero");
    }
    private void checkIfDescriptionValid(Product product) {
        if (product.getDescription().length() < 10 || product.getDescription().length() > 50)
            throw new IllegalArgumentException("Description length must be between 10 and 50 characters.");
    }
}
