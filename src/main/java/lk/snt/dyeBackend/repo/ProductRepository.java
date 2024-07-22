package lk.snt.dyeBackend.repo;

import lk.snt.dyeBackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByProductName(String productName);
    Optional<Product> findByProductName(String productName);
    void deleteByProductName(String productName);
}

