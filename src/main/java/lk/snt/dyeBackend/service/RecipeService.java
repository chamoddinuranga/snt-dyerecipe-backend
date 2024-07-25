package lk.snt.dyeBackend.service;

import jakarta.transaction.Transactional;
import lk.snt.dyeBackend.dto.RecipeDTO;
import lk.snt.dyeBackend.dto.RecipeDetailDTO;
import lk.snt.dyeBackend.entity.Product;
import lk.snt.dyeBackend.entity.Recipe;
import lk.snt.dyeBackend.entity.RecipeDetail;
import lk.snt.dyeBackend.entity.User;
import lk.snt.dyeBackend.repo.ProductRepository;
import lk.snt.dyeBackend.repo.RecipeRepository;
import lk.snt.dyeBackend.repo.UserRepository;
import lk.snt.dyeBackend.util.VarList;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;


//    public String saveRecipe(RecipeDTO recipeDTO) {
//
//        if (recipeRepository.existsByLabDip(recipeDTO.getLabDip())) {
//            return VarList.RSP_DUPLICATED;
//        } else {
//            Recipe recipe = modelMapper.map(recipeDTO, Recipe.class);
//
//            // Save the Recipe entity
//            recipeRepository.save(recipe);
//
//            return VarList.RSP_SUCCESS;
//        }
/*public String saveRecipe(RecipeDTO recipeDTO) {
    if (recipeRepository.existsByLabDip(recipeDTO.getLabDip())) {
        return VarList.RSP_DUPLICATED;
    } else {
        // Convert RecipeDTO to Recipe
        Recipe recipe = modelMapper.map(recipeDTO, Recipe.class);

        // Manually set Recipe and Product references
        for (RecipeDetailDTO detailDTO : recipeDTO.getRecipeDetails()) {
            RecipeDetail detail = new RecipeDetail();
            detail.setQuantityInGrams(detailDTO.getQuantityInGrams());

            // Set Recipe reference
            detail.setRecipe(recipe);

            // Fetch Product entity from the database and set reference
            Product product = productRepository.findById(detailDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            detail.setProduct(product);

            // Add RecipeDetail to Recipe
            recipe.getRecipeDetails().add(detail);
        }

        // Save the Recipe entity
        recipeRepository.save(recipe);

        return VarList.RSP_SUCCESS;
    }
}*/

/*
    public String saveRecipe(RecipeDTO recipeDTO) {
        if (recipeRepository.existsByLabDip(recipeDTO.getLabDip())) {
            return VarList.RSP_DUPLICATED;
        } else {
            // Convert RecipeDTO to Recipe
            Recipe recipe = modelMapper.map(recipeDTO, Recipe.class);

            // Initialize the recipe details set
            Set<RecipeDetail> recipeDetails = new HashSet<>();

            // Handle RecipeDetail entries
            for (RecipeDetailDTO detailDTO : recipeDTO.getRecipeDetails()) {
                RecipeDetail detail = new RecipeDetail();
                detail.setQuantityInGrams(detailDTO.getQuantityInGrams());

                // Set Recipe reference
                detail.setRecipe(recipe);

                // Fetch Product entity from the database and set reference
                Product product = productRepository.findById(detailDTO.getProductId())
                        .orElseThrow(() -> new RuntimeException("Product not found"));
                detail.setProduct(product);

                // Add RecipeDetail to the set
                recipeDetails.add(detail);
            }

            // Set the RecipeDetails in the Recipe entity
            recipe.setRecipeDetails(recipeDetails);

            // Save the Recipe entity
            recipeRepository.save(recipe);

            return VarList.RSP_SUCCESS;
        }
    }
*/



/*
        private static final Logger logger = LoggerFactory.getLogger(RecipeService.class);



        public String saveRecipe(RecipeDTO recipeDTO) {
            if (recipeRepository.existsByLabDip(recipeDTO.getLabDip())) {
                return VarList.RSP_DUPLICATED;
            } else {
                // Convert RecipeDTO to Recipe
                for (RecipeDetailDTO detailDTO : recipeDTO.getRecipeDetails()) {
                    RecipeDetail detail = new RecipeDetail();
                    detail.setQuantityInGrams(detailDTO.getQuantityInGrams());

                    // Set Recipe reference
                    detail.setRecipe(recipe);

                    // Fetch Product entity from the database and set reference
                    Product product = productRepository.findById(detailDTO.getProductId())
                            .orElseThrow(() -> new RuntimeException("Product not found"));

                    // Log the Product entity
                    logger.debug("Fetched Product: {}", product);

                    detail.setProduct(product);

                    // Add RecipeDetail to the set
                    recipeDetails.add(detail);
                }

                // Set the RecipeDetails in the Recipe entity
                recipe.setRecipeDetails(recipeDetails);

                // Log the Recipe and RecipeDetails before saving
                logger.debug("Recipe Before Save: {}", recipe);
                logger.debug("Recipe Details Before Save: {}", recipe.getRecipeDetails());

                // Save the Recipe entity
                recipeRepository.save(recipe);

                return VarList.RSP_SUCCESS;
            }
        }
                Recipe recipe = modelMapper.map(recipeDTO, Recipe.class);

                // Initialize the recipe details set
                Set<RecipeDetail> recipeDetails = new HashSet<>();

                // Handle RecipeDetail entries
  */

    private static final Logger logger = LoggerFactory.getLogger(RecipeService.class);
public String saveRecipe(RecipeDTO recipeDTO) {
    if (recipeRepository.existsByLabDip(recipeDTO.getLabDip())) {
        return VarList.RSP_DUPLICATED;
    } else {
        // Convert RecipeDTO to Recipe
        Recipe recipe = modelMapper.map(recipeDTO, Recipe.class);

        // Initialize the recipe details list
        List<RecipeDetail> recipeDetails = new ArrayList<>();

        // Handle RecipeDetail entries
        for (RecipeDetailDTO detailDTO : recipeDTO.getRecipeDetails()) {
            RecipeDetail detail = new RecipeDetail();
            detail.setQuantityInGrams(detailDTO.getQuantityInGrams());

            // Set Recipe reference
            detail.setRecipe(recipe);

            // Fetch Product entity from the database and set reference
            Product product = productRepository.findById(detailDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            // Log the Product entity
            logger.debug("Fetched Product: {}", product);

            detail.setProduct(product);

            // Add RecipeDetail to the list
            recipeDetails.add(detail);
        }

        // Set the RecipeDetails in the Recipe entity
        recipe.setRecipeDetails(recipeDetails);

        // Log the Recipe and RecipeDetails before saving
        logger.debug("Recipe Before Save: {}", recipe);
        logger.debug("Recipe Details Before Save: {}", recipe.getRecipeDetails());

        // Save the Recipe entity
        recipeRepository.save(recipe);

        return VarList.RSP_SUCCESS;
    }
}



/*
        if (recipeRepository.existsByLabDip(recipeDTO.getLabDip())) {
            return "Recipe already exists with this labDip.";
        } else {
            Recipe recipe = modelMapper.map(recipeDTO, Recipe.class);

            Set<RecipeDetail> updatedDetails = new HashSet<>();
            for (RecipeDetailDTO detailDTO : recipeDTO.getRecipeDetails()) {
                Product product = productRepository.findById(detailDTO.getProductId())
                        .orElseThrow(() -> new RuntimeException("Product not found"));

                RecipeDetail detail = new RecipeDetail();
                detail.setRecipe(recipe);
                detail.setProduct(product);
                detail.setQuantityInGrams(detailDTO.getQuantityInGrams());
                updatedDetails.add(detail);
            }
            recipe.setRecipeDetails(updatedDetails);
            recipeRepository.save(recipe);
            return "Recipe saved successfully.";
        }*/



}



