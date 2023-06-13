package Services;

import Dijkstra.entities.Edge;
import Dijkstra.entities.Graph;
import Dijkstra.services.DijkstraImpl;
import Mappers.AeroportMapperImpl;
import Dtos.*;
import lombok.extern.slf4j.Slf4j;
import Entities.*;
import Enums.*;
import Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;

@Slf4j
@Service
@Transactional
public class AeroportServiceImpl implements AeroportService{
    @Autowired
    private VolRepository volRepository;
    @Autowired
    private AeroportRepository aeroportRepository;
    @Autowired
    private TrajetRepository trajetRepository;
    @Autowired
    private AeroportMapperImpl dtoMapper;
    @Override
    public AeroportDto addAeroports(AeroportDto aeroportDto) {
        log.info("add Aeroports");
        ArrayList<Integer> listeAeroportArriver = aeroportDto.getListeAeroport();
        Aeroport aeroportDepart=dtoMapper.fromAeroportDto(aeroportDto);
        TwoDPosition twoDPosition = new TwoDPosition();
        twoDPosition.setX(aeroportDto.getAxeX());
        twoDPosition.setY(aeroportDto.getAxeY());
        aeroportDepart.setTwoDPosition(twoDPosition);
        if(listeAeroportArriver.size()==0){
            if(aeroportDepart==null){
                return null;
            }else{
                aeroportRepository.save(aeroportDepart);
            }
        }else{
            if(aeroportDepart!=null){

                for(Integer aeroportArriver : listeAeroportArriver){
                    Aeroport aeroportA = aeroportRepository.findById(aeroportArriver).get();
                    aeroportRepository.save(aeroportDepart);
                    aeroportRepository.save(aeroportA);
                    Trajet trajet1= new Trajet();
                    trajet1.setAeroportDepart(aeroportDepart);
                    trajet1.setAeroportArriver(aeroportA);
                    double distance = calculerDistance(aeroportDepart,aeroportA);
                    trajet1.setDistance(distance);
                    trajetRepository.save(trajet1);
                }
            }
        }
        AeroportDto aeroportDto1 ;
        ArrayList<Integer> listAeroportAssocie = getlisteAeroportsAssociated(aeroportDepart);
        aeroportDto1 = dtoMapper.fromAeroport(aeroportDepart);

        aeroportDto1.setListeAeroport(listAeroportAssocie);
        return aeroportDto1;
    }
    @Override
    public double calculerDistance(Aeroport aeroportDepart, Aeroport aeroportArriver) {
        log.info("Calculing Distance");
        Double x1=aeroportDepart.getTwoDPosition().getX();
        Double y1=aeroportDepart.getTwoDPosition().getY();
        Double x2=aeroportArriver.getTwoDPosition().getX();
        Double y2=aeroportArriver.getTwoDPosition().getY();
        double distance = Math.sqrt(pow((x2-x1),2)+pow((y2-y1),2));
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        return Double.parseDouble(df.format(distance));
    }
    @Override
    public List<AeroportDto> getAeroportList() {
        log.info("get Aeroport list {}");
        List<Aeroport> listeAeroport ;
        listeAeroport = aeroportRepository.findAll();
        List<AeroportDto> listeAeroportDto =new ArrayList<>();
    for( Aeroport aeroport : listeAeroport) {
            AeroportDto aeroportDto ;
            aeroportDto = dtoMapper.fromAeroport(aeroport);
            aeroportDto.setListeAeroport(getlisteAeroportsAssociated(aeroport));
            listeAeroportDto.add(aeroportDto);
        }
        return listeAeroportDto;
    }
    @Override
    public AeroportDto getAeroport(int id) {
        log.info("trying to get aeroport {}",id);

            Aeroport aeroport = aeroportRepository.findById(id).get();
            ArrayList<Integer> listeAeroportAssocie=getlisteAeroportsAssociated(aeroport);
            AeroportDto aeroportDto ;
            aeroportDto=dtoMapper.fromAeroport(aeroport);
            aeroportDto.setListeAeroport(listeAeroportAssocie);
        return aeroportDto;
    }
    @Override
    public AeroportDto saveSingleAeroport(AeroportDto aeroportDto) {
        log.info("Saving Single Aeroport{}"+String.valueOf(aeroportDto));

        Aeroport aeroport = dtoMapper.fromAeroportDto(aeroportDto);
        Aeroport saveAeroport = aeroportRepository.save(aeroport);
        ArrayList<Integer> listeAeroportAssocie=getlisteAeroportsAssociated(saveAeroport);
        AeroportDto aeroportDto1 = dtoMapper.fromAeroport(saveAeroport);
        aeroportDto1.setListeAeroport(listeAeroportAssocie);
        return aeroportDto1;
    }

    @Override
    public ArrayList<Integer> getlisteAeroportsAssociated(Aeroport aeroport) {

        List<AeroportDto> listeAeroportDto =new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        ArrayList<Integer> aeroportAsocie = new ArrayList<>();
        List<Trajet> trajets;
        trajets = trajetRepository.findAll();
        Integer i = aeroport.getId();

        for (Trajet y : trajets) {
            int s = y.getAeroportArriver().getId();
            if ( i == s ) {
                int side1 = y.getAeroportDepart().getId();
                list.add(side1);
            }
        }
        for (Trajet w : trajets) {
            int o = w.getAeroportDepart().getId();
            if ( i == o ) {
                int side1 = w.getAeroportArriver().getId();
                list2.add(side1);
            }
        }
        aeroportAsocie.addAll(list);
        aeroportAsocie.addAll(list2);
        AeroportDto aeroportDto ;
        aeroportDto = dtoMapper.fromAeroport(aeroport);
        aeroportDto.setListeAeroport(aeroportAsocie);
        listeAeroportDto.add(aeroportDto);
        list.clear();
        list2.clear();

        return aeroportAsocie;
    }

    @Override
    public AeroportDto addAeroportAssociation(Integer val1, ArrayList<Integer> associations) {
        Aeroport aeroport=aeroportRepository.findById(val1).get() ;
        AeroportDto aeroportDto = dtoMapper.fromAeroport(aeroport);
        for( Integer  association : associations ){
            Trajet trajet3= new Trajet();
            trajet3.setAeroportDepart(aeroport);
            Aeroport aeroport1 ;
            aeroport1=aeroportRepository.findById(association).get();
            trajet3.setAeroportArriver(aeroport1);
            trajetRepository.save(trajet3);
        }
        ArrayList<Integer> listeAeroportAssociated ;
        listeAeroportAssociated=getlisteAeroportsAssociated(aeroport);
        aeroportDto.setListeAeroport(listeAeroportAssociated);
        return aeroportDto;
    }

    //Dijkstra application: ------------
    @Override
    public List<Integer> getListTrajet(Integer AeroportDepart_Id, Integer AeroportArriver_Id) {
        List<Trajet> listeTrajet ;
        listeTrajet=trajetRepository.findAll();
        List<Edge> edges = new ArrayList<>();
        for( Trajet x : listeTrajet){
            Edge edge = new Edge();
            edge.setAeroportDepartId(x.getAeroportDepart().getId());
            edge.setAeroportArriverId(x.getAeroportArriver().getId());
            edge.setWeight((int) x.getDistance());
            edges.add(edge);
        }
        int n = 8;
        Graph graph = new Graph(edges, n);
        return DijkstraImpl.findShortestPaths(graph,AeroportDepart_Id,n,AeroportArriver_Id);}

    @Override
    public Trajet getTrajetById(Integer idTrajet) {
       List<Trajet> listeTrajet;
       listeTrajet =trajetRepository.findAll();
       for( Trajet x :listeTrajet ){

           if(x.getId() == idTrajet)
               return x;
       }
        return null;
    }

    @Override
    public Vol getVol(Integer volId) {
        List<Vol> vols = volRepository.findAll();
        for(Vol x : vols){
            if (x.getId()==volId);
            return x;
        }
        return null;
    }
    @Override
    public List<VolDto> simulerVols(){
        AeroportMapperImpl aeroportMapper = new AeroportMapperImpl();
        ArrayList<VolDto> volDtos = new ArrayList<>();
        List<Vol> listVols = volRepository.findAll();
        for(Vol x: listVols){
            VolDto volDto = new VolDto();
            volDto.setId(x.getId());
            AvionDto avionDto = aeroportMapper.fromAvion(x.getAvion());
            volDto.setAvionDto(avionDto);
            volDto.setHeureDepart(x.getHeureDepart());
            List<Integer> aeroportDtso2 = getListTrajet(x.getTrajet().getAeroportDepart().getId(),x.getTrajet().getAeroportArriver().getId());
            List<AeroportDto> chemin = new ArrayList<>();
            for( Integer y : aeroportDtso2){
                chemin.add(getAeroport(y));
            }
            volDto.setChemin((ArrayList<AeroportDto>) chemin);
            volDtos.add(volDto);
        }
        for(VolDto Y: volDtos){

        }
        int temp = 0;
        for (int i = 0; i < volDtos.size(); i++) {
            for (int j = i+1; j < volDtos.size(); j++) {
                if(volDtos.get(i).getHeureDepart() > volDtos.get(j).getHeureDepart()){
                    temp=volDtos.get(i).getHeureDepart();
                    volDtos.get(i).setHeureDepart(volDtos.get(j).getHeureDepart());
                    volDtos.get(j).setHeureDepart(temp);
                }
            }
        }

            return volDtos;
    }
}
