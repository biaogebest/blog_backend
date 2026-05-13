package jorthan.blog.dtos;

import java.time.Instant;

public class CommonDtos {
    public record ApiResponse<T>(
            String code,
            String msg,
            T data,
            Instant timestamp
    ) {}
}
