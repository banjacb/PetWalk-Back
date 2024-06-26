package com.example.PetWalkback.models.entities;

import com.example.PetWalkback.base.BaseEntity;
import javax.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name = "izvjestaj")
public class IzvjestajEntity implements BaseEntity<Integer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "Sadrzaj", nullable = false, length = 45)
    private String sadrzaj;
    @Basic
    @Column(name="Datum",nullable = false)
    private Date datum;
    @ManyToOne
    @JoinColumn(name = "korisnik_id", referencedColumnName = "id", nullable = false)
    private KorisnikEntity korisnik;
    @ManyToOne
    @JoinColumn(name = "ljubimac_id", referencedColumnName = "id", nullable = false)
    private LjubimacEntity ljubimac;
}
