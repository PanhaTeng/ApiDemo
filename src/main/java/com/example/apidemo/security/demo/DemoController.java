package com.example.apidemo.security.demo;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user-demo")
@PreAuthorize("hasRole('USER')")
@Hidden
public class DemoController {

  @GetMapping
  @PreAuthorize("hasAuthority('user:read')")
  public ResponseEntity<String> userGet() {
    return ResponseEntity.ok("Hello from secured endpoint");
  }
  @PostMapping
  @PreAuthorize("hasAuthority('user:create')")
  public ResponseEntity<String> userCreate() {
    return ResponseEntity.ok("Hello from secured endpoint");
  }
}
