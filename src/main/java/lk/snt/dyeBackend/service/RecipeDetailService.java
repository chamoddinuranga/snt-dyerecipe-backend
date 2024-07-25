package lk.snt.dyeBackend.service;


import jakarta.transaction.Transactional;
import lk.snt.dyeBackend.dto.RecipeDetailDTO;
import lk.snt.dyeBackend.entity.Product;
import lk.snt.dyeBackend.entity.Recipe;
import lk.snt.dyeBackend.entity.RecipeDetail;
import lk.snt.dyeBackend.repo.ProductRepository;
import lk.snt.dyeBackend.repo.RecipeDetailRepository;
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
public class RecipeDetailService {

    @Autowired
    private RecipeDetailRepository recipeDetailRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;


    public String saveRecipeDetail(RecipeDetailDTO recipeDetailDTO) {

        if (recipeDetailRepository.existsById(recipeDetailDTO.getRecipeDetailId())) {
            return VarList.RSP_DUPLICATED;
        } else {
            recipeDetailRepository.save(modelMapper.map(recipeDetailDTO, RecipeDetail.class));
            return VarList.RSP_SUCCESS;
        }
    }


}
