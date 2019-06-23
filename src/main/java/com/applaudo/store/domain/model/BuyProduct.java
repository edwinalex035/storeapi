package com.applaudo.store.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "buy_product")
public class BuyProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;


    @Basic
    @Column(name = "BUY_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    Date buyDate;

    @Column
    private int quantity;

    @Transient
    private Long userId;

    @Transient
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUCT")
    private Product product;

    @Override
    public String toString() {
        return "BuyProduct(Id: " + id + ", Purchase Date: " + buyDate + ", quantity: " + quantity +
                ", user: " + user.getUsername() + ", product: " + product.getName() + ")";
    }
}
