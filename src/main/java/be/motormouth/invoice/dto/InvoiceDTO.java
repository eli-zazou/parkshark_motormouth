package be.motormouth.invoice.dto;

import be.motormouth.invoice.entities.InvoiceStatus;
import be.motormouth.member.dto.MemberDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record InvoiceDTO (
        long id,
        LocalDateTime invoiceDate,
        MemberDto member,
        LocalDate creationDate,
        LocalDate expirationDate,
        InvoiceStatus invoiceStatus,
        List<InvoiceItemsDTO> invoiceItems) {
}
