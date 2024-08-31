package br.com.unime.api1.service;

import br.com.unime.api1.entities.Product;
import br.com.unime.api1.exceptions.ResourceNotFoundException;
import br.com.unime.api1.repository.ProductRepository;
import br.com.unime.api1.requests.ProductResquest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public  Product getById(Long id) throws ResourceNotFoundException {
        Optional<Product> optionalProduct =  productRepository.findById(id);

        if (optionalProduct.isEmpty()){
            throw new ResourceNotFoundException("Produto n encontrado");
        }

        return optionalProduct.get();

    }

    public Product insert(ProductResquest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());

        return productRepository.save(product);
    }



}
