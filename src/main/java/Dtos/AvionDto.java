package Dtos;

import lombok.Data;
import Entities.*;
import Enums.*;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
@Data
public class AvionDto {
    private int id;
    private int consommation;
    private int capaciteCarburant;
    @Enumerated(EnumType.STRING)
    private TypeAvion typeAvion;
    private ThreeDPosition threeDpOsition;
}
