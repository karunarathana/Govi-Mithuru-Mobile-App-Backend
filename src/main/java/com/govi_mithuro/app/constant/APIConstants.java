package com.govi_mithuro.app.constant;

public class APIConstants {

    // User api list

    public static final String API_ROOT = "api/com-test";
    public static final String USER_LOGIN = "/login-user";
    public static final String CREATE_USER = "/create-user";
    public static final String DELETE_USER = "/delete-user";
    public static final String USER_SEARCH_ALL = "/search-all-users";
    public static final String REFRESH_ACCESS_TOKEN = "/auth/refresh-token";
    public static final String GENERATE_NEW_ACCESS_TOKEN = "/auth/new-access-token";
    public static final String FORGOT_PASSWORD = "/auth/forgot";

    // Product api list

    public static final String ADD_NEW_PRODUCT = "/add-product";
    public static final String VIEW_ALL_PRODUCT = "/view-product";
    public static final String DELETE_SINGLE_PRODUCT = "/delete-product";
    public static final String UPDATE_SINGLE_PRODUCT = "/update-product";
    public static final String GET_UNIQUE_PRODUCTS = "/get-product-by-category";
    public static final String VIEW_ALL_PRODUCT_BY_CATEGORY = "/view-product-by-category";
}
