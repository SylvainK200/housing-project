package com.example.tutorials.feature.housing.controller;


import com.example.tutorials.feature.housing.entity.Housing;
import com.example.tutorials.feature.housing.service.HousingService;
import com.example.tutorials.util.entity.response.ListObjectResponse;
import com.example.tutorials.util.entity.response.OperationResponse;
import com.example.tutorials.util.entity.response.SimpleObjectResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.tutorials.util.entity.response.OperationResponse.ResponseStatusEnum.SUCCESS;

@RequiredArgsConstructor
@RestController
@RequestMapping("app")
public class HousingController {

    private final HousingService housingService;


    @GetMapping("/housings")
    public ResponseEntity<ListObjectResponse<Housing>> findAll(){
        return ResponseEntity.ok(new ListObjectResponse<>(SUCCESS, housingService.findAll()));
    }

    @GetMapping("/housings/{id}")
    public ResponseEntity<SimpleObjectResponse<Housing>> findById(@PathVariable Integer id ){
        SimpleObjectResponse<Housing> appUser = new SimpleObjectResponse<>(SUCCESS, housingService.findById(id));
        return ResponseEntity.ok(appUser);
    }

    @PutMapping("/housings/{id}")
    public ResponseEntity<SimpleObjectResponse<Housing>> update (@PathVariable Integer id , @RequestBody Housing appUserUpdate){
        SimpleObjectResponse<Housing> appUser = new SimpleObjectResponse<>(SUCCESS, housingService.update(appUserUpdate, id));
        return ResponseEntity.ok(appUser);
    }

    @PostMapping("/housings")
    public ResponseEntity<SimpleObjectResponse<Housing>> create(@RequestBody Housing appUser){
        SimpleObjectResponse<Housing> createdUser = new SimpleObjectResponse<>(SUCCESS, housingService.create(appUser));
        return ResponseEntity.ok(createdUser);
    }

    @DeleteMapping("/housings/{id}")
    public ResponseEntity<OperationResponse> delete(@PathVariable Integer id){
        housingService.delete(id);
        return ResponseEntity.ok(new OperationResponse(SUCCESS, "deleted"));
    }
}
