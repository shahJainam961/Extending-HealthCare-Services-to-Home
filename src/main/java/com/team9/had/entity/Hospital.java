package com.team9.had.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer hospId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "street1", nullable = false, length = 1000)
    private String street1;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(nullable = false)
    private String district;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "pincode", nullable = false, length = 6)
    private String pincode;

    @Column(name = "contactNo", nullable = false, unique = true)
    private String contactNo;
}
