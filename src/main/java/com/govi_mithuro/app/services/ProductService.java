package com.govi_mithuro.app.services;



import com.govi_mithuro.app.dto.ProductDto;
import com.govi_mithuro.app.model.ProductEntity;

import java.util.List;

public interface ProductService {
    String addNewProduct(ProductDto productDto);
    String updateProductById(int id,ProductDto productDto);
    String deleteProductById(int id);
    List<ProductEntity> viewAllProduct();
    ProductEntity viewSingleProductById(int id);
    List<ProductEntity> viewAllProductByCategoryName(String categoryName);
}
