package com.dormitory.merit.domain.category.controller;

import com.dormitory.merit.domain.category.dto.*;
import com.dormitory.merit.domain.category.service.DeleteService;
import com.dormitory.merit.domain.category.service.RegistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CategoryController {

    private final RegistService registService;
    private final DeleteService deleteService;

    @PostMapping("/regist")
    public ResponseEntity<CategoryRegistRes> regist(@RequestBody CategoryRegistReq request) {
        CategoryRegistRes response = registService.regist(request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<CategoryDelRes> delete(@RequestBody CategoryDelReq request) {
        CategoryDelRes response = deleteService.delete(request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/patch")
    public ResponseEntity<CategoryPatRes> patch(CategoryPatReq request) {
        return null;
    }
}
