package Mappers;
import Enums.*;
import Dtos.*;
import Entities.*;
import org.springframework.stereotype.Service;

@Service
public class AeroportMapperImpl {

    public AeroportDto fromAeroport(Aeroport aeroport){
        AeroportDto aeroportDto = new AeroportDto();
        aeroportDto.setNomAeroport(aeroport.getNomAeroport());
        aeroportDto.setNbrPistes(aeroport.getNbrPistes());
        aeroportDto.setNbrPlaceAuSol(aeroport.getNbrPlaceAuSol());
        aeroportDto.setTempAtenteAuSol(aeroport.getTempAtenteAuSol());
        aeroportDto.setTempAcceAuxPistes(aeroport.getTempAcceAuxPistes());
        aeroportDto.setDelaiAntiCollision(aeroport.getDelaiAntiCollision());
        aeroportDto.setTempDecolageAtterissage(aeroport.getTempDecolageAtterissage());
        aeroportDto.setDureeBoucleDAttentEnVol(aeroport.getDureeBoucleDAttentEnVol());
        aeroportDto.setAxeX(aeroport.getTwoDPosition().getX());
        aeroportDto.setAxeY(aeroport.getTwoDPosition().getY());
        return aeroportDto;
    }
    public Aeroport fromAeroportDto(AeroportDto aeroportDto){
        Aeroport aeroport = new Aeroport();
        aeroport.setNomAeroport(aeroportDto.getNomAeroport());
        aeroport.setNbrPistes(aeroportDto.getNbrPistes());
        aeroport.setNbrPlaceAuSol(aeroportDto.getNbrPlaceAuSol());
        aeroport.setTempAtenteAuSol(aeroportDto.getTempAtenteAuSol());
        aeroport.setTempAcceAuxPistes(aeroportDto.getTempAcceAuxPistes());
        aeroport.setDelaiAntiCollision(aeroportDto.getDelaiAntiCollision());
        aeroport.setTempDecolageAtterissage(aeroportDto.getTempDecolageAtterissage());
        aeroport.setDureeBoucleDAttentEnVol(aeroportDto.getDureeBoucleDAttentEnVol());
        TwoDPosition twoDPosition = new TwoDPosition();
        twoDPosition.setX(aeroportDto.getAxeX());
        twoDPosition.setY(aeroportDto.getAxeY());
        aeroport.setTwoDPosition(twoDPosition);

        return aeroport;
    }
    public AvionDto fromAvion(Avion avion){
        AvionDto avionDto = new AvionDto();
        avionDto.setId(avion.getId());
        avionDto.setTypeAvion(avion.getTypeAvion());
        avionDto.setConsommation(avion.getConsommation());
        avionDto.setThreeDpOsition(avion.getThreeDpOsition());
        avionDto.setCapaciteCarburant(avion.getCapaciteCarburant());
        return avionDto;
    }

    public Avion fromAvionDto(AvionDto avionDtdo){
        Avion avion = new Avion();
        avion.setId(avionDtdo.getId());
        avion.setTypeAvion(avionDtdo.getTypeAvion());
        avion.setConsommation(avionDtdo.getConsommation());
        avion.setThreeDpOsition(avionDtdo.getThreeDpOsition());
        avion.setCapaciteCarburant(avionDtdo.getCapaciteCarburant());
        return avion;
    }
}
