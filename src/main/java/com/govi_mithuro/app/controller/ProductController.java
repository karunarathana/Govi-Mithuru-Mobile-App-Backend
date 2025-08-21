package com.govi_mithuro.app.controller;


import com.govi_mithuro.app.constant.APIConstants;
import com.govi_mithuro.app.dto.ProductDto;
import com.govi_mithuro.app.model.ProductEntity;
import com.govi_mithuro.app.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(APIConstants.API_ROOT)
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    private final ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = APIConstants.ADD_NEW_PRODUCT, method = RequestMethod.POST)
    public ResponseEntity<?> addNewProduct(@RequestBody ProductDto productDto) {
        logger.info("Request Started In addNewProduct |productDto={} ", productDto);
        String response = productService.addNewProduct(productDto);
        logger.info("Request Complete In addNewProduct |response={} ", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @RequestMapping(value = APIConstants.VIEW_ALL_PRODUCT, method = RequestMethod.GET)
    public ResponseEntity<?> viewAllProduct() {
        logger.info("Request Started In viewAllProduct");
        List<ProductEntity> response = productService.viewAllProduct();
        logger.info("Request Complete In viewAllProduct |response={} ", response);
        return ResponseEntity.ok(Map.of(
                "totalItem","Test",
                "totalPages","Test",
                "items", response
        ));
    }
    @RequestMapping(value = APIConstants.UPDATE_SINGLE_PRODUCT, method = RequestMethod.POST)
    public ResponseEntity<?> updateProductById(@PathVariable int id, @RequestBody ProductDto productDto) {
        logger.info("Request Started In updateProductById |id={} |productDto={}",id,productDto);
        String response = productService.updateProductById(id, productDto);
        logger.info("Request Complete In updateProductById |response={} ", response);
        return ResponseEntity.ok(Map.of(
                "totalItem","Test",
                "totalPages","Test",
                "items", response
        ));
    }
    @RequestMapping(value = APIConstants.DELETE_SINGLE_PRODUCT, method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProductById(@PathVariable int id) {
        logger.info("Request Started In deleteProductById |id={}",id);
        String response = productService.deleteProductById(id);
        logger.info("Request Complete In deleteProductById |response={} ", response);
        return ResponseEntity.ok(Map.of(
                "totalItem","Test",
                "totalPages","Test",
                "items", response
        ));
    }
    @RequestMapping(value = APIConstants.GET_UNIQUE_PRODUCTS, method = RequestMethod.GET)
    public ResponseEntity<?> viewSingleProduct(@PathVariable int id) {
        logger.info("Request Started In viewSingleProduct |id={}",id);
        ProductEntity response = productService.viewSingleProductById(id);
        logger.info("Request Complete In viewSingleProduct |response={} ", response);
        return ResponseEntity.ok(Map.of(
                "totalItem","Test",
                "totalPages","Test",
                "items", response
        ));
    }
    @RequestMapping(value = APIConstants.VIEW_ALL_PRODUCT_BY_CATEGORY, method = RequestMethod.GET)
    public ResponseEntity<?> viewAllProductByCategoryName(@RequestParam("category") String category) {
        logger.info("Request Started In viewAllProductByCategoryName |CategoryName={}",category);
        List<ProductEntity> response = productService.viewAllProductByCategoryName(category);
        logger.info("Request Complete In viewAllProductByCategoryName |response={} ", response);
        return ResponseEntity.ok(Map.of(
                "totalItem","Test",
                "totalPages","Test",
                "items", response
        ));
    }
}
