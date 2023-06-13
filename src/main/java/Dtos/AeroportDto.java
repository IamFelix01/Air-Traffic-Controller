package Dtos;

import lombok.Data;

import java.util.ArrayList;

@Data
public class AeroportDto {

    private String nomAeroport;
    private int nbrPistes;
    private int nbrPlaceAuSol;
    private int tempAtenteAuSol;
    private int tempAcceAuxPistes;
    private int delaiAntiCollision;
    private int tempDecolageAtterissage;
    private int dureeBoucleDAttentEnVol;
    private Double axeX;
    private Double axeY;

    private ArrayList<Integer> listeAeroport;
}
