package com.abmccoder.abmc.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name="invoices")
@Entity(name = "invoices")
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
    @JsonIgnore
    @Getter @Setter(AccessLevel.PUBLIC) private Client client_id;

}
