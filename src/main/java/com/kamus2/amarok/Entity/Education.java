package com.kamus2.amarok.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nameEd;
    private String descriptionEd;

    public Education() {
    }

    public Education(String nameEd, String descriptionEd) {
        this.nameEd = nameEd;
        this.descriptionEd = descriptionEd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameEd() {
        return nameEd;
    }

    public void setNameEd(String nameEd) {
        this.nameEd = nameEd;
    }

    public String getDescriptionEd() {
        return descriptionEd;
    }

    public void setDescriptionEd(String descriptionEd) {
        this.descriptionEd = descriptionEd;
    }
    
    
}
