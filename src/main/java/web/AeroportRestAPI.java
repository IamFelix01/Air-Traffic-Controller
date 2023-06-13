package web;

import Dtos.AeroportDto;
import Dtos.VolDto;
import Repositories.VolRepository;
import Services.AeroportService;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin("*")
public class AeroportRestAPI {
    @Autowired
    private AeroportService aeroportService;
    @Autowired
    private VolRepository volRepository;

    @GetMapping("/aeroportList")
    public List<AeroportDto> getAeroportList(){
        return aeroportService.getAeroportList();
    }

    @GetMapping("/aeroports/{id}")
    public AeroportDto getAeroports(@PathVariable(name="id") int aeroportId){
        return aeroportService.getAeroport(aeroportId);
    }

    @PostMapping("/saveAeroport")
    public AeroportDto saveAeroport(@RequestBody AeroportDto aeroportDto){
        log.info(String.valueOf(aeroportDto));
        return aeroportService.saveSingleAeroport(aeroportDto);
    }

    @PostMapping("/addAeroports")
    public AeroportDto addAeroports(@RequestBody AeroportDto aeroportDto) {

        return aeroportService.addAeroports(aeroportDto);
    }
    @PostMapping("/addAeroportAssociations")
    public AeroportDto addAeroportAssociation(@RequestBody String body) {

        JsonMapper mapper = new JsonMapper();
        Map<Object, Object> map;
        ArrayList<Integer> listAeroportAssociation = null;
        Integer aeroportDepart = null;
        try {
                // convert JSON string to Map
                map = mapper.readValue(body, Map.class);
                Object obj = map.get("val1");
                String aeroport = (String) obj;
                aeroportDepart = Integer.parseInt(aeroport);

                //System.out.println(m);
                Object listAeroportAssociationObj = map.get("associations");
                listAeroportAssociation = (ArrayList) listAeroportAssociationObj;

            } catch (Exception e) {
                e.printStackTrace();
            }

        return aeroportService.addAeroportAssociation(aeroportDepart, listAeroportAssociation);
    }
    @GetMapping(path = "/voler/{AeroportDepartId}/{AeroportArriverId}/{AvionId}/{heureDepart}")
    public List<AeroportDto> voler(@PathVariable Integer AeroportDepartId, @PathVariable Integer AeroportArriverId, @PathVariable Integer AvionId, @PathVariable Integer heureDepart) {

        // check which one is not empty and perform logic
        List<Integer> chemain = null;
        List<AeroportDto> listeVole=new ArrayList<>();
        if (!(AeroportDepartId.equals("empty") && AeroportArriverId.equals("empty") && AvionId.equals("empty") && heureDepart.equals("empty"))) {
            chemain = aeroportService.getListTrajet(AeroportDepartId, AeroportArriverId);
            for(Integer x : chemain){
                listeVole.add(aeroportService.getAeroport(x));
            }
        }
        return listeVole;
    }
    @GetMapping(path="/simulerVols")
    public List<VolDto> simulerVols(){
        return aeroportService.simulerVols();
    }
}
