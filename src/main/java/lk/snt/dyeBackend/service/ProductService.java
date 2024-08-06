package lk.snt.dyeBackend.service;

import jakarta.transaction.Transactional;
import lk.snt.dyeBackend.dto.ProductDTO;
import lk.snt.dyeBackend.entity.Product;
import lk.snt.dyeBackend.repo.ProductRepository;
import lk.snt.dyeBackend.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Add new Product to the database
    public String saveProduct(ProductDTO productDTO) {

        if (productRepository.existsById(productDTO.getProductId())) {
            return VarList.RSP_DUPLICATED;
        } else if (productRepository.existsByProductName(productDTO.getProductName())) {
            return VarList.RSP_DUPLICATED;
        } else {
            productRepository.save(modelMapper.map(productDTO, Product.class));
            return VarList.RSP_SUCCESS;
        }
    }

    // Update an existing product
    public String updateProduct(ProductDTO productDTO) {
        if (productRepository.existsById(productDTO.getProductId())) {
            productRepository.save(modelMapper.map(productDTO, Product.class));
            return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    // Load all Products to the console
    public List<ProductDTO> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return modelMapper.map(productList, new TypeToken<ArrayList<ProductDTO>>(){}.getType());
    }

    // Search Product By productId
    public ProductDTO searchProductByProductId(long productId) {
        if (productRepository.existsById(productId)) {
            Product product = productRepository.findById(productId).orElse(null);
            return modelMapper.map(product, ProductDTO.class);
        } else {
            return null;
        }
    }

    // Search Product By productName
//    public ProductDTO searchProductByProductName(String productName) {
//        if (productRepository.existsByProductName(productName)) {
//            Optional<Product> productOpt = productRepository.findByProductName(productName);
//            return productOpt.map(product -> modelMapper.map(product, ProductDTO.class)).orElse(null);
//        } else {
//            return null;
//        }
//    }

    // Delete Product
    public String deleteProductByProductName(String productName) {
        if (productRepository.existsByProductName(productName)) {
            productRepository.deleteByProductName(productName);
            return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public List<ProductDTO> searchProducts(String query) {
        // Fetch products from repository based on search query
        List<Product> products = productRepository.findByProductNameContainingIgnoreCase(query);

        // Map list of Product entities to list of ProductDTOs
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    public ProductDTO searchProductByProductName(String productName) {
        System.out.println("Searching for product: " + productName); // Add this line
        if (productRepository.existsByProductName(productName)) {
            Optional<Product> productOpt = productRepository.findByProductName(productName);
            ProductDTO productDTO = productOpt.map(product -> modelMapper.map(product, ProductDTO.class)).orElse(null);
            System.out.println("ProductDTO found: " + productDTO); // Add this line
            return productDTO;
        } else {
            System.out.println("Product not found."); // Add this line
            return null;
        }
    }




}
