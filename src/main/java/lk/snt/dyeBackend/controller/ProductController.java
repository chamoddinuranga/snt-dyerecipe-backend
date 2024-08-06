package lk.snt.dyeBackend.controller;

import lk.snt.dyeBackend.dto.ProductDTO;
import lk.snt.dyeBackend.dto.ResponseDTO;
import lk.snt.dyeBackend.service.ProductService;
import lk.snt.dyeBackend.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping(value = "/saveProduct")
    public ResponseEntity saveProduct(@RequestBody ProductDTO productDTO) {
        try {
            String res = productService.saveProduct(productDTO);
            if (res.equals("00")) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(productDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else if (res.equals("06")) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Product Already Exists");
                responseDTO.setContent(productDTO);
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

    @PutMapping(value = "/updateProduct")
    public ResponseEntity<ResponseDTO> updateProduct(@RequestBody ProductDTO productDTO) {
        try {
            String res = productService.updateProduct(productDTO);
            if (res.equals(VarList.RSP_SUCCESS)) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(productDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else if (res.equals(VarList.RSP_NO_DATA_FOUND)) {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Not a registered product");
                responseDTO.setContent(productDTO);
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

    @GetMapping("/getAllProducts")
    public ResponseEntity<ResponseDTO> getAllProducts() {
        try {
            List<ProductDTO> productDTOList = productService.getAllProducts();
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Success");
            responseDTO.setContent(productDTOList);
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getProductByProductId/{productId}")
    public ResponseEntity<ResponseDTO> searchProductByProductId(@PathVariable long productId) {
        ProductDTO productDTO = productService.searchProductByProductId(productId);
        try {
            if (productDTO != null) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(productDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Product available for this productId");
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

    @GetMapping("/getProductByProductName/{productName}")
    public ResponseEntity<ResponseDTO> searchProductByProductName(@PathVariable String productName) {
        System.out.println("productName : " + productName);
        ProductDTO productDTO = productService.searchProductByProductName(productName);
        try {
            if (productDTO != null) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(productDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Product available for this productName");
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

    @DeleteMapping("/deleteProductByProductName/{productName}")
    public ResponseEntity<ResponseDTO> deleteProductByProductName(@PathVariable String productName) {
        String res = productService.deleteProductByProductName(productName);
        try {
            if (res.equals(VarList.RSP_SUCCESS)) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Product Deleted");
                responseDTO.setContent(productName);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Product available for this productName");
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

    @GetMapping("/searchProducts")
    public ResponseEntity<ResponseDTO> searchProducts(@RequestParam String query) {
        try {
            List<ProductDTO> products = productService.searchProducts(query);
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Success");
            responseDTO.setContent(products);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception ex) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/products/search")
//    public ResponseEntity<List<ProductDTO>> searchProducts(@RequestParam("query") String query) {
//        List<ProductDTO> products = productService.searchProducts(query);
//        return ResponseEntity.ok(products);
//    }

    @GetMapping("/getProductType")
    public ResponseEntity<ResponseDTO> getProductType(@RequestParam String productName) {
        try {
            ProductDTO productDTO = productService.searchProductByProductName(productName);

            ResponseDTO responseDTO = new ResponseDTO();
            if (productDTO != null) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Product found");
                responseDTO.setContent(productDTO); // Ensure productDTO contains productType
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Product not found");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
