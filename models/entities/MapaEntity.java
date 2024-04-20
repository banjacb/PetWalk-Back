package com.example.PetWalkback.models.entities;

import com.example.PetWalkback.base.BaseEntity;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "mapa")
public class MapaEntity implements BaseEntity<Integer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "koordinata_X", nullable = false)
    private Double koordinataX;
    @Basic
    @Column(name="koordinata_Y", nullable = false)
    private Double koordinataY;
    @Basic
    @Column(name="naziv_objekta", nullable = false)
    private String nazivObjekta;
    @Basic
    @Column(name="opis_objekta", nullable = false)
    private String opisObjekta;
    @Basic
    @Column(name="putanja", nullable = false)
    private String putanja;

}