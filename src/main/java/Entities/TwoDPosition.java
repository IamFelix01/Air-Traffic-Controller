package Entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("2DP")
public class TwoDPosition extends Position{
    @ManyToOne
    @JoinColumn(name="carte_Id")
    private Carte carte;
}
