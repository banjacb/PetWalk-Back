package com.example.PetWalkback.models.entities;

import com.example.PetWalkback.base.BaseEntity;
import javax.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "kategorija")
public class KategorijaEntity implements BaseEntity<Integer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "kategorija", nullable = false, length = 45)
    private String kategorija;
    @OneToMany(mappedBy = "kategorija")
    private List<OglasEntity> oglass;
}
