package com.spring.ecommerce.controllers;

import com.spring.ecommerce.dtos.*;
import com.spring.ecommerce.entities.Address;
import com.spring.ecommerce.exceptions.ProductNotFoundException;
import com.spring.ecommerce.services.CategoryService;
import com.spring.ecommerce.services.ProductService;
import com.spring.ecommerce.services.SellerDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path = "/seller")
public class SellerController {

    @Autowired
    SellerDaoService sellerDaoService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping("/profile")
    public SellerProfileDto customerProfile(Principal principal) {
        return sellerDaoService.getProfile(principal.getName());
    }

    //@PatchMapping("/update-profile")
    @PutMapping("/update-profile")
    public ResponseEntity<String> updateCustomerProfile(@Valid @RequestBody SellerProfileDto sellerProfileDto
            , Principal principal)
    {
        return sellerDaoService.updateProfile(sellerProfileDto, principal.getName());
    }

    //@PatchMapping("/update-password")
    @PutMapping("/update-password")
    public ResponseEntity<String> updateCustomerPassword(@Valid @RequestBody UpdatePasswordDto passwordDto
            , Principal principal)
    {
        return sellerDaoService.updatePassword(passwordDto, principal.getName());
    }

    //@PatchMapping("/update-address/{id}")
    @PutMapping("/update-address/{id}")
    public ResponseEntity<String> updateAddress(@Valid @RequestBody Address address
            , @PathVariable long id, Principal principal)
    {
        return sellerDaoService.updateAnAddress(address, id, principal.getName());
    }

    @GetMapping("/category")
    public List<DisplayCategoryDto> viewAllCategory()
    {
        return categoryService.viewAllCategoryForSeller();
    }

    @PostMapping("/add-product")
    public ResponseEntity<String> addAProduct(@Valid @RequestBody AddProductDto addProductDto, Principal principal)
    {
        return productService.addAProduct(addProductDto, principal.getName());
    }


    @GetMapping("/product/{id}")
    public DisplayProductDto viewAProduct(@PathVariable long id, Principal principal)
    {
        DisplayProductDto displayProductDto = productService.viewAProduct(id, principal.getName());

        if(displayProductDto == null)
        {
            throw new ProductNotFoundException("No product found for the specific product id.");
        }

        return displayProductDto;
    }

    @GetMapping("/product")
    public List<DisplayProductDto> viewAllProducts(Principal principal, @RequestParam(required = false) Integer page)
    {
        return productService.viewALlProducts(principal.getName(), page);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteAProduct(@PathVariable long id, Principal principal)
    {
        return productService.deleteAProduct(id, principal.getName());
    }

    //@PatchMapping("/update-product/{id}")
    @PutMapping("/update-product/{id}")
    public ResponseEntity<String> updateProduct(@Valid @RequestBody AddProductDto updateProduct, @PathVariable long id, Principal principal)
    {
        return productService.updateProduct(updateProduct,id, principal.getName());
    }

    @PostMapping("/add-product-variation")
    public ResponseEntity<String> addProductVariation(@Valid @RequestBody AddProductVariationDto addProductVariationDto, Principal principal)
    {
        return productService.addAProductVariation(addProductVariationDto, principal.getName());
    }

    @GetMapping("/product-variation/{id}")
    public DisplayProductVariationDto viewAProductVariation(@PathVariable long id, Principal principal)
    {
        return productService.viewAProductVariation(id, principal.getName());
    }

    @GetMapping("/product-variation")
    public List<DisplayProductVariationDto> viewAllProductVariation(Principal principal, @RequestParam(required = false) Integer page)
    {
        return productService.viewAllProductVariation(principal.getName(), page);
    }

    //@PatchMapping("/update-product-variation/{id}")
    @PutMapping("/update-product-variation/{id}")
    public ResponseEntity<String> updateProduct(@RequestBody AddProductVariationDto addProductVariationDto, @PathVariable long id, Principal principal)
    {
        return productService.updateProductVariation(addProductVariationDto, principal.getName(), id);
    }
 }
