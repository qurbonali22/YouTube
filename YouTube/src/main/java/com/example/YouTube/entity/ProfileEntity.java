package com.example.YouTube.entity;

import com.example.YouTube.enums.ProfileRole;
import com.example.YouTube.enums.ProfileStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "profile")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private ProfileRole role;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column
    @Enumerated(EnumType.STRING)
    private ProfileStatus status;

    @Column(name = "attach_id")
    private String attachId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attach_id", insertable = false, updatable = false)
    private AttachEntity attach;

}

//id,name,surname,email,password,photo,role,status