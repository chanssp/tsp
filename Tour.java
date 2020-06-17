package penalty;

import java.util.LinkedList;
import java.util.List;

public class Tour {
	public List<Vertex> tour;
	
	public Tour() {
		tour = new LinkedList<>();
	}
	
	public void addVertex(Vertex v) {
		tour.add(v);
	}
	
	public Double totalDistance() {
		double sum = 0.0;
		for(int i=1; i<this.tour.size()-1; i++) {
			double dist = Vertex.distance(tour.get(i), tour.get(i-1));
			sum += dist;
		}
		return sum;
	}
}
