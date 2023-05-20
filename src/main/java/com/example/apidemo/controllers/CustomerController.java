package com.example.apidemo.controllers;

import com.example.apidemo.models.Customer;
import com.example.apidemo.services.impl.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
public class CustomerController {
    private final CustomerServiceImpl customerService;


    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(customerService.findAllCusDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.findCusDtoById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.create(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.update(customer, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
