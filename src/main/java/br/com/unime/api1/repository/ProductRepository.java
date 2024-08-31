package br.com.unime.api1.repository;

import br.com.unime.api1.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
