package lk.snt.dyeBackend.util;

import lk.snt.dyeBackend.dto.ProductDTO;
import lk.snt.dyeBackend.dto.RecipeDetailDTO;
import lk.snt.dyeBackend.entity.Product;
import lk.snt.dyeBackend.entity.RecipeDetail;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Configure mapping between RecipeDetail and RecipeDetailDTO
        modelMapper.addMappings(new PropertyMap<RecipeDetail, RecipeDetailDTO>() {
            @Override
            protected void configure() {
                map().setRecipeId(source.getRecipe().getRecipeId());
                // Add other mappings as needed
            }
        });

        // Configure mapping between Product and ProductDTO
        modelMapper.addMappings(new PropertyMap<Product, ProductDTO>() {
            @Override
            protected void configure() {
                map().setProductId(source.getProductId());
                map().setProductName(source.getProductName());
                map().setProductType(source.getProductType());
                // Add other mappings as needed
            }
        });

        return modelMapper;
    }
}


