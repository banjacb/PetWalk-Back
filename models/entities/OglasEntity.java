package com.example.PetWalkback.models.entities;

import com.example.PetWalkback.base.BaseEntity;
import javax.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Data
@Entity
@Table(name = "oglas")
@EntityListeners(AuditingEntityListener.class)
public class OglasEntity implements BaseEntity<Integer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "Sadrzaj", nullable = false, length = 45)
    private String sadrzaj;
    //@Basic
    @Column(name="Datum",nullable = false, updatable = false)
    @CreatedDate
    private Date datum;
    @Basic
    @Column(name="Status",nullable = false)
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "korisnik_id", referencedColumnName = "id", nullable = false)
    private KorisnikEntity korisnik;
    @ManyToOne
    @JoinColumn(name = "kategorija_id", referencedColumnName = "id", nullable = false)
    private KategorijaEntity kategorija;

}