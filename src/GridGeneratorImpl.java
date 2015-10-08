import java.util.ArrayList;
import java.util.Scanner;

public class GridGeneratorImpl {

	public static void main(String[] args) {
		System.out.println("생성하고 싶은 낱말퍼즐의 크기(홀수)를 입력하세요.");
		Scanner scan = new Scanner(System.in);
		int gridSize = scan.nextInt();
		
		GridGenerator puzzle = new GridGenerator(gridSize);
		puzzle.makeAtOnce();
		puzzle.showGrid();
		
		ArrayList<int[]> everyBlock = new ArrayList<int[]>();
		everyBlock = puzzle.getEveryInfoOfBlocks();
		
		TextIO.writeFile("11x11.txt"); // 생선된 그리드의 블록에 대한 정보를 텍스트문서에 쓰기
		
		for (int i = 0; i < everyBlock.size(); i++) {
			int[] temp = everyBlock.get(i);
			for (int j = 0; j < temp.length - 1; j++) {
				TextIO.put(temp[j] + ", ");
			}
			TextIO.put(temp[temp.length - 1]);
			TextIO.putln();
		}
		System.out.print(puzzle.openedSquareCounter()); // 열린 칸이 몇 개인지 카운트
	}
}


