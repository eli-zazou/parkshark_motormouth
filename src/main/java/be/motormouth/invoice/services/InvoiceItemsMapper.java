package be.motormouth.invoice.services;

import be.motormouth.allocation.services.mapper.AllocationMapper;
import be.motormouth.invoice.dto.InvoiceItemsDTO;
import be.motormouth.invoice.entities.InvoiceItem;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class InvoiceItemsMapper {
    public static List<InvoiceItemsDTO> toDTO(Collection<InvoiceItem> invoiceItems) {
        return invoiceItems.stream().map(InvoiceItemsMapper::toDTO).collect(Collectors.toList());
    }
    public static InvoiceItemsDTO toDTO(InvoiceItem invoiceItem){
        return new InvoiceItemsDTO(invoiceItem.getId()
                , AllocationMapper.mapToDto(invoiceItem.getAllocation())
                , invoiceItem.getPrice());
    }
}
