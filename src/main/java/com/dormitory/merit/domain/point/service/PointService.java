package com.dormitory.merit.domain.point.service;

import com.dormitory.merit.domain.category.entity.Category;
import com.dormitory.merit.domain.category.repository.CategoryRepository;
import com.dormitory.merit.domain.point.dto.PointAssignReq;
import com.dormitory.merit.domain.point.dto.PointGetRes;
import com.dormitory.merit.domain.point.dto.PointUpdateReq;
import com.dormitory.merit.domain.point.entity.Point;
import com.dormitory.merit.domain.point.entity.PointType;
import com.dormitory.merit.domain.point.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PointService {

    private final PointRepository pointRepository;
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<PointGetRes> getAll() {
        return pointRepository.findAllByOrderByCreatedAtDescIdDesc()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<PointGetRes> getByStudentId(int studentId) {
        if (studentId <= 0) {
            throw new IllegalArgumentException("학생 id는 1 이상이어야 합니다.");
        }

        return pointRepository.findAllByStudentIdOrderByCreatedAtDescIdDesc(studentId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public void assign(PointAssignReq request) {
        validate(
                request.getStudentId(),
                request.getCategoryId(),
                request.getType(),
                request.getReason(),
                request.getPoint()
        );

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

    public void update(int pointId, PointUpdateReq request) {
        if (pointId <= 0) {
            throw new IllegalArgumentException("상/벌점 부여 id는 1 이상이어야 합니다.");
        }
        validate(
                request.getStudentId(),
                request.getCategoryId(),
                request.getType(),
                request.getReason(),
                request.getPoint()
        );

        Point point = pointRepository.findById(pointId)
                .orElseThrow(() ->
                        new IllegalArgumentException("존재하지 않는 상/벌점 부여 내역입니다.")
                );

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new IllegalArgumentException("존재하지 않는 카테고리입니다.")
                );

        point.setStudentId(request.getStudentId());
        point.setCategory(category);
        point.setType(request.getType());
        point.setReason(request.getReason());
        point.setPoint(request.getPoint());
        point.setUpdatedAt(LocalDateTime.now());

        pointRepository.save(point);
    }

    public void cancel(int pointId) {
        if (pointId <= 0) {
            throw new IllegalArgumentException("상/벌점 부여 id는 1 이상이어야 합니다.");
        }

        Point point = pointRepository.findById(pointId)
                .orElseThrow(() ->
                        new IllegalArgumentException("존재하지 않는 상/벌점 부여 내역입니다.")
                );

        pointRepository.delete(point);
    }

    private void validate(int studentId, int categoryId, PointType type, String reason, int point) {
        if (studentId <= 0) {
            throw new IllegalArgumentException("학생 id는 1 이상이어야 합니다.");
        }
        if (categoryId <= 0) {
            throw new IllegalArgumentException("카테고리 id는 1 이상이어야 합니다.");
        }
        if (type == null) {
            throw new IllegalArgumentException("상/벌점 타입을 입력해야 합니다.");
        }
        if (reason == null || reason.isBlank()) {
            throw new IllegalArgumentException("상/벌점 부여 사유를 입력해야 합니다.");
        }
        if (point <= 0) {
            throw new IllegalArgumentException("점수는 1점 이상이어야 합니다.");
        }
    }

    private PointGetRes toResponse(Point point) {
        Category category = point.getCategory();

        return new PointGetRes(
                point.getId(),
                point.getStudentId(),
                category.getId(),
                category.getTitle(),
                point.getType(),
                point.getReason(),
                point.getPoint(),
                point.getCreatedAt(),
                point.getUpdatedAt()
        );
    }
}
