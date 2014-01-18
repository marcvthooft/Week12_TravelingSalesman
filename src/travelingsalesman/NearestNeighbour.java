//1.	Kies startnode.
//2.	Bezoek de kortste edge vanuit huidige node naar onbezochte node.
//3.	Herhaal 2 tot alle nodes bezocht zijn.
//4.	Verbind laatst bezochte node met startnode.

package travelingsalesman;

import java.util.LinkedList;
import java.util.ListIterator;

class Vertex
{
    public final String name;
    public Edge[] adjacencies;
    public double minDistance = Double.POSITIVE_INFINITY;
    public Vertex previous;
    public Boolean unvisited = true;
    public Vertex(String argName) { name = argName; }
}

class Edge
{
    public final Vertex target;
    public final double weight;
    public Edge(Vertex argTarget, double argWeight)
    { target = argTarget; weight = argWeight; }
}

public class NearestNeighbour {
    
    /**
     * Berekent de Hamiltonian Cycle ahv Nearest Neigbour algoritm
     * @param source
     * @return Hamiltonian Cycle
     */
    public static LinkedList<Vertex> computeHamiltonianCycle(Vertex source) {
        LinkedList<Vertex> path = new LinkedList<Vertex>();
        Vertex u = source;
        //gaat door tot elke vertex is bezocht
        while (u.unvisited) {   
            u.unvisited = false;
            path.add(u);
            Edge shortestEdge = u.adjacencies[0];
            //kies kortste edge naar onbezochte vertex
            for (Edge e: u.adjacencies) {   
                if (e.target.unvisited && e.weight<shortestEdge.weight) {
                    shortestEdge = e;
                }
            }
            u = shortestEdge.target;
        }
        path.add(source);   //maak de cycle compleet
        return path;
    }

    public static void main(String[] args) {        
        Vertex A = new Vertex("A");
	Vertex B = new Vertex("B");
	Vertex C = new Vertex("C");
	Vertex D = new Vertex("D");
	Vertex E = new Vertex("E");
        Vertex F = new Vertex("F");

        A.adjacencies = new Edge[]{
            new Edge(B,6), new Edge(C,8), new Edge(D,4),
            new Edge(E,5), new Edge(F,7) };
        B.adjacencies = new Edge[]{
            new Edge(A,6), new Edge(C,5), new Edge(D,7),
            new Edge(E,6), new Edge(F,3) };
        C.adjacencies = new Edge[]{
            new Edge(A,8), new Edge(B,5), new Edge(D,1),
            new Edge(E,8), new Edge(F,9) };
        D.adjacencies = new Edge[]{
            new Edge(A,4), new Edge(B,7), new Edge(C,1),
            new Edge(E,5), new Edge(F,6) };
        E.adjacencies = new Edge[]{
            new Edge(A,5), new Edge(B,6), new Edge(C,8),
            new Edge(D,5), new Edge(F,2) };
        F.adjacencies = new Edge[]{
            new Edge(A,7), new Edge(B,3), new Edge(C,9),
            new Edge(D,6), new Edge(E,2) };
        
        LinkedList<Vertex> path = computeHamiltonianCycle(A);
        ListIterator<Vertex> pathIter = path.listIterator();
        System.out.print("Hamiltonian Cycle:");
        while (pathIter.hasNext()) {
            System.out.print(" " + pathIter.next().name);
        }
        System.out.print("\n");
    }
}
