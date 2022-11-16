package com.kamus2.amarok.Dto;

import javax.validation.constraints.NotBlank;

public class dtoProjects {
    @NotBlank
    private String project;
    @NotBlank
    private String description;
    @NotBlank
    private String img;

    public dtoProjects() {
    }

    public dtoProjects(String project, String description, String img) {
        this.project = project;
        this.description = description;
        this.img = img;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
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
