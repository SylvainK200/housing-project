package com.example.tutorials.util.entity.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class OperationResponse {


    public OperationResponse(ResponseStatusEnum operationStatus, String operationMessage) {
        this.operationStatus = operationStatus;
        this.operationMessage = operationMessage;
    }

    public enum ResponseStatusEnum {
        SUCCESS, ERROR, WARNING, NO_ACCESS
    }

    private ResponseStatusEnum operationStatus;

    private String operationMessage;
}
