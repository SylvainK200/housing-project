package com.example.tutorials.util.entity.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ListObjectResponse <T> extends OperationResponse{
    public ListObjectResponse(ResponseStatusEnum statusEnum, String operationMessage){
        super(statusEnum, operationMessage);
    }
    public ListObjectResponse(ResponseStatusEnum operationStatus, List<T> items){
        super(operationStatus, null);
        this.items = items;
    }

    private List<T> items;
}
