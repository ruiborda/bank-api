package com.example.restapi.Controller;
import com.example.restapi.Model.LoanType;
import com.example.restapi.Repository.LoanTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LoanTypeController {
    private final LoanTypeRepository loanTypeRepository;


    @Autowired
    public LoanTypeController(LoanTypeRepository loanTypeRepository) {
        this.loanTypeRepository = loanTypeRepository;
    }

    @GetMapping("/loan-types")
    public List<LoanType> getloanType() {
        return loanTypeRepository.findAll();
    }

    @GetMapping("/loan-types/{id}")
    public Optional<LoanType> getLoanById(@PathVariable Long id) {
        return loanTypeRepository.findById(id);
    }

    @PostMapping("/loan-types")
    public LoanType createLoan(@RequestBody LoanType LoanType) {
        LoanType.setId(null);
        return loanTypeRepository.save(LoanType);
    }

    @PutMapping("/loan-types/{id}")
    public Optional<LoanType> updateLoanById(@PathVariable Long id, @RequestBody LoanType LoanType) {
        if (!loanTypeRepository.existsById(id)) {
            return Optional.empty();
        }
        return Optional.of(loanTypeRepository.save(LoanType));
    }

    @DeleteMapping("/loan-types/{id}")
    public ResponseEntity<String> deleteLoanById(@PathVariable Long id) {
        if (!loanTypeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        loanTypeRepository.deleteById(id);
        return ResponseEntity.ok("LoanType with ID " + id + " has been successfully deleted.");
    }
}
