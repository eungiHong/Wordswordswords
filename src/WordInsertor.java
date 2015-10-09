import java.util.ArrayList;
import edu.princeton.cs.algs4.StdRandom;

public class WordInsertor {

	private char[][] grid;
	private int gridLength;
	private int[][] blockSet;

	private static String wordSet9[] = { "agreement", "amusement", "apparatus", "attention", "authority", "behaviour",
			"committee", "condition", "digestion", "direction", "discovery", "education", "existence", "expansion",
			"insurance", "invention", "knowledge", "operation", "secretary", "selection", "statement", "structure",
			"substance", "transport" };
	private static String wordSet8[] = { "addition", "approval", "argument", "building", "business", "daughter",
			"decision", "distance", "division", "exchange", "increase", "industry", "interest", "language", "learning",
			"mountain", "ornament", "pleasure", "position", "property", "question", "reaction", "relation", "religion",
			"surprise", "teaching", "tendency" };
	private static String wordSet7[] = { "account", "against", "attempt", "balance", "because", "between", "brother",
			"chamber", "chinese", "college", "comfort", "company", "conquer", "control", "country", "current",
			"disease", "disgust", "driving", "english", "epigram", "epistle", "example", "feeling", "fiction",
			"forward", "harbour", "harmony", "hearing", "history", "impulse", "journey", "leather", "machine",
			"manager", "measure", "meeting", "monitor", "morning", "opinion", "payment", "process", "produce",
			"protest", "purpose", "quality", "reading", "request", "respect", "science", "servant", "society",
			"spanish", "speaker", "stretch", "support", "thought", "through", "thunder", "trouble", "weather",
			"writing" };
	private static String wordSet6[] = { "across", "almost", "amount", "animal", "answer", "arabic", "attack", "before",
			"belief", "breath", "butter", "button", "canvas", "chance", "chance", "change", "change", "colour",
			"copper", "cotton", "credit", "damage", "danger", "degree", "design", "desire", "detail", "divide",
			"effect", "enigma", "enough", "expert", "family", "father", "flight", "flower", "flower", "french",
			"friend", "greedy", "growth", "hearer", "humour", "insect", "jargon", "korean", "letter", "liquid",
			"little", "lotion", "market", "memory", "middle", "minute", "mother", "mother", "motion", "nation",
			"number", "person", "please", "poetry", "poison", "polish", "porter", "powder", "profit", "reason",
			"record", "regret", "reward", "rhythm", "silver", "sister", "sneeze", "stitch", "summer", "system",
			"theory", "though", "vessel", "violet", "weight", "winter", "zombie" };
	private static String wordSet5[] = { "about", "adios", "after", "again", "alone", "among", "angle", "apple", "birth",
			"blood", "brass", "bread", "brute", "burst", "cause", "chalk", "check", "chide", "cloth", "cough", "cover",
			"crack", "crime", "crush", "curve", "death", "doubt", "drink", "earth", "error", "event", "every", "field",
			"fight", "flame", "flute", "force", "force", "front", "fruit", "glass", "grain", "grape", "grass", "group",
			"guide", "hello", "jelly", "judge", "latin", "laugh", "level", "light", "limit", "linen", "mango", "metal",
			"money", "month", "music", "night", "noise", "noise", "north", "offer", "order", "other", "owner", "paint",
			"palsy", "paper", "paste", "peace", "phone", "place", "plant", "point", "power", "price", "print", "prose",
			"quite", "range", "river", "scale", "sense", "shade", "shake", "shame", "shock", "slang", "sleep", "slope",
			"smash", "smell", "smile", "smoke", "sound", "south", "space", "stage", "start", "steam", "steel", "still",
			"stone", "story", "sugar", "taste", "there", "thing", "touch", "trade", "trick", "twist", "under", "value",
			"verse", "viola", "voice", "waste", "water", "where", "while", "woman", "wound", "zebra" };
	private static String wordSet4[] = { "bike", "book", "caff", "cash", "come", "cool", "copy", "cork", "debt", "deck",
			"desk", "doll", "down", "dull", "dust", "east", "edge", "even", "ever", "fact", "fall", "fear", "fire",
			"fold", "food", "fork", "form", "free", "from", "geek", "give", "gold", "good", "gout", "grip", "hate", "have",
			"heat", "help", "here", "hole", "hope", "hour", "idea", "iron", "jean", "join", "jump", "keep", "kick",
			"kiss", "land", "land", "lead", "lift", "line", "list", "look", "loss", "love", "make", "mark", "mass",
			"meal", "meat", "milk", "mind", "mine", "mist", "move", "much", "name", "near", "need", "news", "nice",
			"note", "only", "over", "page", "pain", "part", "play", "pull", "pull", "push", "push", "rain", "rate",
			"rest", "rice", "ride", "road", "roll", "room", "rule", "safe", "salt", "sand", "seat", "seem", "self",
			"send", "shoe", "side", "sign", "silk", "sine", "size", "slip", "snow", "soap", "some", "song", "sort",
			"soup", "step", "stop", "such", "swag", "swim", "take", "talk", "test", "than", "that", "then", "this",
			"till", "time", "tree", "tune", "turn", "unit", "very", "view", "walk", "wash", "wave", "week", "well",
			"west", "when", "will", "wind", "wine", "with", "wood", "wool", "word", "work", "year", "zone" };
	private static String wordSet3[] = { "act", "air", "all", "and", "any", "arm", "art", "bad", "bag", "bit", "but",
			"can", "cap", "cat", "cry", "cup", "day", "dog", "ear", "end", "eye", "far", "fit", "for", "get", "git", "gum",
			"hop", "hot", "how", "ice", "ink", "joy", "key", "kin", "law", "leg", "let", "lip", "man", "may", "mug",
			"not", "now", "nut", "off", "oil", "old", "one", "out", "put", "ray", "rub", "run", "saw", "say", "sea",
			"sea", "see", "sex", "sit", "sky", "son", "sum", "tan", "tap", "tax", "tea", "ten", "the", "tin", "toe",
			"top", "use", "war", "wax", "way", "who", "why", "yes", "you" };
	private ArrayList<String> wordDictionary9 = new ArrayList<String>();
	private ArrayList<String> wordDictionary8 = new ArrayList<String>();
	private ArrayList<String> wordDictionary7 = new ArrayList<String>();
	private ArrayList<String> wordDictionary6 = new ArrayList<String>();
	private ArrayList<String> wordDictionary5 = new ArrayList<String>();
	private ArrayList<String> wordDictionary4 = new ArrayList<String>();
	private ArrayList<String> wordDictionary3 = new ArrayList<String>();

	
	// 그리드 그리기부: 그리드의 각 블록에 대한 정보를 담고 있는 리스트의 리스트를 입력 받아, 그리드를 그리는 부문
	public WordInsertor(int gridLength, int[][] blockSet) { // 생성자
		this.grid = new char[gridLength][gridLength];
		this.gridLength = gridLength;
		this.blockSet = blockSet;
		for (int i = 0; i < this.gridLength; i++) {
			for (int j = 0; j < this.gridLength; j++) {
				this.grid[i][j] = '*';
			}
		}
	}
	
	private void draw(int x, int y) { // 닫힌 칸을 열기, i.e., 그리기
		this.grid[x][y] = 'O';
	}

	public void blockDrawer() { // 블록의 정보를 가지고, 블록 그리기
		for (int i = 0; i < blockSet.length; i++) {
			if (blockSet[i][3] == 100) { // 블록이 수평방향일 경우,
				for (int j = 0; j < blockSet[i][2]; j++) { // 블록의 길이만큼,
					draw(blockSet[i][0], blockSet[i][1] + j); // 왼쪽에서 오른쪽으로 그리기
				}
			} else if (blockSet[i][3] == -100) { // 블록이 수직방향일 경우,
				for (int j = 0; j < blockSet[i][2]; j++) { // 블록의 길이만큼,
					draw(blockSet[i][0] + j, blockSet[i][1]); // 위에서 아래로 그리기
				}
			}
		}
	}
	// 그리드 그리기부 끝
	
	
	// 낱말 삽입부: 그리드 그리기부에서 그려진 그리드에 대해서 낱말 사전으로부터 단어를 가져와 삽입하는 부문.
	public void reload() { // 각 낱말리스트의 낱말을 셔플하고, 대응하는 길이의 낱말사전에 삽입하기
		shuffle(wordSet9);
		for (int j = 0; j < wordSet9.length; j++) {
			wordDictionary9.add(wordSet9[j]);
		}
		shuffle(wordSet8);
		for (int j = 0; j < wordSet8.length; j++) {
			wordDictionary8.add(wordSet8[j]);
		}
		shuffle(wordSet7);
		for (int j = 0; j < wordSet7.length; j++) {
			wordDictionary7.add(wordSet7[j]);
		}
		shuffle(wordSet6);
		for (int j = 0; j < wordSet6.length; j++) {
			wordDictionary6.add(wordSet6[j]);
		}
		shuffle(wordSet5);
		for (int j = 0; j < wordSet5.length; j++) {
			wordDictionary5.add(wordSet5[j]);
		}
		shuffle(wordSet4);
		for (int j = 0; j < wordSet4.length; j++) {
			wordDictionary4.add(wordSet4[j]);
		}
		shuffle(wordSet3);
		for (int j = 0; j < wordSet3.length; j++) {
			wordDictionary3.add(wordSet3[j]);
		}
	}

	private void shuffle(Object[] a) { // 셔플 알고리듬
		int N = a.length;
		for (int i = 0; i < N; i++) {
			int r = StdRandom.uniform(i + 1);
			exch(a, i, r);
		}
	}

	private void exch(Object[] a, int i, int j) {
		Object swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	public void fillInTheBlock() { // 블록에 낱말채우기
		for (int i = 0; i < blockSet.length; i++) { // 모든 블록을 도는 반복문
			int row = blockSet[i][0]; // 블록의 x축 좌표
			int col = blockSet[i][1]; // 블록의 y축 좌표
			int length = blockSet[i][2]; // 블록의 길이에 맞는 낱말사전 선택
			if (length == 9) {  // 블록의 길이가 9인 경우,
				for (int j = 0; j < wordDictionary9.size(); j++) { // 낱말의 길이가 9인 낱말사전 돌기
					String word = wordDictionary9.get(j);
					if (blockSet[i][3] == 100) { // 블록이 수평일 경우, 수평방향으로 낱말을 입력
						int count = 0;
						for (int k = 0; k < word.length(); k++) {
							// k번째 칸이 이미 차있는 칸이고, 그 문자가 낱말의 k번째 문자와 다를 경우, 다른 낱말로 넘어감    
							if (grid[row][col + k] != 'O' && grid[row][col + k] != word.charAt(k)) {
								count++;
							}
						}
						if (count == 0) { // 해당 블록이 비어있거나, 입력하려는 낱말와 일치할 경우
							for (int z = 0; z < word.length(); z++) {
								grid[row][col + z] = word.charAt(z); // 낱말을 삽입
							}
							wordDictionary9.remove(j); // 블록에 삽입한 낱말을 사전에서 삭제
							break;
						}
					}
					if (blockSet[i][3] == -100) { // 블럭이 수직일 경우, 수직방향으로 낱말를 입력
						int count = 0;
						for (int k = 0; k < word.length(); k++) {
							if (grid[row + k][col] != 'O' && grid[row + k][col] != word.charAt(k)) {
								count++;
							}
						}
						if (count == 0) {
							for (int z = 0; z < word.length(); z++) {
								grid[row + z][col] = word.charAt(z);
							}
							wordDictionary9.remove(j);
							break;
						}
					}
				}
			} else if (length == 8) {
				// 낱말의 길이가 8인 낱말사전 돌기
				// 위와 같은 패턴으로 길이가 3인 낱말사전까지 반복
				for (int j = 0; j < wordDictionary8.size(); j++) {
					String word = wordDictionary8.get(j);
					if (blockSet[i][3] == 100) {
						int count = 0;
						for (int k = 0; k < word.length(); k++) {
							if (grid[row][col + k] != 'O' && grid[row][col + k] != word.charAt(k)) {
								count++;
							}
						}
						if (count == 0) {
							for (int z = 0; z < word.length(); z++) {
								grid[row][col + z] = word.charAt(z);
							}
							wordDictionary8.remove(j);
							break;
						}
					}
					if (blockSet[i][3] == -100) {
						int count = 0;
						for (int k = 0; k < word.length(); k++) {
							if (grid[row + k][col] != 'O' && grid[row + k][col] != word.charAt(k)) {
								count++;
							}
						}
						if (count == 0) {
							for (int z = 0; z < word.length(); z++) {
								grid[row + z][col] = word.charAt(z);
							}
							wordDictionary8.remove(j);
							break;
						}
					}
				}
			} else if (length == 7) {
				for (int j = 0; j < wordDictionary7.size(); j++) {
					String word = wordDictionary7.get(j);
					if (blockSet[i][3] == 100) {
						int count = 0;
						for (int k = 0; k < word.length(); k++) {
							if (grid[row][col + k] != 'O' && grid[row][col + k] != word.charAt(k)) {
								count++;
							}
						}
						if (count == 0) {
							for (int z = 0; z < word.length(); z++) {
								grid[row][col + z] = word.charAt(z);
							}
							wordDictionary7.remove(j);
							break;
						}
					}
					if (blockSet[i][3] == -100) { 
						int count = 0;
						for (int k = 0; k < word.length(); k++) {
							if (grid[row + k][col] != 'O' && grid[row + k][col] != word.charAt(k)) {
								count++;
							}
						}
						if (count == 0) {
							for (int z = 0; z < word.length(); z++) {
								grid[row + z][col] = word.charAt(z);
							}
							wordDictionary7.remove(j);
							break;
						}
					}
				}
			} else if (length == 6) {
				for (int j = 0; j < wordDictionary6.size(); j++) {
					String word = wordDictionary6.get(j);
					if (blockSet[i][3] == 100) { 
						int count = 0;
						for (int k = 0; k < word.length(); k++) {
							if (grid[row][col + k] != 'O' && grid[row][col + k] != word.charAt(k)) {
								count++;
							}
						}
						if (count == 0) {
							for (int z = 0; z < word.length(); z++) {
								grid[row][col + z] = word.charAt(z);
							}
							wordDictionary6.remove(j);
							break;
						}
					}
					if (blockSet[i][3] == -100) { 
						int count = 0;
						for (int k = 0; k < word.length(); k++) {
							if (grid[row + k][col] != 'O' && grid[row + k][col] != word.charAt(k)) {
								count++;
							}
						}
						if (count == 0) {
							for (int z = 0; z < word.length(); z++) {
								grid[row + z][col] = word.charAt(z);
							}
							wordDictionary6.remove(j);
							break;
						}
					}
				}
			} else if (length == 5) {
				for (int j = 0; j < wordDictionary5.size(); j++) {
					String word = wordDictionary5.get(j);
					if (blockSet[i][3] == 100) { 
						int count = 0;
						for (int k = 0; k < word.length(); k++) {
							if (grid[row][col + k] != 'O' && grid[row][col + k] != word.charAt(k)) {
								count++;
							}
						}
						if (count == 0) {
							for (int z = 0; z < word.length(); z++) {
								grid[row][col + z] = word.charAt(z);
							}
							wordDictionary5.remove(j);
							break;
						}
					}
					if (blockSet[i][3] == -100) {
						int count = 0;
						for (int k = 0; k < word.length(); k++) {
							if (grid[row + k][col] != 'O' && grid[row + k][col] != word.charAt(k)) {
								count++;
							}
						}
						if (count == 0) {
							for (int z = 0; z < word.length(); z++) {
								grid[row + z][col] = word.charAt(z);
							}
							wordDictionary5.remove(j);
							break;
						}
					}
				}
			} else if (length == 4) {
				for (int j = 0; j < wordDictionary4.size(); j++) {
					String word = wordDictionary4.get(j);
					if (blockSet[i][3] == 100) {
						int count = 0;
						for (int k = 0; k < word.length(); k++) {
							if (grid[row][col + k] != 'O' && grid[row][col + k] != word.charAt(k)) {
								count++;
							}
						}
						if (count == 0) {
							for (int z = 0; z < word.length(); z++) {
								grid[row][col + z] = word.charAt(z);
							}
							wordDictionary4.remove(j);
							break;
						}
					}
					if (blockSet[i][3] == -100) {
						int count = 0;
						for (int k = 0; k < word.length(); k++) {
							if (grid[row + k][col] != 'O' && grid[row + k][col] != word.charAt(k)) {
								count++;
							}
						}
						if (count == 0) {
							for (int z = 0; z < word.length(); z++) {
								grid[row + z][col] = word.charAt(z);
							}
							wordDictionary4.remove(j);
							break;
						}
					}
				}
			} else if (length == 3) {
				for (int j = 0; j < wordDictionary3.size(); j++) {
					String word = wordDictionary3.get(j);
					if (blockSet[i][3] == 100) {
						int count = 0;
						for (int k = 0; k < word.length(); k++) {
							if (grid[row][col + k] != 'O' && grid[row][col + k] != word.charAt(k)) {
								count++;
							}
						}
						if (count == 0) {
							for (int z = 0; z < word.length(); z++) {
								grid[row][col + z] = word.charAt(z);
							}
							wordDictionary3.remove(j);
							break;
						}
					}
					if (blockSet[i][3] == -100) {
						int count = 0;
						for (int k = 0; k < word.length(); k++) {
							if (grid[row + k][col] != 'O' && grid[row + k][col] != word.charAt(k)) {
								count++;
							}
						}
						if (count == 0) {
							for (int z = 0; z < word.length(); z++) {
								grid[row + z][col] = word.charAt(z);
							}
							wordDictionary3.remove(j);
							break;
						}
					}
				}
			}
		}
	}
	// 낱말 삽입부

	
	// 크로스워드 검사부: 낱말 삽입부에 의해서 생성된 크로스워드가 정형의 크로스워드인지, 즉 낱말이 제대로 삽입되었는지 확인하는 부문.
	public void showPuzzle() { // 크로스워드 보여주기
		for (int i = 0; i < this.gridLength; i++) {
			for (int j = 0; j < this.gridLength; j++) {
				System.out.print(this.grid[i][j] + " ");
			}
			System.out.println("");
		}
		System.out.println(puzzleExaminer());
	}

	public int puzzleExaminer() { // 낱말로 채워지지 않은 빈 칸의 개수를 확인
		int count = 0;
		for (int i = 0; i < this.gridLength; i++) {
			for (int j = 0; j < this.gridLength; j++) {
				if (this.grid[i][j] == 'O')
					count++;
			}
		}
		return count;
	}
	// 크로스워드 검사부 끝
	
	public void drawAtOnce() { // 한 번에 싹~ 만들기
		blockDrawer();
		reload();
		fillInTheBlock();
		if (puzzleExaminer() != 0) { // 채워지지 않은 빈칸이 있을 경우, 다시 처음부터 시작
			drawAtOnce();
		} else if (puzzleExaminer() == 0) {
			showPuzzle();
		}
	}
}
