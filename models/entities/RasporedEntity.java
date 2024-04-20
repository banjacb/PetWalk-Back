package com.example.PetWalkback.models.entities;

import com.example.PetWalkback.base.BaseEntity;
import javax.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Data
@Entity
@Table(name = "raspored")
//@EntityListeners(AuditingEntityListener.class)
public class RasporedEntity implements BaseEntity<Integer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "Vrijeme_cuvanja", nullable = false)
    private Double vrijemeCuvanja;
    @Basic
    @Column(name="Ukupna_cijena",nullable = false)
    private Double ukupnaCijena;
    @Basic
    @Column(name="Datum",nullable = false)
    private Date datum;
    @ManyToOne
    @JoinColumn(name = "korisnik_id", referencedColumnName = "id", nullable = false)
    private KorisnikEntity korisnik;
    @OneToOne
    @JoinColumn(name = "ljubimac_id", referencedColumnName = "id", nullable = false)
    private LjubimacEntity ljubimac;
}