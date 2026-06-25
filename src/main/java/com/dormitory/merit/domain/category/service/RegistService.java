package com.dormitory.merit.domain.category.service;

import com.dormitory.merit.domain.category.dto.CategoryRegistReq;
import com.dormitory.merit.domain.category.dto.CategoryRegistRes;
import com.dormitory.merit.domain.category.entity.Category;
import com.dormitory.merit.global.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistService {

    private final CategoryRepository categoryRepository;

    public CategoryRegistRes regist(CategoryRegistReq request) {
        Optional<Category> result = categoryRepository.findByTitle(request.getTitle());
        if (!result.isEmpty()) {
            return new CategoryRegistRes("이미 있는 카테고리입니다.");
        }

        Category entity = Category.builder()
                .title(request.getTitle())
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .build();

        categoryRepository.save(entity);

        return new CategoryRegistRes("카테고리 생성 완료");
    }
}
