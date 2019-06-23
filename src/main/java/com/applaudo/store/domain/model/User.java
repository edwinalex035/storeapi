package com.applaudo.store.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnore
    private Set<BuyProduct> purchases = new HashSet<>();

    @Override
    public String toString() {
        return "User(Id: " + id + ", Username: " + username +
                ", Role: " + role.getCode() + ")";
    }
}
