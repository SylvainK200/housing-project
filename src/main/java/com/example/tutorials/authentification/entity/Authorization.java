package com.example.tutorials.authentification.entity;

import com.example.tutorials.util.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "auth_authorization")
public class Authorization extends AbstractEntity {

    private static final long serialVersionUID = 3332484630603716013L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "adm_authorization_sequence")
    private Integer id;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "auth_privilege_id")
    public Privilege privilege;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "auth_role_id")
    public Role role;
}
