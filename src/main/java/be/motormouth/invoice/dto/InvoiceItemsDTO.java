package be.motormouth.invoice.dto;

import be.motormouth.allocation.dto.AllocationDto;
import be.motormouth.allocation.entities.Allocation;
import be.motormouth.invoice.entities.Invoice;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public record InvoiceItemsDTO (
    long id,
    AllocationDto allocation,
    double price) {
}
