package com.abmccoder.abmc.entities;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="products")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;

    @Getter @Setter private String description;
    @Getter @Setter private String code;
    @Getter @Setter private int stock;
    @Getter @Setter private double price;

    @OneToMany(mappedBy = "product_id", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter @Setter private List<InvoiceDetail> detalleComprobantes;
}
