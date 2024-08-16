package com.broadcom.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String address1;
    private String address2;
}
