package com.agi.core;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "modules")
@Data
public class Module {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "intitule")
    private String intitule;

    @Column(name = "v_horaire")
    private Integer vHoraire;

}