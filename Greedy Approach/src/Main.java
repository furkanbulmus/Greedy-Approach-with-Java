import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		int n = 50, p = 5, c = 5;
		int ctrlFile = 0;
		int[] y = new int[50];
		int[] salary = new int[311];

		try {
			File demand = new File("yearly_player_demand.txt");
			Scanner in = new Scanner(demand);
			String line = in.nextLine();
			while (in.hasNextLine()) {
				line = in.nextLine();
				if (line != null) {
					if (line.length() == 3)
						y[ctrlFile] = Integer.parseInt(line.substring(2, 3));
					else if (line.length() == 4)
						y[ctrlFile] = Integer.parseInt(line.substring(3, 4));
					else
						y[ctrlFile] = Integer.parseInt(line.substring(3, 5));
					ctrlFile++;
				}
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		ctrlFile = 1;
		salary[0] = 0;

		try {
			File salaries = new File("players_salary.txt");
			Scanner in = new Scanner(salaries);
			String line = in.nextLine();
			while (in.hasNextLine()) {
				line = in.nextLine();
				if (line != null) {
					int length = line.length();
					switch (length) {
					case 3:
						salary[ctrlFile] = Integer.parseInt(line.substring(2, 3));
						break;
					case 4:
						salary[ctrlFile] = Integer.parseInt(line.substring(2, 4));
						break;
					case 5:
						salary[ctrlFile] = Integer.parseInt(line.substring(3, 5));
						break;
					case 6:
						salary[ctrlFile] = Integer.parseInt(line.substring(3, 6));
						break;
					default:
						salary[ctrlFile] = Integer.parseInt(line.substring(4, 7));
						break;
					}
					ctrlFile++;
				}
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
		int remainingPlayers = 0;
		int minCost = 0;

		for (int i = 0; i < n; i++) {
			if (y[i] >= p) {
				y[i] -= remainingPlayers;
				minCost += (y[i] - p) * c;
				remainingPlayers = 0;
			}
			else {
				y[i] -= remainingPlayers;
				if ((p - y[i]) * salary[p - y[i]] < 0) {
					minCost += (p - y[i]) * salary[p - y[i]];
					remainingPlayers = p - y[i];
				}
				else {
					minCost += 0;
					remainingPlayers = 0;
				}
			}
		}
		
		System.out.println("Minimum cost to promote players: " + minCost);
		

		

	}
}

