package com.dormitory.merit.domain.point.dto;

import com.dormitory.merit.domain.point.entity.PointType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PointUpdateReq {

    private int studentId;
    private int categoryId;
    private PointType type;
    private String reason;
    private int point;
}
