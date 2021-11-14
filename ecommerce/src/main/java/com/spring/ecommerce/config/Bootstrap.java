package com.spring.ecommerce.config;


import com.spring.ecommerce.entities.*;
import com.spring.ecommerce.repos.RoleRepository;
import com.spring.ecommerce.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class Bootstrap implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if(roleRepository.count()<1)
        {
            Role adminRole = new Role();
            adminRole.setAuthority("ROLE_ADMIN");
            Role sellerRole = new Role();
            sellerRole.setAuthority("ROLE_SELLER");
            Role customerRole = new Role();
            customerRole.setAuthority("ROLE_CUSTOMER");

            roleRepository.save(adminRole);
            roleRepository.save(sellerRole);
            roleRepository.save(customerRole);

            if(userRepository.count()<1){
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

                Admin admin = new Admin();
                admin.setFirstName("Mohd Simra");
                admin.setDeleted(false);
                admin.setEmail("mohd.simra@tothenew.com");
                admin.setPassword(passwordEncoder.encode("admin"));
                admin.setActive(true);
                admin.setContact("7455990850");

                UserRole roleOfAdmin = new UserRole(admin,adminRole);
                admin.addRoles(roleOfAdmin);

                Customer customer = new Customer();
                customer.setFirstName("Pragyesh");
                customer.setLastName("Jain");
                customer.setContact("8923688789");
                customer.setDeleted(false);
                customer.setEmail("pragyesh@gmail.com");
                customer.setPassword(passwordEncoder.encode("pragyesh"));
                customer.setActive(true);

                Address address = new Address();
                address.setZipCode("110099");
                address.setLabel("home");
                address.setCountry("India");
                address.setState("Uttrakhand");
                address.setCity("Dehradun");
                address.setAddressLine("Clement Town");

                List<Address> addresses = new ArrayList<>();
                addresses.add(address);
                customer.setAddresses(addresses);

                UserRole roleOfCustomer = new UserRole(customer, customerRole);
                customer.addRoles(roleOfCustomer);

                Seller seller = new Seller();
                seller.setFirstName("Anshul");
                seller.setMiddleName("Rana");
                seller.setLastName("Singh");
                seller.setDeleted(false);
                seller.setActive(true);
                seller.setEmail("anshul@gmail.com");
                seller.setPassword(passwordEncoder.encode("anshul"));
                seller.setGst("753829cc1723");
                seller.setCompanyContact("8923686143");
                seller.setCompanyName("Anshul Cosmetics");

                Address address2 = new Address();
                address2.setZipCode("248002");
                address2.setLabel("office");
                address2.setCountry("India");
                address2.setState("Uttrakhand");
                address2.setCity("Dehradun");
                address2.setAddressLine("Clement Town");

                List<Address> sellerAddress = new ArrayList<>();
                sellerAddress.add(address2);
                seller.setAddresses(sellerAddress);

                UserRole roleOfSeller = new UserRole(seller,sellerRole);
                seller.addRoles(roleOfSeller);

                userRepository.save(seller);
                userRepository.save(customer);
                userRepository.save(admin);
                System.out.println("Total users saved::"+userRepository.count());
            }
        }
   }
}
