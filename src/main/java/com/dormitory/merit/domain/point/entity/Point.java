package com.dormitory.merit.domain.point.entity;

import com.dormitory.merit.domain.category.entity.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "points")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int studentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PointType type;

    @NotNull
    private String reason;

    private int point;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime updatedAt;
}
