package com.abmccoder.abmc.entities;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name="products")
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
}
