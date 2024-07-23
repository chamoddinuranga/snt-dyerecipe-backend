package lk.snt.dyeBackend.service;

import jakarta.transaction.Transactional;
import lk.snt.dyeBackend.dto.RecipeDTO;
import lk.snt.dyeBackend.entity.Recipe;
import lk.snt.dyeBackend.repo.RecipeRepository;
import lk.snt.dyeBackend.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Add new Recipe to the database
    public String saveRecipe(RecipeDTO recipeDTO) {
        /*if (recipeRepository.existsById(recipeDTO.getRecipeId())) {
            return VarList.RSP_DUPLICATED;
        } else*/ /*if (recipeRepository.existsByLabDip(recipeDTO.getLabDip())) {
            return VarList.RSP_DUPLICATED;
        } else */if (recipeRepository.existsByGrnNumber(recipeDTO.getGrnNumber())) {
            return VarList.RSP_DUPLICATED;
        } else {
            recipeRepository.save(modelMapper.map(recipeDTO, Recipe.class));
            return VarList.RSP_SUCCESS;
        }
    }

    // Update an existing recipe
    public String updateRecipe(RecipeDTO recipeDTO) {
        if (recipeRepository.existsById(recipeDTO.getRecipeId())) {
            recipeRepository.save(modelMapper.map(recipeDTO, Recipe.class));
            return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    // Load all Recipes to the console
    public List<RecipeDTO> getAllRecipes() {
        List<Recipe> recipeList = recipeRepository.findAll();
        return modelMapper.map(recipeList, new TypeToken<ArrayList<RecipeDTO>>(){}.getType());
    }

    // Search Recipe By recipeId
    public RecipeDTO searchRecipeByRecipeId(long recipeId) {
        if (recipeRepository.existsById(recipeId)) {
            Recipe recipe = recipeRepository.findById(recipeId).orElse(null);
            return modelMapper.map(recipe, RecipeDTO.class);
        } else {
            return null;
        }
    }

    // Search Recipe By recipeName
    public RecipeDTO searchRecipeByLabDip(String labDip) {
        if (recipeRepository.existsByLabDip(labDip)) {
            Optional<Recipe> recipeOpt = recipeRepository.findByLabDip(labDip);
            return recipeOpt.map(recipe -> modelMapper.map(recipe, RecipeDTO.class)).orElse(null);
        } else {
            return null;
        }
    }

    // New method to search by grnNumber
    public RecipeDTO searchRecipeByGrnNumber(int grnNumber) {
        if (recipeRepository.existsByGrnNumber(grnNumber)) {
            Optional<Recipe> recipeOpt = recipeRepository.findByGrnNumber(grnNumber);
            return recipeOpt.map(recipe -> modelMapper.map(recipe, RecipeDTO.class)).orElse(null);
        } else {
            return null;
        }
    }


    // Delete Recipe
    public String deleteRecipeByRecipeName(String recipeName) {
        if (recipeRepository.existsByLabDip(recipeName)) {
            recipeRepository.deleteByLabDip(recipeName);
            return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
}
