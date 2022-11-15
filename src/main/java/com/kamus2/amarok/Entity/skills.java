package com.kamus2.amarok.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class skills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int porcen;

    public skills() {
    }

    public skills(String name, int porcen) {
        this.name = name;
        this.porcen = porcen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPorcen() {
        return porcen;
    }

    public void setPorcen(int porcen) {
        this.porcen = porcen;
    }
    
    
    
}
