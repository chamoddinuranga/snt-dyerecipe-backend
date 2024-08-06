package lk.snt.dyeBackend.repo;

import lk.snt.dyeBackend.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    boolean existsByLabDip(String labDip);
    Optional<Recipe> findByLabDip(String labDip);
    void deleteByLabDip(String labDip);
    List<Recipe> findByLabDipContaining(String query);
    /*boolean existsByGrnNumber(int grnNumber);
    Optional<Recipe> findByGrnNumber(int grnNumber);*/
}
