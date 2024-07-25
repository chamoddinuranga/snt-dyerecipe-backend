package lk.snt.dyeBackend.repo;

import lk.snt.dyeBackend.entity.Order;
import lk.snt.dyeBackend.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    /*boolean existsByGrnNumber(int grnNumber);
    Optional<Recipe> findByGrnNumber(int grnNumber);*/
}
