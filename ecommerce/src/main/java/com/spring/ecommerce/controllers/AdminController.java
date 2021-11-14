package com.spring.ecommerce.controllers;

import com.spring.ecommerce.dtos.*;
import com.spring.ecommerce.entities.products.categories.CategoryMetadataField;
import com.spring.ecommerce.exceptions.CategoryNotFoundException;
import com.spring.ecommerce.services.CategoryService;
import com.spring.ecommerce.services.ProductService;
import com.spring.ecommerce.services.UserDaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {

    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    UserDaoService userService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping(path = "/customers")
    public List<GetAllCustomerInfoDto> listAllCustomers()
    {
        return userService.getAllCustomers();
    }

    @GetMapping(path = "/sellers")
    public List<GetAllSellersInfoDto> listAllSellers()
    {
        return userService.getAllSellers();
    }

    //@PatchMapping(path = "/seller/activate/{id}")
    @PutMapping("/seller/activate/{id}")
    public ResponseEntity<String> activateSeller(@PathVariable long id)
    {
        return userService.activateSeller(id);
    }

    //@PatchMapping(path = "/seller/deactivate/{id}")
    @PutMapping("/seller/deactivate/{id}")
    public ResponseEntity<String> deactivateSeller(@PathVariable long id)
    {
       return userService.deactivateSeller(id);
    }

    //@PatchMapping(path = "/customer/activate/{id}")
    @PutMapping("/customer/activate/{id}")
    public ResponseEntity<String> activateCustomer(@PathVariable long id)
    {
        return userService.activateCustomer(id);
    }

    //@PatchMapping(path = "/customer/deactivate/{id}")
    @PutMapping("/customer/deactivate/{id}")
    public ResponseEntity<String> deactivateCustomer(@PathVariable long id)
    {
        return userService.deactivateCustomer(id);
    }

    @PostMapping("/add-category")
    public ResponseEntity<String> addCategory(@Valid @RequestBody AddCategoryDto categoryDto)
    {
        return categoryService.addCategory(categoryDto);
    }

    @GetMapping("/category/{id}")
    public AllCategoryInfoDto viewACategory(@PathVariable long id)
    {
        AllCategoryInfoDto categoryDto = categoryService.viewACategory(id);
        if(categoryDto == null)
        {
            throw new CategoryNotFoundException("No category found for the specified category id.");
        }
        return categoryDto;
    }

    @GetMapping("/category")
    public List<AllCategoryInfoDto> viewAllCategory()
    {
        return categoryService.viewAllCategoryAdmin();
    }

    //@PatchMapping("/update-category")
    @PutMapping("/update-category")
    public ResponseEntity<String> updateCategory(@Valid @RequestBody AddCategoryDto categoryDto)
    {
        return categoryService.updateCategory(categoryDto);
    }

    @PostMapping("/add-category-metadata-field")
    public ResponseEntity<String> addCategoryMetadataField(@RequestParam("fieldName") @NotNull String fieldName)
    {
        return categoryService.addMetadataField(fieldName);
    }

    @GetMapping("/category-metadata-field")
    public List<CategoryMetadataField> getAllMetadataFields()
    {
        return categoryService.viewAllMetadataFields();
    }

    @PostMapping("/add-metadata-field-values")
    public ResponseEntity<String> addMetadataFieldValues(
            @Valid @RequestBody AddCategoryMetadataFieldValuesDto addCategoryMetadataFieldValuesDto)
    {
        return categoryService.addCategoryMetadataFieldValues(addCategoryMetadataFieldValuesDto);
    }

    //@PatchMapping("/update-metadata-field-values")
    @PutMapping("/update-metadata-field-values")
    public ResponseEntity<String> updateMetadataFieldValues(
            @Valid @RequestBody AddCategoryMetadataFieldValuesDto addCategoryMetadataFieldValuesDto)
    {
        return categoryService.updateCategoryMetadataFieldValues(addCategoryMetadataFieldValuesDto);
    }

    //@PatchMapping("/deactivate-product/{id}")
    @PutMapping("/deactivate-product/{id}")
    public ResponseEntity<String> deactivateAProduct(@PathVariable long id)
    {
        return productService.deactivateAProduct(id);
    }

    //@PatchMapping("/activate-product/{id}")
    @PutMapping("/activate-product/{id}")
    public ResponseEntity<String> activateAProduct(@PathVariable long id)
    {
        return productService.activateAProduct(id);
    }

    @GetMapping("/product/{id}")
    public ProductWithVariationImageDto viewAProduct(@PathVariable long id)
    {
        return productService.viewAProductForAdmin(id);
    }

    @GetMapping("/product")
    public List<ProductWithVariationImageDto> viewAllProduct()
    {
        return productService.viewAllProductForAdmin();
    }


}
