package com.example.PetWalkback.models.entities;

import com.example.PetWalkback.base.BaseEntity;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cijena")
public class CijenaEntity implements BaseEntity<Integer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "Cijena", nullable = false, precision = 0)
    private Integer cijena;
    @ManyToOne
    @JoinColumn(name = "korisnik_id", referencedColumnName = "id", nullable = false)
    private KorisnikEntity korisnik;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usluga_id", referencedColumnName = "id", nullable = false)
    private UslugaEntity usluga;

}
