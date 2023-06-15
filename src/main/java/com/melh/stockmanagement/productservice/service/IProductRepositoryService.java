package com.melh.stockmanagement.productservice.service;

import com.melh.stockmanagement.productservice.enums.Language;
import com.melh.stockmanagement.productservice.repository.entity.Product;
import com.melh.stockmanagement.productservice.request.ProductCreateRequest;
import com.melh.stockmanagement.productservice.request.ProductUpdateRequest;

import java.util.List;

public interface IProductRepositoryService {
    Product createProduct(Language language, ProductCreateRequest createRequest);
    Product getProduct(Language language, Long Id);
    List<Product> getProducts(Language language);
    Product updateProduct(Language language, ProductUpdateRequest updateRequest);
    void deleteProduct(Language language, Long Id);
}
