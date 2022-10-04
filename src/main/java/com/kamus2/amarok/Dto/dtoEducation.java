package com.kamus2.amarok.Dto;

import javax.validation.constraints.NotBlank;

public class dtoEducation {
    @NotBlank
    private String nameEd;
    @NotBlank
    private String descriptionEd;

    public dtoEducation() {
    }

    public dtoEducation(String nameEd, String descriptionEd) {
        this.nameEd = nameEd;
        this.descriptionEd = descriptionEd;
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
