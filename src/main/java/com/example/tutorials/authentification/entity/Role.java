package com.example.tutorials.authentification.entity;


import com.example.tutorials.util.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "auth_app_role")
public class Role extends AbstractEntity {
    private static final long serialVersionUID = -7999239621946902382L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "adm_role_sequence")
    private Integer id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description", length = 510)
    private String description;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    @Column(name = "super_user", nullable = false)
    private boolean superUser = false;

    @OneToMany(mappedBy = "role")
    private List<Authorization> authorizations = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "auth_app_user_role",
            joinColumns = {@JoinColumn(name = "auth_role_id")},
            inverseJoinColumns = {@JoinColumn(name = "auth_app_user_id")}
    )
    private Set<AppUser> users = new HashSet<>();
}
