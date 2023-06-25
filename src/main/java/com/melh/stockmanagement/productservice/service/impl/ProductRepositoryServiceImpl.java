package com.melh.stockmanagement.productservice.service.impl;

import com.melh.stockmanagement.productservice.enums.Language;
import com.melh.stockmanagement.productservice.exception.enums.FriendlyMessageCodes;
import com.melh.stockmanagement.productservice.exception.exceptions.ProductNotCreatedException;
import com.melh.stockmanagement.productservice.repository.ProductRepository;
import com.melh.stockmanagement.productservice.repository.entity.Product;
import com.melh.stockmanagement.productservice.request.ProductCreateRequest;
import com.melh.stockmanagement.productservice.request.ProductUpdateRequest;
import com.melh.stockmanagement.productservice.service.IProductRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductRepositoryServiceImpl implements IProductRepositoryService {
    private final ProductRepository productRepository;
    @Override
    public Product createProduct(Language language, ProductCreateRequest createRequest) {
        log.debug("[{}][createProduct] -> request: {}", this.getClass().getSimpleName(), createRequest);
        try {
            Product product = Product.builder()
                    .productName(createRequest.getProductName())
                    .quantity(createRequest.getQuantity())
                    .price(createRequest.getPrice())
                    .deleted(false)
                    .build();
            Product productResponse = productRepository.save(product);
            log.debug("[{}][createProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);
            return productResponse;
        } catch (Exception exception) {
            throw new ProductNotCreatedException(language, FriendlyMessageCodes.PRODUCT_NOT_CREATED_EXCEPTION, "product request: " + createRequest.toString());
        }
    }

    @Override
    public Product getProduct(Language language, Long Id) {
        return null;
    }

    @Override
    public List<Product> getProducts(Language language) {
        return null;
    }

    @Override
    public Product updateProduct(Language language, ProductUpdateRequest updateRequest) {
        return null;
    }

    @Override
    public void deleteProduct(Language language, Long Id) {

    }
}
