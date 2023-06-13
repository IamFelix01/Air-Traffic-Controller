package Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Aeroport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nomAeroport;
    private int nbrPistes;
    private int nbrPlaceAuSol;
    private int tempAtenteAuSol;
    private int tempAcceAuxPistes;
    private int delaiAntiCollision;
    private int tempDecolageAtterissage;
    private int dureeBoucleDAttentEnVol;
    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="TwoDPosition_Id")
    private TwoDPosition twoDPosition;
}