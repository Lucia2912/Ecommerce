package com.abmccoder.abmc.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity(name="carts")
@Table(name="carts")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Carts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @Getter @Setter private int amount;
    @Getter @Setter private double price;
    @Getter @Setter private boolean delivered;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    @Getter @Setter private Product product_id;

    @ManyToOne
    @JoinColumn(name = "clientid")
    @JsonIgnore
    @Getter @Setter private Client clientid;
}
