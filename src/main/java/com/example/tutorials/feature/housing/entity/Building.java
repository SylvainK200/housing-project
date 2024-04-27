package com.example.tutorials.feature.housing.entity;

import com.example.tutorials.authentification.entity.AppUser;
import com.example.tutorials.util.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hsg_building")
@EqualsAndHashCode(callSuper = false)
public class Building extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "hsg_building_sequence")
    private Integer id;

    private AppUser manager;

    private String type;

    private String name;

    private String description;

    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY)
    @Builder.Default
    List<Housing> housings = new ArrayList<>();

}
