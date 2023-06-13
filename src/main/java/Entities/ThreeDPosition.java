package Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("3DP")
public class ThreeDPosition extends Position{
    private Double z;
}
