import java.util.ArrayList;
import java.util.Scanner;

public class GridGeneratorImpl {

	public static void main(String[] args) {
		System.out.println("�����ϰ� ���� ���������� ũ��(Ȧ��)�� �Է��ϼ���.");
		Scanner scan = new Scanner(System.in);
		int gridSize = scan.nextInt();
		
		GridGenerator puzzle = new GridGenerator(gridSize);
		puzzle.makeAtOnce();
		puzzle.showGrid();
		
		ArrayList<int[]> everyBlock = new ArrayList<int[]>();
		everyBlock = puzzle.getEveryInfoOfBlocks();
		
		TextIO.writeFile("11x11.txt"); // ������ �׸����� ��Ͽ� ���� ������ �ؽ�Ʈ������ ����
		
		for (int i = 0; i < everyBlock.size(); i++) {
			int[] temp = everyBlock.get(i);
			for (int j = 0; j < temp.length - 1; j++) {
				TextIO.put(temp[j] + ", ");
			}
			TextIO.put(temp[temp.length - 1]);
			TextIO.putln();
		}
		System.out.print(puzzle.openedSquareCounter()); // ���� ĭ�� �� ������ ī��Ʈ
	}
}


