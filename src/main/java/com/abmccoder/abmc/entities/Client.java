package com.abmccoder.abmc.entities;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity(name="clients")
@Table(name="clients")
@NoArgsConstructor @ToString @EqualsAndHashCode
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;

    @Column (name = "name")
    @Getter @Setter private String name;
    @Column (name = "lastname")
    @Getter @Setter private String lastname;
    @Column (name = "docnumber")
    @Getter @Setter private String docnumber;

    @OneToMany(mappedBy = "client_id", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter @Setter private List<Invoice> invoices;

    @OneToMany(mappedBy = "clientid", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter @Setter private List<Carts> carts;
}
