/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kamus2.amarok.Dto;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author matia
 */
public class dtoPersona {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String description;
    @NotBlank
    private String img;

    public dtoPersona() {
    }

    public dtoPersona(String name, String surname, String description, String img) {
        this.name = name;
        this.surname = surname;
        this.description = description;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    

   
    
}
