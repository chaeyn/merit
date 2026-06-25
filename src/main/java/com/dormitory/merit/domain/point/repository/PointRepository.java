package com.dormitory.merit.domain.point.repository;

import com.dormitory.merit.domain.point.entity.Point;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Point, Integer> {

    @EntityGraph(attributePaths = "category")
    List<Point> findAllByOrderByCreatedAtDescIdDesc();

    @EntityGraph(attributePaths = "category")
    List<Point> findAllByStudentIdOrderByCreatedAtDescIdDesc(int studentId);
}
