package com.mymvc.MyMVC.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 3,max = 25, message = "Min - Max length is 3-25!")
    @NotBlank(message = "name cannot be empty")
    private String name;

    @Email(message = "Input a valid email!")
    @NotBlank(message = "name cannot be empty")
    private String email;


}
