# SQLite Problem 06-10  
https://exercism.org/tracks/sqlite

## 6. Leap

### English Summary:
A leap year (in the Gregorian calendar) occurs:
- In every year that is evenly divisible by 4.
- Unless the year is evenly divisible by 100, in which case it's only a leap year if the year is also evenly divisible by 400.

Some examples:
- 1997 was not a leap year as it's not divisible by 4.
- 1900 was not a leap year as it's not divisible by 400.
- 2000 was a leap year!

### 한글 요약:
윤년 (그레고리력 기준)은 다음과 같은 경우에 발생합니다:
- 4로 나누어 떨어지는 모든 해.
- 단, 그 해가 100으로 나누어 떨어지면, 그 해가 윤년이 되려면 400으로도 나누어 떨어져야 합니다.

**몇 가지 예시:**
- 1997년은 4로 나누어 떨어지지 않아서 윤년이 아닙니다.
- 1900년은 400으로 나누어 떨어지지 않아서 윤년이 아닙니다.
- 2000년은 윤년이었습니다!

### 참고 유튜브 - 한글 
[링크: YTN 사이언스-윤년/윤달/윤초](https://www.youtube.com/watch?v=I3QVnQIxPKE)

### Hint  
**Schema:**
```sql
CREATE TABLE "leap" ( "year" INT, "is_leap" BOOL);
```
**Task:** update the leap table and set the is_leap based on the year field.

### Solution
```sql
UPDATE leap
SET is_leap = 
    CASE 
        WHEN (year % 4 = 0 AND year % 100 != 0) OR (year % 400 = 0) 
        THEN 1 
        ELSE 0 
    END;
```

### 설명:
**윤년 규칙 적용**:
- `year % 4 = 0`: 4로 나누어 떨어지는지 확인.
- `year % 100 != 0`: 100으로 나누어 떨어지지 않는지 확인.
- `year % 400 = 0`: 400으로 나누어 떨어지는지 확인.
- 이 조건들을 조합하여 `(year % 4 = 0 AND year % 100 != 0) OR (year % 400 = 0)`로 표현.  

**CASE 문**:
- 조건이 참이면 `is_leap`을 1(TRUE)로 설정.
- 거짓이면 `is_leap`을 0(FALSE)로 설정.

---

## 7. Raindrops  

### English Original:
**Introduction**  
Raindrops is a slightly more complex version of the FizzBuzz challenge, a classic interview question.

**Instructions**  
Your task is to convert a number into its corresponding raindrop sounds.  

If a given number:  
- is divisible by 3, add "Pling" to the result.  
- is divisible by 5, add "Plang" to the result.  
- is divisible by 7, add "Plong" to the result.  
- is not divisible by 3, 5, or 7, the result should be the number as a string.  

**Examples**  
- 28 is divisible by 7, but not 3 or 5, so the result would be "Plong".  
- 30 is divisible by 3 and 5, but not 7, so the result would be "PlingPlang".  
- 34 is not divisible by 3, 5, or 7, so the result would be "34".

### 한글 번역:
**소개**  
Raindrops는 FizzBuzz 챌린지의 조금 더 복잡한 버전으로, 전형적인 인터뷰 질문입니다.

**지시사항**  
당신의 임무는 숫자를 해당하는 빗방울 소리로 변환하는 것입니다.  

주어진 숫자가:  
- 3으로 나누어 떨어지면 결과에 "Pling"을 추가합니다.  
- 5로 나누어 떨어지면 결과에 "Plang"을 추가합니다.  
- 7로 나누어 떨어지면 결과에 "Plong"을 추가합니다.  
- 3, 5, 7 중 어느 것으로도 나누어 떨어지지 않으면 결과는 숫자를 문자열로 나타낸 것입니다.  

**예시**  
- 28은 7로 나누어 떨어지지만 3이나 5로는 나누어 떨어지지 않으므로 결과는 "Plong"입니다.  
- 30은 3과 5로 나누어 떨어지지만 7로는 나누어 떨어지지 않으므로 결과는 "PlingPlang"입니다.  
- 34는 3, 5, 7 중 어느 것으로도 나누어 떨어지지 않으므로 결과는 "34"입니다.


### Hint
**Schema:**
```sql
CREATE TABLE "raindrops" ("number" INT, "sound" TEXT);
```
**Task:** update the raindrops table and set the sound based on the number.

### Solution
```sql
UPDATE raindrops
SET sound = 
   IIF(number % 3 = 0, 'Pling', '') || 
   IIF(number % 5 = 0, 'Plang', '') || 
   IIF(number % 7 = 0, 'Plong', '') || 
   IIF(
       (number % 3 = 0) OR (number % 5 = 0) OR (number % 7 = 0), 
       '', 
       CAST(number AS TEXT)
   );
```

### 설명:
**`IIF` 함수**:
- SQLite에서 `IIF(condition, value_if_true, value_if_false)`는 조건이 참일 때와 거짓일 때의 값을 선택하는 간단한 조건문입니다. 
**문자열 연결 연산자 (`||`)**:
- SQLite에서 `||`는 문자열을 연결하는 연산자입니다. 각 `IIF`의 결과를 순차적으로 붙여서 최종 `sound` 값을 만듭니다.
**조건 처리**:
- **`IIF(number % 3 = 0, 'Pling', '')`**:  
숫자가 3으로 나누어 떨어지면 "Pling"을 추가, 아니면 빈 문자열(`''`)을 추가.
- **`IIF(number % 5 = 0, 'Plang', '')`**:  
숫자가 5로 나누어 떨어지면 "Plang"을 추가, 아니면 빈 문자열(`''`)을 추가.
- **`IIF(number % 7 = 0, 'Plong', '')`**:  
숫자가 7로 나누어 떨어지면 "Plong"을 추가, 아니면 빈 문자열(`''`)을 추가.
- 이 세 부분은 각각 독립적으로 실행되며, 조건이 맞으면 해당 문자열이 결과에 추가됩니다.
**마지막 `IIF` - 숫자 처리**:
- **`IIF((number % 3 = 0) OR (number % 5 = 0) OR (number % 7 = 0), '', CAST(number AS TEXT))`**:
- `(number % 3 = 0) OR (number % 5 = 0) OR (number % 7 = 0)`:  
숫자가 3, 5, 7 중 하나라도 나누어 떨어지는지 확인.
- 참이면 빈 문자열(`''`)을 추가 (이미 앞에서 "Pling", "Plang", "Plong"이 추가되었으므로 더 붙일 필요 없음).
- 거짓이면 `CAST(number AS TEXT)`로 숫자를 문자열로 변환해 추가.
**결과 조합**:
- 모든 `IIF`의 결과가 `||`로 연결되며, 최종적으로 `sound` 열에 저장됩니다.

---

## 8. Resistor Color
### English Summary:
In this exercise, you need to create a program to handle resistor color codes used on a Raspberry Pi.  
Resistors have resistance values indicated by color bands, where the first two bands represent numbers.  
Each color corresponds to a specific digit (e.g., black = 0, brown = 1, etc.).  
Your task is to update the result column in the color_code table with the correct numerical value based on the color column.  
The colors and their values are: black (0), brown (1), red (2), orange (3), yellow (4), green (5), blue (6), violet (7), grey (8), white (9).   
```sql
CREATE TABLE "color_code" ("color" TEXT, "result" INT);.
```

### 한글 설명:
이 연습에서는 라즈베리 파이를 사용해 무언가를 만들 때 필요한 저항의 색상 코드를 다룹니다.  
저항은 저항 값을 색상 띠로 나타내며, 처음 두 개의 띠는 숫자를 의미합니다. 각 색상은 특정 숫자에 대응됩니다 (예: 검정 = 0, 갈색 = 1 등).  
당신의 임무는 color_code 테이블의 color 열에 있는 색상에 따라 result 열을 올바른 숫자 값으로 업데이트하는 것입니다.  
색상과 값은 다음과 같습니다: 검정(0), 갈색(1), 빨강(2), 주황(3), 노랑(4), 초록(5), 파랑(6), 보라(7), 회색(8), 흰색(9).  
테이블 스키마는: 
```sql
CREATE TABLE "color_code" ("color" TEXT, "result" INT);.
```

### Hint
**Schema:**
```sql
CREATE TABLE "color_code" ("color" TEXT, "result" INT);
```
**Task:** update the color_code table and set the result based on the color.

### Solution
```sql
UPDATE color_code
SET result = 
    CASE color
        WHEN 'black' THEN 0
        WHEN 'brown' THEN 1
        WHEN 'red' THEN 2
        WHEN 'orange' THEN 3
        WHEN 'yellow' THEN 4
        WHEN 'green' THEN 5
        WHEN 'blue' THEN 6
        WHEN 'violet' THEN 7
        WHEN 'grey' THEN 8
        WHEN 'white' THEN 9
        ELSE NULL
    END;
```

---

## 9. Resistor Color Duo
### English Summary:
The task is to create a program that decodes the resistance value of a resistor based on its color-coded bands.  
The program should take color names as input (e.g., "brown", "green") and output a two-digit number representing the resistance value.  
The first two colors correspond to the first two digits of the resistance value, and subsequent colors are ignored.  
Each color has a corresponding numerical value: black (0), brown (1), red (2), orange (3), yellow (4), green (5), blue (6), violet (7), grey (8), and white (9).  

### Korean Translation:
이 과제는 색상 코딩된 밴드를 기반으로 저항기의 저항 값을 디코딩하는 프로그램을 만드는 것입니다.  
프로그램은 색상 이름(예: "갈색", "녹색")을 입력으로 받아 저항 값을 나타내는 두 자리 숫자를 출력해야 합니다.  
처음 두 색상은 저항 값의 처음 두 자리에 해당하며, 이후 색상은 무시됩니다.  
각 색상은 해당 숫자 값을 가집니다: 검정 (0), 갈색 (1), 빨강 (2), 주황 (3), 노랑 (4), 녹색 (5), 파랑 (6), 보라 (7), 회색 (8), 흰색 (9).  

### Hint
**Schema:**
```sql
CREATE TABLE "grains" ("task" TEXT, "square" INT, "result" INT);
```
**Task:** update the color_code table and set the result based on the two colors.

### Solution
```sql
UPDATE color_code
SET result = 
    (CASE color1
        WHEN 'black' THEN 0
        WHEN 'brown' THEN 1
        WHEN 'red' THEN 2
        WHEN 'orange' THEN 3
        WHEN 'yellow' THEN 4
        WHEN 'green' THEN 5
        WHEN 'blue' THEN 6
        WHEN 'violet' THEN 7
        WHEN 'grey' THEN 8
        WHEN 'white' THEN 9
        ELSE NULL
    END * 10) +
    (CASE color2
        WHEN 'black' THEN 0
        WHEN 'brown' THEN 1
        WHEN 'red' THEN 2
        WHEN 'orange' THEN 3
        WHEN 'yellow' THEN 4
        WHEN 'green' THEN 5
        WHEN 'blue' THEN 6
        WHEN 'violet' THEN 7
        WHEN 'grey' THEN 8
        WHEN 'white' THEN 9
        ELSE NULL
    END);
```

### 설명:
**가정:**
- 테이블: color_code
- 스키마: 
  > CREATE TABLE "color_code" ("color1" TEXT, "color2" TEXT, "result" INT);  
  > color1: 첫 번째 색상  
  > color2: 두 번째 색상  
  > result: 계산된 두 자리 저항 값 (첫 번째 색상 × 10 + 두 번째 색상)  

**문제 요구사항:**
- 입력: 두 개의 색상 (color1, color2)
- 출력: 두 자리 숫자 (result), 첫 번째 색상이 10의 자리, 두 번째 색상이 1의 자리
- 색상별 값:
> black: 0, brown: 1, red: 2, orange: 3, yellow: 4  
> green: 5, blue: 6, violet: 7, grey: 8, white: 9

**구조:**
- result는 두 자리 숫자이므로, color1 값을 10의 자리로 만들기 위해 10을 곱하고, color2 값을 1의 자리로 더합니다.
- 예: "brown" (1)과 "green" (5) → 1 * 10 + 5 = 15

**CASE 문:**
- color1과 color2 각각에 대해 색상을 숫자로 변환.
- 각 색상에 대응하는 값을 문제에 명시된 대로 매핑.
- 예상치 못한 색상 입력 시 NULL 반환.

**계산:**
- (CASE color1 ... END * 10): 첫 번째 색상의 값을 10배로 만들어 10의 자리로 설정.
- \+ (CASE color2 ... END): 두 번째 색상의 값을 1의 자리로 더함.

---

## 10. Two-Fer 
### English Summary:
**Introduction Summary**  
The phrase "two-fer" comes from "two for one," meaning if you buy one item, you get another free. In a bakery holiday offer, buying one cookie gets you a second cookie free, and you decide to give the extra cookie to someone else in line.

**Instructions Summary**  
Your task is to determine what to say when giving away the extra cookie. If you know the person’s name (e.g., Do-yun), say: "One for Do-yun, one for me." If you don’t know their name, say: "One for you, one for me." Examples include "One for Alice, one for me" (known name) or "One for you, one for me" (unknown name).

### Korean Translation:
**소개 요약**  
"Two-fer"는 "two for one"에서 유래된 표현으로, 하나를 사면 하나를 무료로 받는다는 뜻입니다. 빵집의 휴일 프로모션에서 쿠키 하나를 사면 하나를 더 주는데, 당신은 추가 쿠키를 줄에 있는 다른 사람에게 주기로 결정합니다.

**지시사항 요약**  
당신의 임무는 추가 쿠키를 줄 때 할 말을 정하는 것입니다. 만약 그 사람의 이름을 안다면 (예: 도윤), 이렇게 말합니다: "도윤에게 하나, 나에게 하나." 이름을 모르면 이렇게 말합니다: "너에게 하나, 나에게 하나." 예시는 "앨리스에게 하나, 나에게 하나" (이름을 아는 경우) 또는 "너에게 하나, 나에게 하나" (이름을 모르는 경우)입니다.


<!-- ### English Summary:

---



---

### 참고:
이 문제는 SQL 쿼리와 직접 관련은 없어 보이며, 문자열 출력 로직을 묻는 것 같습니다. 만약 SQLite 쿼리로 해결해야 한다면 테이블 스키마를 추가로 제공해 주시면 쿼리를 작성해 드리겠습니다! 예를 들어, 테이블에 이름(`name`)과 결과(`dialogue`) 열이 있다고 가정하면 아래와 같은 쿼리가 가능할 수 있습니다:

#### 가정된 스키마:
```sql
CREATE TABLE "two_fer" ("name" TEXT, "dialogue" TEXT);
```

#### 예시 쿼리:
```sql
UPDATE two_fer
SET dialogue = 
    CASE 
        WHEN name IS NULL OR name = '' THEN 'One for you, one for me.'
        ELSE 'One for ' || name || ', one for me.'
    END;
```

추가 요구사항이 있으면 말씀해주세요! -->
### Hint
- **Schema:**
```sql
CREATE TABLE "twofer" ("input" TEXT, "response" TEXT);
```
- **Task:** update the twofer table and set the response based on the input.

### Solution
```sql
UPDATE twofer
SET response = 
    CASE
        WHEN input = "" THEN "One for you, one for me."
        ELSE "One for " || input || ", one for me."
    END;
```
<!-- ## no. Title
### English Summary:
### Korean Translation:
### Hint
- **Schema:**
- **Task:**
### Solution
### 설명: -->
