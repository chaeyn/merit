package com.dormitory.merit.domain.point.controller;

import com.dormitory.merit.domain.point.dto.PointAssignReq;
import com.dormitory.merit.domain.point.dto.PointGetRes;
import com.dormitory.merit.domain.point.dto.PointUpdateReq;
import com.dormitory.merit.domain.point.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/points")
public class PointController {

    private final PointService pointService;

    @GetMapping
    public ResponseEntity<List<PointGetRes>> getAll() {
        List<PointGetRes> response = pointService.getAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/students/{studentId}")
    public ResponseEntity<List<PointGetRes>> getByStudentId(@PathVariable int studentId) {
        List<PointGetRes> response = pointService.getByStudentId(studentId);
        return ResponseEntity.ok(response);
    }

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

    @PatchMapping("/{pointId}")
    public ResponseEntity<Void> update(@PathVariable int pointId, @RequestBody PointUpdateReq request) {
        pointService.update(pointId, request);
        return ResponseEntity.ok().build();
    }
}
