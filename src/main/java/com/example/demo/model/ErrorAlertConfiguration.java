package com.example.demo.model;

import com.example.demo.enums.Roles;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorAlertConfiguration {
    private Integer errorCount;
    private Integer timeWindow;
    private String metaData;
    @Enumerated(EnumType.STRING)
    private Roles role;
}
