package com.broadcom.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="playersinfo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @Id
    private long id;
    @Column
    private String firstName;
    private String lastName;
    private int age;
    private String address1;
    private String address2;
}

