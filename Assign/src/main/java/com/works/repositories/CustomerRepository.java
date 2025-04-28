package com.works.repositories;

import com.works.entities.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


    Optional<Customer> findByEmailEqualsIgnoreCaseAndPasswordEquals(String email, String password);

    Optional<Customer> findByEmailEqualsIgnoreCase(@NotNull @Email @NotEmpty String email);
}
