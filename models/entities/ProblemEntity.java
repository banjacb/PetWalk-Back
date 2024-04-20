package com.example.PetWalkback.models.entities;

import com.example.PetWalkback.base.BaseEntity;
import lombok.Data;
import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "problem")
public class ProblemEntity implements BaseEntity<Integer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "Sadrzaj", nullable = false, length = 45)
    private String sadrzaj;
    @Basic
    @Column(name="Datum", nullable = false)
    private Date datum;
    @Basic
    @Column(name="Status_problema", nullable = false)
    private boolean status_problema;
    @ManyToOne
    @JoinColumn(name = "korisnik_id", referencedColumnName = "id", nullable = false)
    private KorisnikEntity korisnik;
}
