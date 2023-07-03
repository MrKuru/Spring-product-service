package com.melh.stockmanagement.productservice.controller;

import com.melh.stockmanagement.productservice.enums.Language;
import com.melh.stockmanagement.productservice.exception.enums.FriendlyMessageCodes;
import com.melh.stockmanagement.productservice.exception.utils.FriendlyMessageUtils;
import com.melh.stockmanagement.productservice.repository.entity.Product;
import com.melh.stockmanagement.productservice.request.ProductCreateRequest;
import com.melh.stockmanagement.productservice.response.FriendlyMessage;
import com.melh.stockmanagement.productservice.response.InternalApiResponse;
import com.melh.stockmanagement.productservice.response.ProductResponse;
import com.melh.stockmanagement.productservice.service.IProductRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/1.0/product")
@RequiredArgsConstructor
public class ProductController {
    private final IProductRepositoryService productRepositoryService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/{language}/create")
    public InternalApiResponse<ProductResponse> createProduct(@PathVariable("language") Language language,
                                                              @RequestBody ProductCreateRequest productCreateRequest){
        log.debug("[{}][createProduct] -> request: {}", this.getClass().getSimpleName(), productCreateRequest);
        Product product = productRepositoryService.createProduct(language, productCreateRequest);
        ProductResponse productResponse = convertProductResponse(product);
        log.debug("[{}][createProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);
        return InternalApiResponse.<ProductResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.PRODUCT_SUCCESSFULLY_CREATED))
                        .build())
                .httpStatus(HttpStatus.CREATED)
                .hasError(false)
                .payload(productResponse)
                .build();
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{language}/get/{Id}")
    public InternalApiResponse<ProductResponse> getProduct(@PathVariable("language") Language language,
                                                           @PathVariable("Id") Long Id){
        log.debug("[{}][getProduct] -> request: {}", this.getClass().getSimpleName(), Id);
        Product product = productRepositoryService.getProduct(language, Id);
        ProductResponse productResponse = convertProductResponse(product);
        log.debug("[{}][getProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);
        return InternalApiResponse.<ProductResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.OK))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.OK))
                        .build())
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponse)
                .build();
    }
    private static ProductResponse convertProductResponse(Product product) {
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .productCreateDate(product.getProductCreatedDate().getTime())
                .productUpdateDate(product.getProductUpdatedDate().getTime())
                .build();
    }
}
