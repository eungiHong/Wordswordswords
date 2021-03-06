﻿Wordswordswords는 크로스워드(낱말퍼즐)를 자동으로 생성해 내는 프로그램입니다. 이 프로그램은 다음과 같은 부문(component)을 가집니다.

1. 그리드 생성기
2. 낱말 사전
3. 낱말 삽입기
4. 사용자 인터페이스

프로그램의 작동과정은 다음과 같습니다.
1. 그리드 생성기를 사용하여 크로스워드의 바탕이 될 그리드를 생성한다.
2. 낱말 삽입기를 사용하여 낱말 사전으로부터 가져온 낱말을 과정 1에서 생성된 그리드의 블록 안에 삽입한다.
3. 사용자 인터페이스를 이용하여 과정 2에서 생성된 크로스워드를 낱말이 숨겨진 채 전시하고, 사용자로부터 낱말을 입력받아 숨겨진 낱말과 일치할시, O를 내놓고 그렇지 않을시, X를 내놓는다.


1. 그리드 생성기

그리드 생성기는 1보다 큰 홀수(3, 5, 7, ...)를 입력받아 입력받은 홀수를 길이로 가지는 정사각형의 그리드와, 그리드 안에 들어있는 블록에 대한 정보를 내놓습니다.

(1) 용어(term)

그리드(grid): 크로스워드의 바탕을 이루는 칸(square)의 집합. 
// 제약조건: 1. 그리드의 길이는 가로와 세로가 같아야 한다. 2. 그리드의 길이는 홀수여야한다. 3. 그리드를 180도 회전하였을 때, 회전하기 전과 회전한 후의 모양이 같아야 한다.

블록(block): 낱말이 들어갈 칸의 연결체. 
// 제약조건: 1. 블록의 길이는 그리드의 길이를 넘어선 안된다. 2. 블록의 길이가 가로 세로 모두 1이어선 안된다(홀로 고립되어 있는 칸이 존재하는 경우).

칸(square): 고유한 좌표를 갖는 크로스워드의 최소단위.
닫힌 칸(closed square): 연결되어 블록을 이룰 수 없는 칸, 코드 상에 "X"로 표시됨.
열린 칸(opened square): 연결되어 블록을 이룰 수 있는 칸. 코드 상에 "O"로 표시됨.

교차점(intersection): 수직방향의 블록과 수평방향의 블록이 겹치는 칸. 


(2) 블록의 표시(representation of a block)

하나의 블록은 다음과 같이 특별한 의미가 부여된 정수의 리스트로 표시될 수 있습니다.

x축 좌표, y축 좌표, 길이, 방향(수평 또는 수직), 교차점의 개수

예시) 
1, 0, 7, 100, 3  // 좌표 (1,0)에서부터 수평방향으로 길이 7만큼 뻗어나가며 교차점을 3개 가지는 블록
0, 1, 6, -100, 0 // 좌표 (0,1)에서부터 수직방향으로 길이 6만큼 뻗어나가며 교차점을 0개 가지는 블록


(3) 그리드 크기별로 열어야 할 칸의 적정한 개수 (지극히 주관적인)
11x11: recursiveRowCloser 함수를 이용하여 (0,0)에서 부터 닫을 때, 만약 randomlyAndSymmetricallyClose 함수를 7번 호출한다면, 121칸 중 75칸 열림  / (1,0)에서부터 닫을 때, if n == 8, then 75/121 
13x13: (0,0)에서 부터 닫을 때, if n == 13, then 93/169  / (1,0)에서부터 닫을 때, if n == 17, then 93/169
15x15: (0,0)에서 부터 닫을 때, if n == 20, then 121/225  / (1,0)에서부터 닫을 때, if n == 24, then 121/225
17x17: (0,0)에서 부터 닫을 때, 만약 randomlyAndSymmetricallyCloseForLargeSize 함수를 28번 호출한다면, 289칸 중 151칸 열림  / (1,0)에서부터 닫을 때, if n == 33, then 151/289
19x19: (0,0)에서 부터 닫을 때, if n == 33, then 196/361  / (1,0)에서부터 닫을 때, if n == 38, then 196/361


(4) 하위부문(sub-components)
1) 그리드 생성부 - 그리드를 생성하는 하위부문
2) 블록 추출부 - 그리드 생성부에서 생성된 그리드가 가지고 있는 블록에 대한 정보를 리스트의 리스트의 형태로 추출하는 하위부문
3) 그리드 검사부 - 그리드 생성부에서 생성된 그리드가 제약조건을 만족하는 정형(well-formed)의 그리드인지, 또는 '쓸만한' 그리드인지 검사하는 하위부문



2. 낱말 사전
낱말 사전은 구성하기 나름이며, 영어 낱말 사전, 한국어 낱말 사전, 꽃 이름 사전, 지명 사전, 동음이의어 사전 등 다양한 사전을 구성할 수 있습니다. (코드 상에 구현한 사전은 영어의 기본 어휘 사전)


3. 낱말 삽입기

낱말 삽입기는 그리드의 길이와 블록에 대한 정보를 입력받아 낱말이 삽입된 그리드를 내놓습니다.


예시)

그리드의 길이 11과 다음과 같은,

{{5, 2, 7, 100, 3},
{1, 3, 5, -100, 3},
{5, 7, 5, -100, 3},
{3, 0, 5, 100, 2},
{3, 6, 5, 100, 2},
{6, 1, 5, -100, 2},
{0, 9, 5, -100, 2},
{6, 9, 5, -100, 2},
{7, 0, 5, 100, 2},
{7, 6, 5, 100, 2},
{9, 0, 4, 100, 2}, 
{0, 7, 4, -100, 2},
{1, 7, 4, 100, 2},
{7, 3, 4, -100, 2},
{9, 5, 3, 100, 2},
{1, 3, 3, 100, 2},
{0, 1, 5, -100, 1},
{4, 5, 3, -100, 1},
{8, 5, 3, -100, 1},
{0, 5, 3, -100, 1}};

정수 리스트의 리스트로 이루어진 블록에 대한 정보를 입력값으로 받아, 다음과 같은 크로스워드를 출력합니다.

* b * * * t * f * v * 
* r * s e a * e v e n 
* e * l * x * a * r * 
p a p e r * b r a s s 
* d * e * a * * * e * 
* * o p i n i o n * * 
* g * * * y * t * a * 
b u r s t * s h a m e 
* i * e * o * e * o * 
e d g e * f a r * n * 
* e * m * f * * * g * 

낱말 삽입기의 하위부문(sub-components)
1) 그리드 그리기부 - 그리드의 각 블록에 대한 정보를 담고 있는 리스트의 리스트를 입력 받아, 그리드를 그리는 부문
2) 낱말 삽입부 - 그리드 그리기부에 의해서 그려진 그리드에 대해서 낱말 사전으로부터 단어를 가져와 삽입하는 부문.
3) 크로스워드 검사부 - 낱말 삽입부에 의해서 생성된 크로스워드가 정형의 크로스워드인지, 즉 낱말이 제대로 삽입되었는지 확인하는 부문.


4. 사용자 인터페이스
개발 예정


5. Agenda
(1) 그리드 생성기에서 생성하는 블록에 대한 정보가 현재 중복이 제거되어 있지 않고 정렬이 되어 있지 않은데, 자동으로 1. 중복 제거, 2. 교차점이 많은 순으로 정렬, 3. 길이가 긴 순으로 정렬하여 낱말 삽입기에 전달해주는 작업 필요.(현재는 notepad++ 사용)

(2) 그리드의 크기가 커질 수록, 블록의 교차점이 많아지고, 따라서 단어가 매치되지 않을 확률이 높아지는데 (예를 들어 영어의 경우, 'bn', 'ksz'와 같이 영어의 음운구조상 아예 존재할 수 없는 문자열이 발생하거나 음운구조상 허용되어도 실제 단어로 존재하지 않는 문자열 발생), 이럴 경우 다시 이전 상태으로 되돌아갈 수 있는 backtracking algorithm 구현 필요(현재는 생성된 퍼즐을 검사하여 채워지지 않은 칸이 있을 시, 그리드를 초기화하고 검사를 통과할 때까지 생성을 반복).
