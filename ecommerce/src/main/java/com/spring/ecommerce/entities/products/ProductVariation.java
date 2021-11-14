package com.spring.ecommerce.entities.products;


import com.spring.ecommerce.entities.orders.OrderProduct;
import com.spring.ecommerce.entities.orders.Cart;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@Table(name = "product_variation")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class ProductVariation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private Double price;

    private long quantityAvailable;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Map<String,String> metadata;

    private String primaryImageName;
    private boolean isActive;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productVariation")
    private List<OrderProduct> orderProductList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productVariation")
    private List<Cart> cartList;

    public ProductVariation() {
        this.isActive = true;
    }
}
