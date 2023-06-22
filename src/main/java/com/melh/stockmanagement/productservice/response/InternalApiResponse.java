package com.melh.stockmanagement.productservice.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class InternalApiResponse <T> {
    private FriendlyMessage friendlyMessage;
    private HttpStatus httpStatus;
    private List<String> errorMessages;
    private boolean hasError;
    private T payload;
}
