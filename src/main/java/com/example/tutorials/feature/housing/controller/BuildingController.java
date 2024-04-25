package com.example.tutorials.feature.housing.controller;

import com.example.tutorials.feature.housing.entity.Building;
import com.example.tutorials.feature.housing.service.BuildingService;
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
public class BuildingController {

    private final BuildingService buildingService;


    @GetMapping("/buildings")
    public ResponseEntity<ListObjectResponse<Building>> findAll(){
        return ResponseEntity.ok(new ListObjectResponse<>(SUCCESS, buildingService.findAll()));
    }

    @GetMapping("/buildings/{id}")
    public ResponseEntity<SimpleObjectResponse<Building>> findById(@PathVariable Integer id ){
        SimpleObjectResponse<Building> appUser = new SimpleObjectResponse<>(SUCCESS, buildingService.findById(id));
        return ResponseEntity.ok(appUser);
    }

    @PutMapping("/buildings/{id}")
    public ResponseEntity<SimpleObjectResponse<Building>> update (@PathVariable Integer id , @RequestBody Building appUserUpdate){
        SimpleObjectResponse<Building> appUser = new SimpleObjectResponse<>(SUCCESS, buildingService.update(appUserUpdate, id));
        return ResponseEntity.ok(appUser);
    }

    @PostMapping("/buildings")
    public ResponseEntity<SimpleObjectResponse<Building>> create(@RequestBody Building appUser){
        SimpleObjectResponse<Building> createdUser = new SimpleObjectResponse<>(SUCCESS, buildingService.create(appUser));
        return ResponseEntity.ok(createdUser);
    }

    @DeleteMapping("/buildings/{id}")
    public ResponseEntity<OperationResponse> delete(@PathVariable Integer id){
        buildingService.delete(id);
        return ResponseEntity.ok(new OperationResponse(SUCCESS, "deleted"));
    }
}
