package com.example.tutorials.authentification.controller;

import com.example.tutorials.authentification.entity.AppUser;
import com.example.tutorials.authentification.service.AppUserService;
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
public class AppUserController {

    private final AppUserService appUserService;


    @GetMapping("/app-users")
    public ResponseEntity<ListObjectResponse<AppUser>> findAll(){
        return ResponseEntity.ok(new ListObjectResponse<>(SUCCESS, appUserService.findAll()));
    }

    @GetMapping("/app-users/{id}")
    public ResponseEntity<SimpleObjectResponse<AppUser>> findById(@PathVariable Integer id ){
        SimpleObjectResponse<AppUser> appUser = new SimpleObjectResponse<>(SUCCESS, appUserService.findById(id));
        return ResponseEntity.ok(appUser);
    }

    @PutMapping("/app-users/{id}")
    public ResponseEntity<SimpleObjectResponse<AppUser>> update (@PathVariable Integer id , @RequestBody AppUser appUserUpdate){
        SimpleObjectResponse<AppUser> appUser = new SimpleObjectResponse<>(SUCCESS, appUserService.update(appUserUpdate, id));
        return ResponseEntity.ok(appUser);
    }

    @PostMapping("/app-users")
    public ResponseEntity<SimpleObjectResponse<AppUser>> create(@RequestBody AppUser appUser){
        SimpleObjectResponse<AppUser> createdUser = new SimpleObjectResponse<>(SUCCESS, appUserService.create(appUser));
        return ResponseEntity.ok(createdUser);
    }

    @DeleteMapping("/app-users/{id}")
    public ResponseEntity<OperationResponse> delete(@PathVariable Integer id){
        appUserService.delete(id);
        return ResponseEntity.ok(new OperationResponse(SUCCESS, "deleted"));
    }
}
