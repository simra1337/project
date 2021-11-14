package com.spring.ecommerce.entities.orders;

import com.spring.ecommerce.entities.products.ProductVariation;
import com.spring.ecommerce.entities.Customer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Cart {

    @EmbeddedId
    private CartId cartId;

    @ManyToOne
    @MapsId("customerUserId")
    @JoinColumn(name = "customer_user_id")
    private Customer customer;

    @ManyToOne
    @MapsId("productVariationId")
    @JoinColumn(name = "product_variation_id")
    private ProductVariation productVariation;

    private int quantity;
}
