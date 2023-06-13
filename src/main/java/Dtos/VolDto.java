package Dtos;

import lombok.Data;

import java.util.ArrayList;
@Data
public class VolDto {
        private int id;

        private Integer heureDepart;

        private AvionDto avionDto;

        private ArrayList<AeroportDto> chemin;
}
