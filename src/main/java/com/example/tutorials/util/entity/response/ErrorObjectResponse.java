package com.example.tutorials.util.entity.response;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ErrorObjectResponse extends OperationResponse {

    public ErrorObjectResponse(ResponseStatusEnum operationStatus) {
        this(operationStatus, null);
    }

    public ErrorObjectResponse(ResponseStatusEnum operationStatus, Map<String, String> errors) {
        super(operationStatus, null);
        this.errors = errors;
    }

    private int status;
    private Map<String, String> errors = new HashMap<>();
}
