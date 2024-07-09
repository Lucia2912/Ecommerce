package com.abmccoder.abmc.entities;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="invoice_detail")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class InvoiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @Getter @Setter private int amount;
    @Getter @Setter private double price;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @Getter @Setter private Product product_id;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    @Getter @Setter private Invoice invoice_id;
}
