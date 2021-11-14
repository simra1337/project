package com.spring.ecommerce.entities;

import com.spring.ecommerce.entities.orders.Cart;
import com.spring.ecommerce.entities.orders.Orders;
import com.spring.ecommerce.entities.products.ProductReview;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "user_id")
@Getter
@Setter
public class Customer extends User{

    private String contact;
    private String image;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Orders> ordersSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<ProductReview> productReviews;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Cart> cartList;

}
