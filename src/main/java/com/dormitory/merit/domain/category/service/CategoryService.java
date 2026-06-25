package com.dormitory.merit.domain.category.service;

import com.dormitory.merit.domain.category.dto.*;
import com.dormitory.merit.domain.category.entity.Category;
import com.dormitory.merit.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryRegistRes regist(CategoryRegistReq request) {
        Optional<Category> result = categoryRepository.findByTitle(request.getTitle());
        if (!result.isEmpty()) {
            return new CategoryRegistRes("이미 있는 카테고리입니다.");
        }

        Category entity = Category.builder()
                .title(request.getTitle())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        categoryRepository.save(entity);

        return new CategoryRegistRes("카테고리 생성 완료");
    }

    public CategoryDelRes delete(CategoryDelReq req) {

        Category entity = categoryRepository.findById(req.getId())
                        .orElseThrow(() ->
                            new IllegalArgumentException("존재하지 않는 카테고리입니다.")
                        );

        categoryRepository.delete(entity);

        return new CategoryDelRes("성공적으로 삭제 되었습니다.");
    }

    public CategoryPatRes patch(CategoryPatReq req) {
        Category entity = categoryRepository.findById(req.getId())
                .orElseThrow(() ->
                        new IllegalArgumentException("존재하지 않는 카테고리입니다.")
                );
        entity.setTitle(req.getTitle());
        entity.setUpdatedAt(LocalDateTime.now());

        return new CategoryPatRes("수정 완료", LocalDateTime.now());
    }

    public List<CategoryGetRes> get() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            return List.of(new CategoryGetRes(0, null));
        }

        return categories.stream()
                .map(category -> new CategoryGetRes(category.getId(), category.getTitle()))
                .toList();
    }
}
