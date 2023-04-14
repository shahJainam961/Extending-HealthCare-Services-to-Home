package com.team9.had.model;

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
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer did;
    private String districtName;
    @ManyToOne
    @JoinColumn(name = "state_sid", nullable = false)
    private State state;
}
