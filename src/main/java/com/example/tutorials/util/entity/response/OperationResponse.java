package com.example.tutorials.util.entity.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OperationResponse {
    public OperationResponse() {

    }

    public OperationResponse(ResponseStatusEnum operationStatus, String operationMessage) {
        this.operationStatus = operationStatus;
        this.operationMessage = operationMessage;
    }

    public enum ResponseStatusEnum {
        SUCCESS, ERROR, WARNING, NO_ACCESS
    };

    private ResponseStatusEnum operationStatus;

    private String operationMessage;
}
