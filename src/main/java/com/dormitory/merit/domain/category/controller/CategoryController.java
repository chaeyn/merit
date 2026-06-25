package com.dormitory.merit.domain.category.controller;

import com.dormitory.merit.domain.category.dto.*;
import com.dormitory.merit.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/regist")
    public ResponseEntity<CategoryRegistRes> regist(@RequestBody CategoryRegistReq request) {
        CategoryRegistRes response = categoryService.regist(request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<CategoryDelRes> delete(@RequestBody CategoryDelReq request) {
        CategoryDelRes response = categoryService.delete(request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/patch")
    public ResponseEntity<CategoryPatRes> patch(@RequestBody CategoryPatReq request) {
        CategoryPatRes res = categoryService.patch(request);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/get")
    public ResponseEntity<List<CategoryGetRes>> get() {
        List<CategoryGetRes> res = categoryService.get();
        return ResponseEntity.ok(res);
    }
}
