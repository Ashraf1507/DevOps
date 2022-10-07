package com.agi.core;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "matieres")
@Data
public class Matiere {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "intitule")
    private String intitule;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "module_id")
    private Module module;
}