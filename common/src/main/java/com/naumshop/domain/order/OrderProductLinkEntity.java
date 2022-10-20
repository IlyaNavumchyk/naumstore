package com.naumshop.domain.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.naumshop.domain.product.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "l_order_product")
@EqualsAndHashCode(exclude = {"order", "product"})
@JsonIgnoreProperties({"order"})
@Data
public class OrderProductLinkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_count")
    private Integer productCount;

    @Column(name = "product_price")
    private Double productPrice;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties({"category", "orders"})
    private Product product;

    @PrePersist
    public void prePersist() {

        creationDate = LocalDateTime.now();
    }
}
