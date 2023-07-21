package com.example.restapi.Controller;
import com.example.restapi.Model.Payment;
import com.example.restapi.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PaymentController {
    private final PaymentRepository paymentRepository;


    @Autowired
    public PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @GetMapping("/payments")
    public List<Payment> getloanType() {
        return paymentRepository.findAll();
    }

    @GetMapping("/payments/{id}")
    public Optional<Payment> getLoanById(@PathVariable Long id) {
        return paymentRepository.findById(id);
    }

    @PostMapping("/payments")
    public Payment createLoan(@RequestBody Payment payment) {
        payment.setId(null);
        return paymentRepository.save(payment);
    }

    @PutMapping("/payments/{id}")
    public Optional<Payment> updateLoanById(@PathVariable Long id, @RequestBody Payment payment) {
        if (!paymentRepository.existsById(id)) {
            return Optional.empty();
        }
        return Optional.of(paymentRepository.save(payment));
    }

    @DeleteMapping("/payments/{id}")
    public ResponseEntity<String> deleteLoanById(@PathVariable Long id) {
        if (!paymentRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        paymentRepository.deleteById(id);
        return ResponseEntity.ok("LoanType with ID " + id + " has been successfully deleted.");
    }
}
