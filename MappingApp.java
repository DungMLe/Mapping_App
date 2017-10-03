import java.util.Scanner;
public class MappingApp {

	public static void main(String[] aargs){
		Scanner input = new Scanner(System.in);
		
		System.out.println("Welcome to the Mapping App! Please hit enter to begin!");
		String s = input.nextLine();
		System.out.println("The map is loading...");
		for(int i = 0; i < 5; i++){
			System.out.print("* ");
			try{
				Thread.sleep(500);
			}
			catch(InterruptedException e){
				
			}
		}
		System.out.println();
		Graph graph = new Graph();
		boolean load = graph.loadData("MapData.txt");
		System.out.println(graph.printMap());
		
		String choice;
		do{
		    graph = new Graph();
			load = graph.loadData("MapData.txt");
			System.out.print("Enter current location: ");
			String currentPos = input.nextLine();
			System.out.print("Enter destination: ");
			String destination = input.nextLine();
			
			graph.findPath(currentPos, destination);
			
			System.out.println(graph);
			System.out.print("Continue? Press X to exit: ");
			choice = input.nextLine();
		}while(!choice.equals("X") && !choice.equals("x"));
		System.out.println("The Map is Closed! See you again.");
		
	}
}
