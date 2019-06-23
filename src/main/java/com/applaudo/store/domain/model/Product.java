package com.applaudo.store.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private BigDecimal price;

    @Column
    private int popularity;

    @Column
    private int stock;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnore
    private Set<BuyProduct> purchases = new HashSet<>();

    @Override
    public String toString() {
        return "Product(Id: " + id + ", Name: " + name + ", Description: " + description +
                "Price: " + price + ", Stock: " + stock + ")";
    }
}
