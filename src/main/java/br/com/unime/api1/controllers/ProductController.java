package br.com.unime.api1.controllers;

import br.com.unime.api1.entities.Product;
import br.com.unime.api1.exceptions.ResourceNotFoundException;
import br.com.unime.api1.repository.ProductRepository;
import br.com.unime.api1.requests.ProductNameResquest;
import br.com.unime.api1.requests.ProductResquest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import br.com.unime.api1.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAll(){
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getByid(@PathVariable Long id) throws ResourceNotFoundException {

        return ResponseEntity.ok(productService.getById(id));
    }

    @PostMapping("/products")
    public ResponseEntity<Product> insert(@RequestBody ProductResquest productResquest){
        Product product = productService.insert(productResquest);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody ProductResquest productResquest){
        Product product = null;
        try {
            product = productService.update(id, productResquest);
            return ResponseEntity.ok(product);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @PatchMapping("/products/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody ProductNameResquest productNameResquest){
        Product product = null;
        try {
            product = productService.update(id, productNameResquest);
            return ResponseEntity.ok(product);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws ResourceNotFoundException {
        productService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleException (ResourceNotFoundException ex){
        Map<String, String> responseMessage = new HashMap<>();
        responseMessage.put("messege", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
    }



}
