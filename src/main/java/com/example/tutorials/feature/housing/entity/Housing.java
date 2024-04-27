package com.example.tutorials.feature.housing.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hsg_housing")
public class Housing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "hsg_housing_sequence")
    private String id;

    private String type;
    private String name;
    private String description;
    // a month is considered as 30 days
    private Float pricePerMonth;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="hsg_building_id")
    private Building building;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "housing")
    @Builder.Default
    private List<Booking> bookings = new ArrayList<>();


}
