package com.govi_mithuro.app.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "t_product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productID;
    private String productName;
    private String productPrice;
    private Date productCreateData;
    private String productCategory;
    private String others;
    @Lob
    @Column(name = "product_image_data", columnDefinition = "LONGBLOB")
    private byte[] placeImageData;

    public byte[] getPlaceImageData() {
        return placeImageData;
    }

    public void setPlaceImageData(byte[] placeImageData) {
        this.placeImageData = placeImageData;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getProductCreateData() {
        return productCreateData;
    }

    public void setProductCreateData(Date productCreateData) {
        this.productCreateData = productCreateData;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getOthers() {
        return others;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public void setOthers(String others) {
        this.others = others;
    }

}
