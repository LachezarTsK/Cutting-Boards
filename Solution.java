import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int numberOfQueries = scanner.nextInt();

		for (int i = 0; i < numberOfQueries; i++) {
			int numberOfRows = scanner.nextInt();
			int numberOfColumns = scanner.nextInt();

			Cut[] cuts = new Cut[numberOfRows + numberOfColumns - 2];
			int index = 0;

			for (int row = 0; row < numberOfRows - 1; row++) {
				int cost_horizontalCut = scanner.nextInt();
				Cut nextCut = new Cut(cost_horizontalCut);
				nextCut.horizintalCut = true;
				cuts[index] = nextCut;
				index++;
			}
			for (int column = 0; column < numberOfColumns - 1; column++) {
				int cost_verticalCut = scanner.nextInt();
				Cut nextCut = new Cut(cost_verticalCut);
				nextCut.verticalCut = true;
				cuts[index] = nextCut;
				index++;
			}
			long result = calculateMinimumCuttingCost(cuts, i);
			System.out.println(result);
		}
		scanner.close();
	}

	/**
	 * The method calculates the minimum cost for cutting a board into 1x1 squares.
	 * 
	 * @return minimum cost for board cutting, modulo (10^9+7)
	 */
	public static long calculateMinimumCuttingCost(Cut[] cuts, int x) {

		int chunksOfRows = 1;
		int chunksOfColumns = 1;
		long minimumCost = 0;
		int moduloDivisor = (int) Math.pow(10, 9) + 7;
		Arrays.sort(cuts);

		for (int i = 0; i < cuts.length; i++) {
			if (cuts[i].horizintalCut) {
				minimumCost += (long) cuts[i].cost * chunksOfColumns;
				chunksOfRows++;
			}
			/**
			 * Instead of "else{}", applying "else if(cuts[i].verticalCut){}" for better
			 * readability, at the expense of one additional "if-check".
			 */
			else if (cuts[i].verticalCut) {
				minimumCost += (long) cuts[i].cost * chunksOfRows;
				chunksOfColumns++;
			}
		}
		return minimumCost % moduloDivisor;
	}
}

class Cut implements Comparable<Cut> {
	int cost;
	/**
	 * A boolean value only of "horizintalCut" or only of "verticalCut" can do 
	 * the job as well. Both are applied for better readability.
	 */
	boolean horizintalCut;
	boolean verticalCut;

	public Cut(int cost) {
		this.cost = cost;
	}

	/**
	 * Sort from highest to lowest cost.
	 */
	@Override
	public int compareTo(Cut arg0) {
		return arg0.cost - this.cost;
	}
}
