package com.example.tutorials.util.entity.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class SimpleObjectResponse<T> extends OperationResponse{
    public SimpleObjectResponse(ResponseStatusEnum operationStatus) {
        super(operationStatus, null);
    }

    public SimpleObjectResponse(ResponseStatusEnum operationStatus, T item) {
        super(operationStatus, null);
        this.item = item;
    }

    private T item;
}
