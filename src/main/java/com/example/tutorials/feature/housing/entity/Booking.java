package com.example.tutorials.feature.housing.entity;


import com.example.tutorials.authentification.entity.AppUser;
import com.example.tutorials.util.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hsg_booking")
@EqualsAndHashCode(callSuper = false)
public class Booking extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "hsg_booking_sequence")
    private Integer id;

    private String reference;
    private Instant bookingDate;
    private Boolean fully_paid;

    @Column(name="begining_date")
    private Instant beginingDate;

    @Column(name="ending_date")
    private Instant endingDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="auth_app_user_id")
    private AppUser customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="hsg_housing_id" )
    private Housing housing;
}
