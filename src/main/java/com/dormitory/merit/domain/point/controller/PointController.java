package com.dormitory.merit.domain.point.controller;

import com.dormitory.merit.domain.point.dto.PointAssignReq;
import com.dormitory.merit.domain.point.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/points")
public class PointController {

    private final PointService pointService;

    @PostMapping
    public ResponseEntity<Void> assign(@RequestBody PointAssignReq request) {
        pointService.assign(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{pointId}")
    public ResponseEntity<Void> cancel(@PathVariable int pointId) {
        pointService.cancel(pointId);
        return ResponseEntity.ok().build();
    }
}
