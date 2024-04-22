package com.example.tutorials.feature.housing.entity;

import com.example.tutorials.authentification.entity.AppUser;
import com.example.tutorials.util.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hsg_building")
public class Building extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "hsg_building_sequence")
    private Integer id;

    private AppUser manager;

    private String type;

    private String name;

    private String description;

    List<Housing> housings  = new ArrayList<>();

}
