package lk.snt.dyeBackend.controller;

import lk.snt.dyeBackend.dto.RecipeDTO;
import lk.snt.dyeBackend.dto.ResponseDTO;
import lk.snt.dyeBackend.entity.Recipe;
import lk.snt.dyeBackend.repo.RecipeRepository;
import lk.snt.dyeBackend.service.RecipeService;
import lk.snt.dyeBackend.service.UserService;
import lk.snt.dyeBackend.util.ApiResponse;
import lk.snt.dyeBackend.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/recipe")
@CrossOrigin
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private ResponseDTO responseDTO;
    @Autowired
    private RecipeRepository recipeRepository;

    @PostMapping(value = "/saveRecipe")
    public ResponseEntity<ResponseDTO> saveRecipe(@RequestBody RecipeDTO recipeDTO) {
        System.out.println(recipeDTO.toString());
        try {
            String res = recipeService.saveRecipe(recipeDTO);
            if (res.equals(VarList.RSP_SUCCESS)) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(recipeDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else if (res.equals(VarList.RSP_DUPLICATED)) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Recipe Already Exists");
                responseDTO.setContent(recipeDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllRecipes")
    public ResponseEntity<ResponseDTO> getAllRecipes() {


        try {
            List<RecipeDTO> recipeDTOList = recipeService.getAllRecipes();
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Success");
            responseDTO.setContent(recipeDTOList);
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/updateRecipe")
    public ResponseEntity<ResponseDTO> updateRecipe(@RequestBody RecipeDTO recipeDTO) {
        try {
            String res = recipeService.updateRecipe(recipeDTO);
            if (res.equals(VarList.RSP_SUCCESS)) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(recipeDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else if (res.equals(VarList.RSP_NO_DATA_FOUND)) {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Recipe Found");
                responseDTO.setContent(recipeDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }
    }
    // Search By Recipe by LabDip
    @GetMapping("/getRecipeByLabDip/{labDip}")
    public ResponseEntity<ResponseDTO> searchRecipeByLabDip(@PathVariable String labDip) {
        RecipeDTO recipeDTO = recipeService.searchRecipeByLabDip(labDip);
        try {
            if (recipeDTO != null) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(recipeDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Recipe available for this Lab Dip");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(ex);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteRecipeById/{recipeId}")
    public ResponseEntity<ResponseDTO> deleteRecipeByRecipeName(@PathVariable long recipeId) {
        String res = recipeService.deleteRecipeById(recipeId);
        try {
            if (res.equals(VarList.RSP_SUCCESS)) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Recipe Deleted");
                responseDTO.setContent(recipeId);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Recipe available for this recipeName");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(ex);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/searchLabDips")
    public ResponseEntity<ApiResponse> searchLabDips(@RequestParam("query") String query) {
        try {
            List<Recipe> recipes = recipeRepository.findByLabDipContaining(query);
            List<String> labDips = recipes.stream()
                    .map(Recipe::getLabDip)
                    .distinct() // To avoid duplicates
                    .collect(Collectors.toList());

            ApiResponse responseDTO = new ApiResponse();
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Success");
            responseDTO.setContent(labDips);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception ex) {
            ApiResponse responseDTO = new ApiResponse();
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("Error: " + ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
