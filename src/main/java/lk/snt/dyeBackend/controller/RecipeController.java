package lk.snt.dyeBackend.controller;

import lk.snt.dyeBackend.dto.RecipeDTO;
import lk.snt.dyeBackend.dto.ResponseDTO;
import lk.snt.dyeBackend.service.RecipeService;
import lk.snt.dyeBackend.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/recipe")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping(value = "/saveRecipe")
    public ResponseEntity saveRecipe(@RequestBody RecipeDTO recipeDTO) {
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
/*
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

    @GetMapping("/getRecipeByRecipeId/{recipeId}")
    public ResponseEntity<ResponseDTO> searchRecipeByRecipeId(@PathVariable long recipeId) {
        RecipeDTO recipeDTO = recipeService.searchRecipeByRecipeId(recipeId);
        try {
            if (recipeDTO != null) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(recipeDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Recipe available for this recipeId");
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

    //Search By GRN Number

    *//*@GetMapping("/getRecipeByGRN/{grnNumber}")
    public ResponseEntity<ResponseDTO> SearchRecipeByGRN(@PathVariable int grnNumber) {
        RecipeDTO recipeDTO = recipeService.searchRecipeByGrnNumber(grnNumber);
        try {
            if (recipeDTO != null) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(recipeDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Recipe available for this recipeId");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(ex);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*//*

    @DeleteMapping("/deleteRecipeByRecipeName/{recipeName}")
    public ResponseEntity<ResponseDTO> deleteRecipeByRecipeName(@PathVariable String recipeName) {
        String res = recipeService.deleteRecipeByRecipeName(recipeName);
        try {
            if (res.equals(VarList.RSP_SUCCESS)) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Recipe Deleted");
                responseDTO.setContent(recipeName);
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
    }*/
}
