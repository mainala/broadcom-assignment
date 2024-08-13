package com.broadcom.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {
    private long ID;
    private String FirstName;
    private String LastName;
    private int Age;
    private String Address1;
    private String Address2;
}
