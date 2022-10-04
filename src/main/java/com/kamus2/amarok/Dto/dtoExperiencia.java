package com.kamus2.amarok.Dto;

import javax.validation.constraints.NotBlank;


public class dtoExperiencia {
    @NotBlank
    private String nameE;
    @NotBlank
    private String descriptionExp;

    public dtoExperiencia() {
    }

    public dtoExperiencia(String nameE, String descriptionExp) {
        this.nameE = nameE;
        this.descriptionExp = descriptionExp;
    }

    public String getNameE() {
        return nameE;
    }

    public void setNameE(String nameE) {
        this.nameE = nameE;
    }

    public String getDescriptionExp() {
        return descriptionExp;
    }

    public void setDescriptionExp(String descriptionExp) {
        this.descriptionExp = descriptionExp;
    }
    
    
    
}
