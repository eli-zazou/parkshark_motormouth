package be.motormouth.invoice.services;

import be.motormouth.invoice.dto.InvoiceDTO;
import be.motormouth.invoice.entities.Invoice;
import be.motormouth.member.services.MemberMapper;

import java.util.Collection;
import java.util.stream.Collectors;

public class InvoiceMapper {
    public static Collection<InvoiceDTO> toDTO(Collection<Invoice> invoices) {
        return invoices.stream().map(InvoiceMapper::toDTO).collect(Collectors.toList());
    }
    public static InvoiceDTO toDTO(Invoice invoice){
        return new InvoiceDTO(invoice.getId()
                , invoice.getInvoiceDate()
                , MemberMapper.toDto(invoice.getMember())
                , invoice.getCreationDate()
                , invoice.getExpirationDate()
                , invoice.getInvoiceStatus()
                , InvoiceItemsMapper.toDTO(invoice.getInvoiceItems()));
    }
}
