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
import java.util.stream.Collectors;


import org.slf4j.Logger;

@Service
@Transactional
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeDetailRepository recipeDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;



    private static final Logger logger = LoggerFactory.getLogger(RecipeService.class);

/*

// Update Recipe
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

 */
 //Get all recipes
    public List<RecipeDTO> getAllRecipes() {
        List<Recipe> recipeList = recipeRepository.findAll();
        return modelMapper.map(recipeList, new TypeToken<ArrayList<RecipeDTO>>(){}.getType());
    }

    //Search Recipe By labdip
    public RecipeDTO searchRecipeByLabDip(String labDip) {
        if (recipeRepository.existsByLabDip(labDip)) {
            Optional<Recipe> recipeOpt = recipeRepository.findByLabDip(labDip);
            return recipeOpt.map(recipe -> modelMapper.map(recipe, RecipeDTO.class)).orElse(null);
        } else {
            return null;
        }
    }

    //Search Recipe By recipeId
    public RecipeDTO searchRecipeByRecipeId(long recipeId) {
        if (recipeRepository.existsById(recipeId)) {
            Optional<Recipe> recipeOpt = recipeRepository.findById(recipeId);
            return recipeOpt.map(recipe -> modelMapper.map(recipe, RecipeDTO.class)).orElse(null);
        } else {
            return null;
        }
    }

    //Delete Recipe By recipeId
    // Delete User
    public String deleteRecipeById(long recipeId) {
        if (recipeRepository.existsById(recipeId)) {
            recipeRepository.deleteById(recipeId);
            return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

//    public String saveRecipe(RecipeDTO recipeDTO) {
//        System.out.println(recipeDTO);
//
//        if (recipeRepository.existsByLabDip(recipeDTO.getLabDip())) {
//            return VarList.RSP_DUPLICATED;
//        } else {
//            // Convert RecipeDTO to Recipe
//            Recipe recipe = modelMapper.map(recipeDTO, Recipe.class);
//            System.out.println(Recipe.class);
//
//            // Initialize the recipe details list
//            List<RecipeDetail> recipeDetails = new ArrayList<>();
//
//            // Handle RecipeDetail entries
//            for (RecipeDetailDTO detailDTO : recipeDTO.getRecipeDetails()) {
//                RecipeDetail detail = new RecipeDetail();
//                detail.setQuantityInGrams(detailDTO.getQuantityInGrams());
//                detail.setAddFunction(detailDTO.getAddFunction()); // Set new fields
//                detail.setDose(detailDTO.getDose());
//                detail.setTemp(detailDTO.getTemp());
//
//                // Set Recipe reference
//                detail.setRecipe(recipe);
//
//                // Fetch Product entity from the database and set reference
////                Product product = productRepository.findById(detailDTO.getProductId())
////                        .orElseThrow(() -> new RuntimeException("Product not found"));
//
//                Product product = productRepository.findById(detailDTO.getProductId())
//                        .orElseThrow(() -> new RuntimeException("Product not found"));
//
//                // Log the Product entity
//                logger.debug("Fetched Product: {}", product);
//
//                detail.setProduct(product);
//
//                // Add RecipeDetail to the list
//                recipeDetails.add(detail);
//            }
//
//            // Set the RecipeDetails in the Recipe entity
//            recipe.setRecipeDetails(recipeDetails);
//
//            // Log the Recipe and RecipeDetails before saving
//            logger.debug("Recipe Before Save: {}", recipe);
//            logger.debug("Recipe Details Before Save: {}", recipe.getRecipeDetails());
//
//            // Save the Recipe entity
//            recipeRepository.save(recipe);
//
//            return VarList.RSP_SUCCESS;
//        }
//    }

    public String saveRecipe(RecipeDTO recipeDTO) {
        System.out.println(recipeDTO);

        if (recipeRepository.existsByLabDip(recipeDTO.getLabDip())) {
            return VarList.RSP_DUPLICATED;
        } else {
            // Convert RecipeDTO to Recipe
            Recipe recipe = modelMapper.map(recipeDTO, Recipe.class);
            System.out.println(Recipe.class);

            // Initialize the recipe details list
            List<RecipeDetail> recipeDetails = new ArrayList<>();

            // Handle RecipeDetail entries
            for (RecipeDetailDTO detailDTO : recipeDTO.getRecipeDetails()) {
                RecipeDetail detail = new RecipeDetail();
                detail.setQuantityInGrams(detailDTO.getQuantityInGrams());
                detail.setAddFunction(detailDTO.getAddFunction()); // Set new fields
                detail.setDose(detailDTO.getDose());
                detail.setTemp(detailDTO.getTemp());

                // Set Recipe reference
                detail.setRecipe(recipe);

                // Fetch Product entity from the database and set reference
//                Product product = productRepository.findById(detailDTO.getProductId())
//                        .orElseThrow(() -> new RuntimeException("Product not found"));

                Product product = productRepository.findByProductName(detailDTO.getProductName())
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
                detail.setAddFunction(detailDTO.getAddFunction()); // Set new fields
                detail.setDose(detailDTO.getDose());
                detail.setTemp(detailDTO.getTemp());

                // Fetch Product entity from the database
                Product product = productRepository.findByProductName(detailDTO.getProductName())
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




    public List<Recipe> searchRecipesByLabDip(String query) {
        return recipeRepository.findByLabDipContaining(query);
    }

    public List<String> searchLabDips(String query) {
        List<Recipe> recipes = recipeRepository.findByLabDipContaining(query);
        return recipes.stream()
                .map(Recipe::getLabDip)
                .distinct() // To avoid duplicates
                .collect(Collectors.toList());
    }

}



