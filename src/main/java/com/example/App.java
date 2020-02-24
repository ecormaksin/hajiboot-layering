package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.domain.customer.Customer;
import com.example.domain.customer.CustomerRepository;

@SpringBootApplication
public class App implements CommandLineRunner {
    CustomerRepository customerRepository;
    
    App(CustomerRepository aCustomerRepository) {
        this.customerRepository = aCustomerRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        // データ追加
        Customer created = customerRepository.save(new Customer(null, "Hidetoshi", "Dekisugi"));
        System.out.println(created + " is created!");
        // データ表示
        customerRepository.findAll()
            .forEach(System.out::println);
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
