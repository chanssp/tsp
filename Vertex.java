package penalty;

public class Vertex {
	public Integer index;
	public Double x;
	public Double y;
	
	public static Double distance(Vertex a, Vertex b) {
		double dist = Math.sqrt((a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y));
		return dist;
	}
}
