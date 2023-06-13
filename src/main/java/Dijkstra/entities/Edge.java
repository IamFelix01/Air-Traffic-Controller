package Dijkstra.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Edge {
    public int AeroportDepartId;
    public int AeroportArriverId;
    public int weight;

    public Edge(int AeroportDepartId, int AeroportArriverId, int weight)
    {
        this.AeroportDepartId = AeroportDepartId;
        this.AeroportArriverId = AeroportArriverId;
        this.weight = weight;
    }
}
