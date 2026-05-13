package jorthan.blog.controller;

import jorthan.blog.dtos.CommonDtos;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/api/system")
public class SystemController {

    @GetMapping("/test")
    public ResponseEntity<CommonDtos.ApiResponse<String>> test() {
        var response = new CommonDtos.ApiResponse<>(
                "SUCCESS",
                "后端服务启动成功，环境正常",
                "系统运行正常",
                Instant.now()
        );
        return ResponseEntity.ok(response);
    }
}
