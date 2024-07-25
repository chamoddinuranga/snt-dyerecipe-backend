package lk.snt.dyeBackend.service;

import jakarta.transaction.Transactional;
import lk.snt.dyeBackend.dto.ProductDTO;
import lk.snt.dyeBackend.dto.RecipeDTO;
import lk.snt.dyeBackend.dto.RecipeDetailDTO;
import lk.snt.dyeBackend.entity.Product;
import lk.snt.dyeBackend.entity.Recipe;
import lk.snt.dyeBackend.entity.RecipeDetail;
import lk.snt.dyeBackend.repo.ProductRepository;
import lk.snt.dyeBackend.repo.RecipeDetailRepository;
import lk.snt.dyeBackend.repo.RecipeRepository;
import lk.snt.dyeBackend.repo.UserRepository;
import lk.snt.dyeBackend.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


import org.slf4j.Logger;

@Service
@Transactional
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeDetailRepository recipeDetailRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;



    private static final Logger logger = LoggerFactory.getLogger(RecipeService.class);

    //Save Recipe
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
   /* public String updateRecipe(RecipeDTO recipeDTO) {
        if (recipeRepository.existsById(recipeDTO.getRecipeId())) {
            // Convert RecipeDTO to Recipe
            Recipe recipe = modelMapper.map(recipeDTO, Recipe.class);

            // Clear existing RecipeDetails and map new ones
            recipe.getRecipeDetails().clear();
            for (RecipeDetailDTO detailDTO : recipeDTO.getRecipeDetails()) {
                RecipeDetail detail = new RecipeDetail();
                detail.setQuantityInGrams(detailDTO.getQuantityInGrams());

                // Fetch Product entity from the database
                Product product = productRepository.findById(detailDTO.getProductId())
                        .orElseThrow(() -> new RuntimeException("Product not found"));

                detail.setProduct(product);
                detail.setRecipe(recipe);

                recipe.getRecipeDetails().add(detail);
            }

            // Save the updated recipe
            recipeRepository.save(recipe);
            return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }*/

    /*public String updateRecipe(RecipeDTO recipeDTO) {
        if (recipeRepository.existsById(recipeDTO.getRecipeId())) {
            // Fetch the existing recipe from the database
            Recipe existingRecipe = recipeRepository.findById(recipeDTO.getRecipeId())
                    .orElseThrow(() -> new RuntimeException("Recipe not found"));

            // Map the updated fields from recipeDTO to existingRecipe
            modelMapper.map(recipeDTO, existingRecipe);

            // Clear existing RecipeDetails and set new ones
            existingRecipe.getRecipeDetails().clear();

            // Handle RecipeDetail entries
            for (RecipeDetailDTO detailDTO : recipeDTO.getRecipeDetails()) {
                RecipeDetail detail = new RecipeDetail();
                detail.setQuantityInGrams(detailDTO.getQuantityInGrams());

                // Fetch Product entity from the database
                Product product = productRepository.findById(detailDTO.getProductId())
                        .orElseThrow(() -> new RuntimeException("Product not found"));

                detail.setProduct(product);
                detail.setRecipe(existingRecipe);

                existingRecipe.getRecipeDetails().add(detail);
            }

            // Save the updated recipe
            recipeRepository.save(existingRecipe);
            return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }*/

    public String updateRecipe(RecipeDTO recipeDTO) {
        if (recipeRepository.existsById(recipeDTO.getRecipeId())) {
            // Fetch the existing recipe from the database
            Recipe existingRecipe = recipeRepository.findById(recipeDTO.getRecipeId())
                    .orElseThrow(() -> new RuntimeException("Recipe not found"));

            // Update the main Recipe fields
            existingRecipe.setColor(recipeDTO.getColor());
            existingRecipe.setLabDip(recipeDTO.getLabDip());
            existingRecipe.setRoleCount(recipeDTO.getRoleCount());
            existingRecipe.setWeight(recipeDTO.getWeight());
            existingRecipe.setLiquorRatio(recipeDTO.getLiquorRatio());
            existingRecipe.setVolume(recipeDTO.getVolume());
            existingRecipe.setCreatedUser(recipeDTO.getCreatedUser());
            existingRecipe.setCreatedDateTime(recipeDTO.getCreatedDateTime());

            // Handle RecipeDetail entries
            List<RecipeDetail> updatedDetails = new ArrayList<>();
            for (RecipeDetailDTO detailDTO : recipeDTO.getRecipeDetails()) {
                RecipeDetail detail;
                if (detailDTO.getRecipeDetailId() != null && recipeDetailRepository.existsById(detailDTO.getRecipeDetailId())) {
                    // Fetch the existing RecipeDetail from the database
                    detail = recipeDetailRepository.findById(detailDTO.getRecipeDetailId())
                            .orElseThrow(() -> new RuntimeException("RecipeDetail not found"));
                } else {
                    // Create a new RecipeDetail if it does not exist
                    detail = new RecipeDetail();
                }

                detail.setQuantityInGrams(detailDTO.getQuantityInGrams());

                // Fetch Product entity from the database
                Product product = productRepository.findById(detailDTO.getProductId())
                        .orElseThrow(() -> new RuntimeException("Product not found"));

                detail.setProduct(product);
                detail.setRecipe(existingRecipe);

                updatedDetails.add(detail);
            }

            // Set the updated RecipeDetails in the Recipe entity
            existingRecipe.setRecipeDetails(updatedDetails);

            // Save the updated recipe
            recipeRepository.save(existingRecipe);
            return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public List<RecipeDTO> getAllRecipes() {
        List<Recipe> recipeList = recipeRepository.findAll();
        return modelMapper.map(recipeList, new TypeToken<ArrayList<RecipeDTO>>(){}.getType());
    }

   /* public List<RecipeDTO> getAllRecipes() {
        // Fetch all Recipe entities from the database
        List<Recipe> recipeList = recipeRepository.findAll();

        // Log the fetched Recipe entities
        logger.debug("Fetched Recipes: {}", recipeList);

        // Convert Recipe entities to RecipeDTOs
        List<RecipeDTO> recipeDTOList = modelMapper.map(recipeList, new TypeToken<ArrayList<RecipeDTO>>() {
        }.getType());

        // Log the converted RecipeDTOs
        logger.debug("Converted RecipeDTOs: {}", recipeDTOList);

        return recipeDTOList;
    }*/
}



