package app.sinulingga.productsservice.repository;

import app.sinulingga.productsservice.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query(value = "SELECT * FROM products.products p WHERE LOWER(p.name) LIKE %:name%", nativeQuery = true)
    Page<Product> findByNameContaining(@Param("name") String name, Pageable pageable);
}
