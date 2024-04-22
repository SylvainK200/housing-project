package com.example.tutorials.authentification.entity;

import com.example.tutorials.util.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;


/**
 * Privilèges
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "auth_privilege")
public class Privilege extends AbstractEntity {

    private static final long serialVersionUID = -6805288874475783079L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "adm_privilege_sequence")
    private Integer id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description", length = 510)
    private String description;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "feature", nullable = false)
    private String feature;

    @Column(name = "subfeature", nullable = false)
    private String subFeature;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    @OneToMany(mappedBy = "privilege")
    private List<Authorization> authorizations = new ArrayList<>();

    public Privilege(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public Privilege(String code, String name, String description, String feature, String subFeature) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.feature = feature;
        this.subFeature = subFeature;
    }

}
