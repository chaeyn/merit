package com.dormitory.merit.domain.category.service;

import com.dormitory.merit.domain.category.dto.CategoryDelReq;
import com.dormitory.merit.domain.category.dto.CategoryDelRes;
import com.dormitory.merit.domain.category.entity.Category;
import com.dormitory.merit.global.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteService {

    private final CategoryRepository categoryRepository;

    public CategoryDelRes delete(CategoryDelReq req) {

        Category entity = categoryRepository.findById(req.getId())
                        .orElseThrow(() ->
                            new IllegalArgumentException("존재하지 않는 카테고리입니다.")
                        );

        categoryRepository.delete(entity);

        return new CategoryDelRes("성공적으로 삭제 되었습니다.");
    }
}
