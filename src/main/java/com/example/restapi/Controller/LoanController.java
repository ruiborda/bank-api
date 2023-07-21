package com.example.restapi.Controller;
import com.example.restapi.Model.Loan;
import com.example.restapi.Repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LoanController {
    private final LoanRepository loanRepository;
    @Autowired
    public LoanController(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @GetMapping("/loans")
    public List<Loan> getLoans() {
        return loanRepository.findAll();
    }

    @GetMapping("/loans/{id}")
    public Optional<Loan> getLoanById(@PathVariable Long id) {
        return loanRepository.findById(id);
    }

    @PostMapping("/loans")
    public Loan createLoan(@RequestBody Loan Loan) {
        Loan.setId(null);
        return loanRepository.save(Loan);
    }

    @PutMapping("/loans/{id}")
    public Optional<Loan> updateLoanById(@PathVariable Long id, @RequestBody Loan Loan) {
        if (!loanRepository.existsById(id)) {
            return Optional.empty();
        }
        return Optional.of(loanRepository.save(Loan));
    }

    @DeleteMapping("/loans/{id}")
    public ResponseEntity<String> deleteLoanById(@PathVariable Long id) {
        if (!loanRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        loanRepository.deleteById(id);
        return ResponseEntity.ok("Loan with ID " + id + " has been successfully deleted.");
    }
}
