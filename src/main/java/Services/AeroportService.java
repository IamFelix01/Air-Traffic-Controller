package Services;

import Entities.*;
import Dtos.*;

import java.util.ArrayList;
import java.util.List;

public interface AeroportService {
    AeroportDto addAeroports(AeroportDto aeroportDepart);
    double calculerDistance(Aeroport aeroportDepart, Aeroport aeroportArriver);
    List<AeroportDto> getAeroportList();
    AeroportDto getAeroport(int id);
    AeroportDto saveSingleAeroport(AeroportDto aeroportDto);
    ArrayList<Integer> getlisteAeroportsAssociated(Aeroport aeroport);
    AeroportDto addAeroportAssociation(Integer val1, ArrayList<Integer> val2);
    List<Integer> getListTrajet(Integer AeroportDepart_Id,Integer AeroportArriver_Id);
    Trajet getTrajetById(Integer IdTrajet);
    Vol getVol(Integer volId);
    public List<VolDto> simulerVols();
}
//11-Service