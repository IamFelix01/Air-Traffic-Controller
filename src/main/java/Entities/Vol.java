package Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vol {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;

    private Integer heureDepart;

    @ManyToOne
    private Avion avion;

    @ManyToOne
    private Trajet trajet;
}
