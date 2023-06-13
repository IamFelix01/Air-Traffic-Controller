package Entities;

import Enums.TypeAvion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Avion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int consommation;
    private int capaciteCarburant;

    @Enumerated(EnumType.STRING)
    private TypeAvion typeAvion;

    @OneToOne
    @JoinColumn(name="ThreeDposition_Id")
    private ThreeDPosition threeDpOsition;

    @OneToMany(mappedBy = "avion",fetch = FetchType.LAZY)
    private Collection<Vol> vols;
}
