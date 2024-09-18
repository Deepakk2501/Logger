package com.example.demo.entity;

import com.example.demo.constants.ErrorMessage;
import com.example.demo.enums.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @Enumerated(EnumType.STRING)
    private Roles adminRole = Roles.ADMIN;
    @Enumerated(EnumType.STRING)
    private Roles developerRole = Roles.DEVELOPER;//    @Enumerated(EnumType.STRING)
    private ErrorMessage adminErrorMessage = ErrorMessage.ADMIN_MESSAGE;
    private ErrorMessage developerErrorMessage = ErrorMessage.DEVELOPER;
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "role_id")
    private ErrorAlert errorAlert;

}
