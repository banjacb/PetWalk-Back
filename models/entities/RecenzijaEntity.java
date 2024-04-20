package com.example.PetWalkback.models.entities;

import com.example.PetWalkback.base.BaseEntity;
import javax.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;


@Data
@Entity
@Table(name = "recenzija")
@EntityListeners(AuditingEntityListener.class)
public class RecenzijaEntity implements BaseEntity<Integer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "Komentar", nullable = false, length = 45)
    private String komentar;
    @Basic
    @Column(name = "Ocjena", nullable = true)
    private Integer ocjena;
    @Column(name="Datum",nullable = false, updatable = false)
    @CreatedDate
    private Date datum;
    @ManyToOne
    @JoinColumn(name = "od_id", referencedColumnName = "id", nullable = false)
    private KorisnikEntity korisnikOd;
    @ManyToOne
    @JoinColumn(name = "za_id", referencedColumnName = "id", nullable = false)
    private KorisnikEntity korisnikZa;
}
