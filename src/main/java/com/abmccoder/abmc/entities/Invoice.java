package com.abmccoder.abmc.entities;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="invoice")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter @Setter
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;

    @Getter @Setter private LocalDateTime created_at;
    @Getter @Setter private double total;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @Getter @Setter(AccessLevel.PUBLIC) private Client client_id;


    @OneToMany(mappedBy = "invoicedetail_id", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter @Setter private List<InvoiceDetail> detalleComprobantes;
}
