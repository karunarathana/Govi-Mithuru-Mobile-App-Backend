package com.govi_mithuro.app.services;



import com.govi_mithuro.app.dto.ProductDto;
import com.govi_mithuro.app.model.ProductEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    String addNewProduct(MultipartFile files, ProductDto productDto) throws IOException;
    String updateProductById(int id,ProductDto productDto,MultipartFile files) throws IOException;
    String deleteProductById(int id);
    List<ProductEntity> viewAllProduct();
    ProductEntity viewSingleProductById(int id);
    List<ProductEntity> viewAllProductByCategoryName(String categoryName);
}
