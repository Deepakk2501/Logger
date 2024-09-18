package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Builder
@AllArgsConstructor
@Data
public class ErrorAlert {
    public static Integer adminErrorCount = 3;
    public static Integer developerErrorCount = 2;
    public static Integer adminTimeWindow = 10;
    public static Integer developerTimeWindow = 10;

}
