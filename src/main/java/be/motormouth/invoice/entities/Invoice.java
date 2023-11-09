package be.motormouth.invoice.entities;

import be.motormouth.member.entities.Member;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "INVOICE")
public class Invoice {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoice_seq")
    @SequenceGenerator(name = "invoice_seq", sequenceName = "invoice_seq", allocationSize = 1)
    private long id;
    private LocalDateTime invoiceDate;
    @ManyToOne
    @JoinColumn(name = "FK_MEMBER_ID")
    private Member member;
    private LocalDate creationDate;
    private LocalDate expirationDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private InvoiceStatus invoiceStatus;
    @OneToMany(mappedBy = "invoice", fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private List<InvoiceItem> invoiceItems;

    public Invoice() {
    }

    public Invoice(LocalDateTime invoiceDate, Member member, LocalDate creationDate, LocalDate expirationDate, InvoiceStatus invoiceStatus, List<InvoiceItem> invoiceItems) {
        this.invoiceDate = invoiceDate;
        this.member = member;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
        this.invoiceStatus = invoiceStatus;
        this.invoiceItems = invoiceItems;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getInvoiceDate() {
        return invoiceDate;
    }

    public Member getMember() {
        return member;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public InvoiceStatus getInvoiceStatus() {
        return invoiceStatus;
    }

    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", invoiceDate=" + invoiceDate +
                ", member=" + member +
                ", creationDate=" + creationDate +
                ", expirationDate=" + expirationDate +
                ", invoiceStatus=" + invoiceStatus +
                ", invoiceItems=" + invoiceItems +
                '}';
    }
}
