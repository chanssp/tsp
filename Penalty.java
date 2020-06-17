package penalty;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Penalty {
	static String inputFilename = "/Users/parkchanmin/Desktop/rl11849.tsp.txt";
	
	static Integer numVertices;
	static Vertex[] vertices;
	
	public static void main(String args[]) {
		try {
			Scanner inputScanner = new Scanner(new FileInputStream(inputFilename));
			
			for (int intro=0; intro<5; intro++) {
				inputScanner.nextLine();
				if (intro == 2) {
					// to get the number of vertices from input txt file
					String[] hello = inputScanner.nextLine().split(" ");
					numVertices = Integer.parseInt(hello[2]);
				}
			}


			vertices = new Vertex[numVertices];
			for(int i=0;i<numVertices;i++){
				vertices[i] = new Vertex();
				vertices[i].index = inputScanner.nextInt();
				vertices[i].x = inputScanner.nextDouble();
				vertices[i].y = inputScanner.nextDouble();
			}
			inputScanner.close();
			System.out.print("done scanning\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Tour solution = solve();
		for (int i=0; i<solution.tour.size(); i++) {
			System.out.print(solution.tour.get(i).index + " ");
		}
		System.out.print("\n" + solution.totalDistance());
	}
	
	private static Tour solve() {
		Tour tour = makeRandomTour();
		double min = tour.totalDistance();
		for(int i=0; i<1; i++) {
			Tour temp = makeRandomTour();
			double dist = temp.totalDistance();
			if (dist <= min) {
				tour = temp;
				min = dist;
			}
		}
		return tour;
	}
	
	private static Tour makeRandomTour() {
		Tour tour = new Tour();
		List<Vertex> remaining = new LinkedList<Vertex>(Arrays.asList(vertices.clone()));
		
		tour.addVertex(vertices[0]);
		remaining.remove(0);
		while(!remaining.isEmpty()) {
			Random random = new Random();
			int sizeRemaining = (int)Math.ceil((double)(0.4 * remaining.size()));
			double min = 10000;
			int ind = 0;
			for(int i=0; i<sizeRemaining; i++) {
				int j = random.nextInt(remaining.size());
				double dist = Vertex.distance(tour.tour.get(tour.tour.size() - 1), remaining.get(j));
				if (dist < min) {
					min = dist;
					ind = j; 
				}
			}
			tour.addVertex(remaining.get(ind));
			remaining.remove(ind);
		}
		tour.addVertex(vertices[0]);
		return tour;
	}
}
