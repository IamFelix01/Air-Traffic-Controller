package Entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trajet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="aeroportDepart_Id")
    private Aeroport aeroportDepart;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="aeroportArriver_Id")
    private Aeroport aeroportArriver;

    private double distance;

    @OneToMany(mappedBy = "trajet")
    private List<Vol> vols;
}
