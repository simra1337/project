package com.spring.ecommerce.controllers;

import com.spring.ecommerce.dtos.*;
import com.spring.ecommerce.entities.Address;
import com.spring.ecommerce.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {

    @Autowired
    CustomerDaoService customerDaoService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping("/profile")
    public CustomerProfileDto customerProfile(Principal principal) {
        return customerDaoService.getProfile(principal.getName());
    }

    @GetMapping("/address")
    public List<Address> customerAddresses(Principal principal) {
        return customerDaoService.getAddresses(principal.getName());
    }

    //@PatchMapping("/update-profile")
    @PutMapping("/update-profile")
    public ResponseEntity<String> updateCustomerProfile(@Valid @RequestBody CustomerProfileDto customerProfileDto
            , Principal principal)
    {
        return customerDaoService.updateProfile(customerProfileDto, principal.getName());
    }

    //@PatchMapping("/update-password")
    @PutMapping("/update-password")
    public ResponseEntity<String> updateCustomerPassword(@Valid @RequestBody UpdatePasswordDto passwordDto
            , Principal principal)
    {
        return customerDaoService.updatePassword(passwordDto, principal.getName());
    }

    //@PatchMapping("/add-address")
    @PutMapping("add-address")
    public ResponseEntity<String> addNewAddress(@Valid @RequestBody Address address
            , Principal principal)
    {
        return customerDaoService.addNewAddress(address, principal.getName());
    }

    //@PatchMapping("/update-address/{id}")
    @PutMapping("/update-address/{id}")
    public ResponseEntity<String> updateAddress(@Valid @RequestBody Address address
            , @PathVariable long id, Principal principal)
    {
        return customerDaoService.updateAnAddress(address, id, principal.getName());
    }

    @DeleteMapping("/delete-address/{id}")
    public ResponseEntity<String> deleteAnAddress(@PathVariable long id, Principal principal)
    {
        return customerDaoService.deleteAnAddress(id, principal.getName());
    }

    @GetMapping({"/category", "/category/{id}"})
    public List<ViewAllCategoryForCustomerDto> viewAllCategory(@PathVariable(name = "id", required = false ) Long id)
    {
        return categoryService.viewCategoryForCustomer(id);
    }

    @GetMapping("/filter-category/{id}")
    public FilterCategoryDto filterCategory(@PathVariable long id)
    {
        return categoryService.filterCategory(id);
    }

    @GetMapping("/product/{productId}")
    public DisplayProductForCustomerDto viewAProduct(@PathVariable long productId)
    {
        return productService.viewAProductForCustomer(productId);
    }

    @GetMapping("/all-products/{categoryId}")
    public List<DisplayProductForCustomerDto> viewAllProduct(@PathVariable long categoryId)
    {
        return productService.viewAllProductForCustomer(categoryId);
    }

    @GetMapping("/similar-products/{productId}")
    public List<ProductWithVariationImageDto> viewSimilarProducts(@PathVariable long productId)
    {
        return productService.viewSimilarProducts(productId);
    }
}
