package com.govi_mithuro.app.services.impl;

import com.govi_mithuro.app.dto.ProductDto;
import com.govi_mithuro.app.model.ProductEntity;
import com.govi_mithuro.app.repo.ProductRepo;
import com.govi_mithuro.app.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final ProductRepo productRepo;
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    public ProductServiceImpl(RedisTemplate<String, Object> redisTemplate, ProductRepo productRepo) {
        this.redisTemplate = redisTemplate;
        this.productRepo = productRepo;
    }

    @Override
    public String addNewProduct(ProductDto productDto) {
        logger.info("Method Executing Start In addNewProduct |productDto={}", productDto);
        ProductEntity savedResponse = productRepo.save(convertProductToProductEntity(0, productDto, "Create"));
        if (savedResponse != null && savedResponse.getProductID() != null) {
            redisTemplate.opsForValue().set(String.valueOf(savedResponse.getProductID()), productDto);
            logger.info("Product saved successfully: " + savedResponse.getProductID());
            return "Product saved successfully";
        }
        logger.info("Method Executing Completed In addNewProduct savedResponse={}", savedResponse);
        return "Failed to save product: " + savedResponse.getProductID();
    }

    private ProductEntity convertProductToProductEntity(int updateId, ProductDto productDto, String methodName) {
        logger.info("Method Executing Start In convertProductToProductEntity |productDto={}", productDto);
        ProductEntity productEntity = new ProductEntity();
        if (methodName.equals("Create")) {
            productEntity.setProductName(productDto.getProductName());
            productEntity.setProductDescription(productDto.getProductDescription());
            productEntity.setProductPrice(productDto.getProductPrice());
            productEntity.setDiscountPrice(productDto.getDiscountPrice());
            productEntity.setLastPrice(productDto.getLastPrice()); // productPrice - discountPrice
            productEntity.setBrandName(productDto.getBrandName());
            productEntity.setProductCreateData(new Date(System.currentTimeMillis()));
            productEntity.setProductCategory(productDto.getProductCategory());
            productEntity.setProductColor(productDto.getProductColor());
            productEntity.setOthers(productDto.getOthers());
            productEntity.setProductStock(productDto.getProductStock());
            productEntity.setWarrantyPeriod(productDto.getWarrantyPeriod().concat("Year"));
        } else if (methodName.equals("Update")) {
            productEntity.setProductName(productDto.getProductName());
            productEntity.setProductDescription(productDto.getProductDescription());
            productEntity.setProductPrice(productDto.getProductPrice());
            productEntity.setDiscountPrice(productDto.getDiscountPrice());
            productEntity.setLastPrice(productDto.getLastPrice()); // productPrice - discountPrice
            productEntity.setBrandName(productDto.getBrandName());
            productEntity.setProductCreateData(new Date(System.currentTimeMillis()));
            productEntity.setProductCategory(productDto.getProductCategory());
            productEntity.setProductColor(productDto.getProductColor());
            productEntity.setOthers(productDto.getOthers());
            productEntity.setProductStock(productDto.getProductStock());
            productEntity.setWarrantyPeriod(productDto.getWarrantyPeriod().concat("Year"));
        } else {
            logger.info("Invalid method name");
        }

        logger.info("Method Executing Completed In convertProductToProductEntity |productEntity={}", productEntity);
        return productEntity;

    }

    @Override
    public String updateProductById(int id, ProductDto productDto) {
        logger.info("Method Executing Start In updateProductById |id={} |productDto={}", id, productDto);
        ProductEntity productEntity = convertProductToProductEntity(id, productDto, "Update");
        ProductEntity updateProduct = productRepo.save(productEntity);
        logger.info("Method Executing Completed In updateProductById |response={}", updateProduct);
        return "Update Successfully";
    }

    @Override
    public String deleteProductById(int id) {
        logger.info("Method Executing Start In deleteProductById |id={}", id);
        productRepo.deleteById(id);
        logger.info("Method Executing Completed In deleteProductById |response={}", "Delete Successfully");
        return "Delete Successfully";
    }

    @Override
    public List<ProductEntity> viewAllProduct() {
        logger.info("Method Executing Start In viewAllProduct");
        List<ProductEntity> all = productRepo.findAll();
        logger.info("Method Executing Start In deleteProductById |response={}", "Delete Successfully");
        return all;
    }

    @Override
    public ProductEntity viewSingleProductById(int id) {
        logger.info("Method Executing Start In viewSingleProductById id={}", id);
        Optional<ProductEntity> byproductId = productRepo.findByproductId(id);
        logger.info("Method Executing Completed In viewSingleProductById product={}", byproductId.get());
        return byproductId.get();
    }

    @Override
    public List<ProductEntity> viewAllProductByCategoryName(String categoryName) {
        logger.info("Method Executing Start In viewAllProductByCategoryName categoryName={}", categoryName);
        List<ProductEntity> productsByCategory = productRepo.findByProductCategory(categoryName);
        logger.info("Method Executing Completed In viewAllProductByCategoryName products={}", productsByCategory);
        if (productsByCategory != null && !productsByCategory.isEmpty()) {
            return productsByCategory;
        }
        logger.info("No products found for category: {}", categoryName);
        return List.of();
    }
}
