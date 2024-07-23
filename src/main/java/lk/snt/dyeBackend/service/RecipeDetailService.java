package lk.snt.dyeBackend.service;


import jakarta.transaction.Transactional;
import lk.snt.dyeBackend.dto.RecipeDetailDTO;
import lk.snt.dyeBackend.entity.RecipeDetail;
import lk.snt.dyeBackend.repo.RecipeDetailRepository;
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
public class RecipeDetailService {

    @Autowired
    private RecipeDetailRepository recipeDetailRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Add new RecipeDetail to the database
    public String saveRecipeDetail(RecipeDetailDTO recipeDetailDTO) {

        if (recipeDetailRepository.existsById(recipeDetailDTO.getRecipeDetailId())) {
            return VarList.RSP_DUPLICATED;
        } else {
            recipeDetailRepository.save(modelMapper.map(recipeDetailDTO, RecipeDetail.class));
            return VarList.RSP_SUCCESS;
        }
    }

    // Update an existing RecipeDetail
    public String updateRecipeDetail(RecipeDetailDTO recipeDetailDTO) {
        if (recipeDetailRepository.existsById(recipeDetailDTO.getRecipeDetailId())){
            recipeDetailRepository.save(modelMapper.map(recipeDetailDTO, RecipeDetail.class));
            return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    // Load all RecipeDetails to the console
    public List<RecipeDetailDTO> getAllRecipeDetails() {
        List<RecipeDetail> recipeDetailList = recipeDetailRepository.findAll();
        return modelMapper.map(recipeDetailList, new TypeToken<ArrayList<RecipeDetailDTO>>(){}.getType());
    }

    // Search RecipeDetail By ID
    public RecipeDetailDTO searchRecipeDetailById(long id) {
        if (recipeDetailRepository.existsById(id)) {
            RecipeDetail recipeDetail = recipeDetailRepository.findById(id).orElse(null);
            return modelMapper.map(recipeDetail, RecipeDetailDTO.class);
        } else {
            return null;
        }
    }

    // Delete RecipeDetail by ID
    public String deleteRecipeDetailById(long id) {
        if (recipeDetailRepository.existsById(id)) {
            recipeDetailRepository.deleteById(id);
            return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
}
