package com.dormitory.merit.domain.point.service;

import com.dormitory.merit.domain.category.entity.Category;
import com.dormitory.merit.domain.category.repository.CategoryRepository;
import com.dormitory.merit.domain.point.dto.PointAssignReq;
import com.dormitory.merit.domain.point.entity.Point;
import com.dormitory.merit.domain.point.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PointService {

    private final PointRepository pointRepository;
    private final CategoryRepository categoryRepository;

    public void assign(PointAssignReq request) {
        validate(request);

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new IllegalArgumentException("존재하지 않는 카테고리입니다.")
                );

        LocalDateTime now = LocalDateTime.now();

        Point point = Point.builder()
                .studentId(request.getStudentId())
                .category(category)
                .type(request.getType())
                .reason(request.getReason())
                .point(request.getPoint())
                .createdAt(now)
                .updatedAt(now)
                .build();

        pointRepository.save(point);
    }

    private void validate(PointAssignReq request) {
        if (request.getStudentId() <= 0) {
            throw new IllegalArgumentException("학생 id는 1 이상이어야 합니다.");
        }
        if (request.getCategoryId() <= 0) {
            throw new IllegalArgumentException("카테고리 id는 1 이상이어야 합니다.");
        }
        if (request.getType() == null) {
            throw new IllegalArgumentException("상/벌점 타입을 입력해야 합니다.");
        }
        if (request.getReason() == null || request.getReason().isBlank()) {
            throw new IllegalArgumentException("상/벌점 부여 사유를 입력해야 합니다.");
        }
        if (request.getPoint() <= 0) {
            throw new IllegalArgumentException("점수는 1점 이상이어야 합니다.");
        }
    }
}
