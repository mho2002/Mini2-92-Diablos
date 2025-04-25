package com.example.demo.controllers;

import com.example.demo.models.Payment;
import com.example.demo.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;
    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/addPayment")
    public Payment addPayment(@RequestBody Payment payment)
    {
//        if (!paymentService.isPaymentHasValidData(payment))
//            throw new IllegalArgumentException("Payment data is not valid");
        return paymentService.addPayment(payment);
    }

    @GetMapping("/allPayments")
    public List<Payment> getAllPayments()
    {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable Long id)
    {
        if (id==null)
            throw new IllegalArgumentException("Payment id is null");
        try {
            return paymentService.getPaymentById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/update/{id}")
    public Payment updatePayment(@PathVariable Long id, @RequestBody Payment payment)
    {
        if (id==null)
            throw new IllegalArgumentException("Payment id is null");
//        else if (!paymentService.isPaymentHasValidData(payment)) {
//            throw new IllegalArgumentException("New payment data is not valid");
//        }
        return paymentService.updatePayment(id, payment);
    }

    @DeleteMapping("/delete/{id}")
    public String deletePayment(@PathVariable Long id)
    {
        if (id==null)
            throw new IllegalArgumentException("Payment id is null");
        paymentService.deletePayment(id);
        return "Payment deleted";
    }

    @GetMapping("/findByTripId")
    public List<Payment> findPaymentsByTripId(@RequestParam Long tripId)
    {
        if (tripId==null)
            throw new IllegalArgumentException("Trip id is null");
        return paymentService.findPaymentsByTripId(tripId);
    }

    @GetMapping("/findByAmountThreshold")
    public List<Payment> findPaymentsWithAmountGreaterThan(@RequestParam Double threshold)
    {
        if (threshold==null)
            throw new IllegalArgumentException("Threshold is null");
        return paymentService.findByAmountThreshold(threshold);
    }




}
