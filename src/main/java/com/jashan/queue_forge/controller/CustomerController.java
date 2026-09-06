package com.jashan.queue_forge.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.jashan.queue_forge.Repository.CustomerRepository;
import com.jashan.queue_forge.models.Customers;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/customer")


public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }

    @GetMapping("")
    public List<Customers> findAll(){

        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Customers findById(@PathVariable Integer id) {
        return customerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    
    }
    
    @PostMapping("")
    public void  postCoustomer(@RequestBody Customers customer) {
        customerRepository.save(customer);
    }

    @PutMapping("/{id}")
    public void updateCustomer(@PathVariable Integer id, @RequestBody Customers customer) {

        if (!customerRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            
        }
        
        customerRepository.save(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Integer id){
        customerRepository.deleteById(id);
    }
    


}
