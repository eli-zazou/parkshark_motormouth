package be.motormouth.invoice.entities;

import be.motormouth.allocation.entities.Allocation;
import jakarta.persistence.*;

@Entity
@Table(name = "INVOICE_ITEM")
public class InvoiceItem {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoiceitems_seq")
    @SequenceGenerator(name = "invoiceitems_seq", sequenceName = "invoiceitems_seq", allocationSize = 1)
    private long id;
    @ManyToOne
    @JoinColumn(name = "FK_INVOICE_ID")
    private Invoice invoice;
    @ManyToOne
    @JoinColumn(name = "FK_ALLOCATION_ID")
    private Allocation allocation;
    private double price;

    public InvoiceItem() {
    }

    public InvoiceItem(Invoice invoice, Allocation allocation, double price) {
        this.invoice = invoice;
        this.allocation = allocation;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public Allocation getAllocation() {
        return allocation;
    }

    public double getPrice() {
        return price;
    }
}
