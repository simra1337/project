package com.spring.ecommerce.services;

import com.spring.ecommerce.dtos.CustomerProfileDto;
import com.spring.ecommerce.dtos.UpdatePasswordDto;
import com.spring.ecommerce.entities.Address;
import com.spring.ecommerce.entities.Customer;
import com.spring.ecommerce.entities.User;
import com.spring.ecommerce.repos.AddressRepository;
import com.spring.ecommerce.repos.CustomerRepository;
import com.spring.ecommerce.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDaoService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;

    public CustomerProfileDto getProfile(String email)
    {
        // getting the user details
        User user = userRepository.findByEmail(email);
        Customer customer = customerRepository.findCustomerById(user.getId());
        CustomerProfileDto customerProfile = new CustomerProfileDto();
        customerProfile.setId(customer.getId());
        customerProfile.setFirstName(customer.getFirstName());
        customerProfile.setLastName(customer.getLastName());
        customerProfile.setActive(customer.isActive());
        customerProfile.setContact(customer.getContact());
        customerProfile.setImage(customer.getImage());

        return customerProfile;
    }

    public List<Address> getAddresses(String email)
    {
        // returning the list of addresses
        User user = userRepository.findByEmail(email);
        return user.getAddresses();
    }

    public ResponseEntity<String> updateProfile(CustomerProfileDto customerProfileDto, String email)
    {
        // getting the user details
        User user = userRepository.findByEmail(email);
        // updating profile
        Customer customer = customerRepository.findCustomerById(user.getId());
        customer.setFirstName(customerProfileDto.getFirstName());
        customer.setLastName(customerProfileDto.getLastName());
        customer.setContact(customerProfileDto.getContact());
        customer.setImage(customerProfileDto.getImage());

        customerRepository.save(customer);
        return new ResponseEntity("Profile updated successfully.",HttpStatus.ACCEPTED);
    }

    public ResponseEntity<String> updatePassword(UpdatePasswordDto updatePasswordDto, String email)
    {
        //  getting the user details
        User user = userRepository.findByEmail(email);
        Customer customer = customerRepository.findCustomerById(user.getId());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // checking if new password and confirm password is same or not
        if(updatePasswordDto.getNewPassword().equals(updatePasswordDto.getConfirmPassword()))
        {
            // checking if current password is same as the new password
            if(encoder.matches(updatePasswordDto.getNewPassword(), customer.getPassword()))
            {
                return new ResponseEntity("Current password and new password should be different."
                        ,HttpStatus.BAD_REQUEST);
            }

            customer.setPassword(encoder.encode(updatePasswordDto.getNewPassword()));
            customerRepository.save(customer);
            return new ResponseEntity("Password updated successfully.",HttpStatus.ACCEPTED);
        }
        return new ResponseEntity("New password and confirm password should be same.",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addNewAddress(Address address, String email)
    {
        // getting user details
        User user = userRepository.findByEmail(email);
        Customer customer = customerRepository.findCustomerById(user.getId());
        //getting the list of address for the customer from database
        List<Address> addressList = customer.getAddresses();

        Address newAddress = new Address();
        newAddress.setAddressLine(address.getAddressLine());
        newAddress.setCity(address.getCity());
        newAddress.setState(address.getState());
        newAddress.setCountry(address.getCountry());
        newAddress.setLabel(address.getLabel());
        newAddress.setZipCode(address.getZipCode());

        // adding new address to the list
        addressList.add(newAddress);
        // updating the list
        customer.setAddresses(addressList);
        customerRepository.save(customer);

        return new ResponseEntity("New address added successfully.",HttpStatus.CREATED);
    }


    public ResponseEntity<String> updateAnAddress(Address address, long id, String email)
    {
        // getting user details
        User user = userRepository.findByEmail(email);
        // getting all the ids of address from the list of address for the customer
        List<Long> addressIds = addressRepository.findAddressIdsForUserId(user.getId());

        // checking if the id provided to update exist in the ids we got from the list of addresses of customer
        if(addressIds.contains(id))
        {
            Address updatedAddress = addressRepository.findById(id);
            updatedAddress.setAddressLine(address.getAddressLine());
            updatedAddress.setLabel(address.getLabel());
            updatedAddress.setCountry(address.getCountry());
            updatedAddress.setState(address.getState());
            updatedAddress.setCity(address.getCity());
            updatedAddress.setZipCode(address.getZipCode());

            // updating address
            addressRepository.save(updatedAddress);
            return new ResponseEntity("Address updated successfully.",HttpStatus.CREATED);
        }
        return new ResponseEntity("No address found with particular id.",HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteAnAddress(long id, String email)
    {
        // getting user details
        User user = userRepository.findByEmail(email);
        // getting all the ids of address from the list of address for the customer
        List<Long> addressIds = addressRepository.findAddressIdsForUserId(user.getId());

        // checking if the id provided to update exist in the ids we got from the list of addresses of customer
        if(addressIds.contains(id))
        {
            // deleting address
            addressRepository.deleteById(id);
            return new ResponseEntity("Address deleted successfully.",HttpStatus.CREATED);
        }
        return new ResponseEntity("No address found with particular id.",HttpStatus.NOT_FOUND);
    }
}
