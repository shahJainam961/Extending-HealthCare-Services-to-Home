package com.team9.had.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer cid;
    private String cityName;
    private String pincode;
    @ManyToOne
    @JoinColumn(name = "district_did", nullable = false)
    private District district;

}
