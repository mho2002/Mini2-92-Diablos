package com.example.demo.services;

import com.example.demo.models.Payment;
import com.example.demo.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment addPayment(Payment payment)
    {
        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments()
    {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id)
    {
        if (paymentRepository.findById(id).isPresent())
            return paymentRepository.findById(id).get();
        return null;
    }

    public Payment updatePayment(Long id, Payment payment)
    {
        Payment oldPayment = new Payment();
        if (paymentRepository.findById(id).isPresent())
            oldPayment = paymentRepository.findById(id).get();
        else
            return null;
        oldPayment.setPaymentMethod(payment.getPaymentMethod());
        oldPayment.setAmount(payment.getAmount());
        oldPayment.setPaymentStatus(payment.isPaymentStatus());
        oldPayment.setTrip(payment.getTrip());
        return paymentRepository.save(oldPayment);
    }

    public void deletePayment(Long id)
    {
        if (paymentRepository.findById(id).isPresent())
            paymentRepository.deleteById(id);
    }

    public List<Payment> findPaymentsByTripId(Long tripId)
    {
        return paymentRepository.findByTripId(tripId);
    }

    public List<Payment> findByAmountThreshold(Double threshold)
    {
        return paymentRepository.findByAmountGreaterThan(threshold);
    }

    public boolean isPaymentHasValidData (Payment payment)
    {
        if (payment.getPaymentMethod().isEmpty() || payment.getPaymentMethod() == null)
            return false;
        else if (payment.getAmount() == 0) {
            return false;
        }
        else return payment.getTrip() != null;
    }
}
