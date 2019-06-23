package com.applaudo.store.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "USER")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column
    private String username;

    @Column
    private String password;

    @ManyToOne
    @JoinColumn(name = "ID_ROLE")
    private Role role;

    @Override
    public String toString() {
        return "User(Id: " + id + ", Username: " + username +
                ", Role: " + role.getCode() + ")";
    }
}
