package com.kamus2.amarok.Dto;

import javax.validation.constraints.NotBlank;


public class dtoSkills {
    @NotBlank
    private String name;
    @NotBlank
    private int porcen;

    public dtoSkills() {
    }

    public dtoSkills(String name, int porcen) {
        this.name = name;
        this.porcen = porcen;
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

    public CharSequence getNameSk() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
