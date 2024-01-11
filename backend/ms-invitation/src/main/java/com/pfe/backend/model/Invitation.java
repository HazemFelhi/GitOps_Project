package com.pfe.backend.model;
import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor 
@NoArgsConstructor


@Entity
@Table(name = "invitation")

public class Invitation implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    
    private String email ;
    private Long entrepriseId;

}
