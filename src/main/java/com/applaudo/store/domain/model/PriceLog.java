package com.applaudo.store.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "LOG_PRICE")
public class PriceLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column(name = "id_product")
    private long idProduct;

    @Column(name = "old_price")
    private BigDecimal oldPrice;

    @Column(name = "new_price")
    private BigDecimal newPrice;

    @Column
    private String username;

    @Basic
    @Column(name = "CREATE_DT")
    @Temporal(TemporalType.TIMESTAMP)
    Date createDt;

    @Override
    public String toString() {
        return "PriceLog(Id: " + id + ", idProduct: " + idProduct + ", Old Price: " + oldPrice +
                ", New Price: " + newPrice + ", Username: " + username + ", Create Date: " + createDt + ")";
    }
}
