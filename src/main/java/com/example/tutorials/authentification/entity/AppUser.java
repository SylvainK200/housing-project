package com.example.tutorials.authentification.entity;

import com.example.tutorials.util.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "auth_app_user")
public class AppUser extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "prs_user_sequence")
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "signature", columnDefinition = "TEXT")
    private String signature;

    @Column(name = "small_signature", columnDefinition = "TEXT")
    private String smallSignature;

    private Instant lastConnectionDate;

    private String phoneNumber;

    private boolean enabled = true;

    @ManyToMany(mappedBy = "users")
    private List<Role> roles = new ArrayList<>();

    public void addRole(Role role) {
        if (!roles.contains(role)) {
            this.roles.add(role);
            role.getUsers().add(this);
        }
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
        role.getUsers().remove(this);
    }

    public boolean hasRole(Role role) {
        if (roles.contains(role))
            return true;
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppUser)) return false;

        AppUser user = (AppUser) o;

        return getId() != null ? getId().equals(user.getId()) : user.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
