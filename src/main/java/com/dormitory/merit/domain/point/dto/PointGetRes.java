package com.dormitory.merit.domain.point.dto;

import com.dormitory.merit.domain.point.entity.PointType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PointGetRes {

    private int id;
    private int studentId;
    private int categoryId;
    private String categoryTitle;
    private PointType type;
    private String reason;
    private int point;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
