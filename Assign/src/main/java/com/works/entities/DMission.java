package com.works.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class DMission {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mid;


    private Long cid;


    @Size(min = 2, max = 500)
    @NotNull
    @NotEmpty
    String title;


    @Size(min = 2, max = 500)
    @NotNull
    @NotEmpty
    String description;

}
