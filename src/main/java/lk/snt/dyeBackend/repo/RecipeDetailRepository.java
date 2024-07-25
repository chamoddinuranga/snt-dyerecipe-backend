package lk.snt.dyeBackend.repo;

import lk.snt.dyeBackend.entity.RecipeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RecipeDetailRepository extends JpaRepository<RecipeDetail, Long> {
    // No need to change `existsById` method; JpaRepository already includes it

    // Add any custom queries if needed. For example, if you need a method to find by recipeId
    //Optional<RecipeDetail> findByRecipeId(@Param("recipeId") Long recipeId);

    // Add other custom methods as needed based on your requirements
}

