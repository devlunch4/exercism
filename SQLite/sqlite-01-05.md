# SQLite Problem 01-05  
https://exercism.org/tracks/sqlite

## 1. Hello, World!

```sql
INSERT INTO hello_world (greeting)
VALUES ('Goodbye, Mars!');
```

---

## 2. Darts

### English Summary:
This is a dart scoring problem where points are determined by where the dart lands on a target with concentric circles:

- **Outside the target** (radius > 10): **0 points**
- **Outer circle** (5 < radius ≤ 10): **1 point**
- **Middle circle** (1 < radius ≤ 5): **5 points**
- **Inner circle** (radius ≤ 1): **10 points**

Given the coordinates `(x, y)` where a dart lands, calculate the score.

### 한글 요약:
이 문제는 다트 점수 계산 문제로, 동심원으로 이루어진 과녁의 어느 부분에 다트가 꽂히느냐에 따라 점수가 결정됩니다:

- **과녁 밖** (반지름 > 10): **0점**
- **바깥 원** (5 < 반지름 ≤ 10): **1점**
- **중간 원** (1 < 반지름 ≤ 5): **5점**
- **안쪽 원** (반지름 ≤ 1): **10점**

다트가 꽂힌 좌표 `(x, y)`가 주어졌을 때, 얻는 점수를 계산하는 문제입니다.

### Hint
**Schema:**
```sql
CREATE TABLE "darts" ("x" REAL, "y" REAL, "score" INTEGER);
```
**Task:** Update the `darts` table and set the score based on `x` and `y` values.

### Solution
```sql
UPDATE darts
SET score =
CASE
    WHEN SQRT(x*x + y*y) <= 1 THEN 10
    WHEN SQRT(x*x + y*y) <= 5 THEN 5
    WHEN SQRT(x*x + y*y) <= 10 THEN 1
    ELSE 0
END;
```

### 설명:
- `score` 값을 `CASE` 문을 이용해 설정
- `(0,0)`에서 `(x,y)`까지의 거리 계산 (피타고라스 정리 활용)
- 거리별 점수 설정 (10, 5, 1)
- 거리가 `10`보다 크면 (모든 원 밖에 있으면) `0점`

---

## 3. Difference of Squares

### Problem Statement:
Find the difference between the square of the sum and the sum of the squares of the first **N** natural numbers.

- **Square of the sum:**  
  \[
  (1 + 2 + ... + 10)^2 = 55^2 = 3025
  \]
- **Sum of the squares:**  
  \[
  1^2 + 2^2 + ... + 10^2 = 385
  \]
- **Difference:**  
  \[
  3025 - 385 = 2640
  \]

Researching the best algorithm for the problem is encouraged.

### 한글 설명:
처음 **N**개의 자연수에 대해 **합의 제곱과 제곱의 합의 차이**를 구하세요.

- **합의 제곱:**  
  \[
  (1 + 2 + ... + 10)^2 = 55^2 = 3025
  \]
- **제곱의 합:**  
  \[
  1^2 + 2^2 + ... + 10^2 = 385
  \]
- **차이:**  
  \[
  3025 - 385 = 2640
  \]

이 문제의 효율적인 해결책을 스스로 찾는 것이 아니라, 연구하여 최선의 알고리즘을 찾는 것이 중요합니다.

### Hint
**Schema:**
```sql
CREATE TABLE "difference-of-squares" ("number" INT, "property" TEXT, "result" INT);
```
**Task:** Update the `difference-of-squares` table based on `number` and `property` fields.

### Solution
```sql
UPDATE "difference-of-squares"
SET result =
CASE property
    WHEN 'squareOfSum' THEN pow((number+1) * number / 2, 2)
    WHEN 'sumOfSquares' THEN number * (number+1) * (2*number+1) / 6
    WHEN 'differenceOfSquares' THEN pow((number+1) * number / 2, 2) - number * (number+1) * (2*number+1) / 6
END;
```

### 설명:
- `CASE` 문을 사용하여 `property` 값에 따라 `result`를 계산
- **합의 제곱 (`squareOfSum`)**  
  - 공식: \((n(n+1)/2)^2\)
- **제곱의 합 (`sumOfSquares`)**  
  - 공식: \(n(n+1)(2n+1)/6\)
- **차이 계산 (`differenceOfSquares`)**  
  - `squareOfSum - sumOfSquares`

---

## 4. Gigasecond

### Problem Statement:
This problem introduces a simpler approach to measuring time using only seconds with metric prefixes instead of traditional time units.

- **Kilosecond** = 1,000 seconds
- **Megasecond** = 1,000,000 seconds
- **Gigasecond** = 1,000,000,000 seconds

### 한글 설명:
이 문제는 **전통적인 시간 단위 대신 초(second)에 미터법 접두사를 사용**하여 시간을 측정하는 더 단순한 접근법을 소개합니다.

- **킬로초 (Kilosecond)** = 1,000초
- **메가초 (Megasecond)** = 1,000,000초
- **기가초 (Gigasecond)** = 1,000,000,000초

### Hint
**Schema:**
```sql
CREATE TABLE "gigasecond" ("moment" TEXT, "result" TEXT);
```
**Task:** Update the `gigasecond` table and set `result` based on `moment`.

### Solution
```sql
UPDATE gigasecond
SET result = REPLACE(datetime(moment, '+1000000000 seconds'), ' ', 'T');
```

---

## 5. Grains

### Problem Statement:
Calculate the number of grains of wheat on a chessboard given that the number on each square doubles.

A wise servant who saved the life of a prince asked the king for grains of wheat:
- **Square 1**: 1 grain
- **Square 2**: 2 grains
- **Square 3**: 4 grains
- ...
- **Square 64**: ???

Calculate:
1. **How many grains are on a given square**
2. **Total number of grains on the chessboard**

### 한글 설명:
체스판에 놓인 밀알의 수를 계산하세요.  
각 칸마다 밀알의 수가 두 배로 늘어납니다.

- **1번 칸:** 1개  
- **2번 칸:** 2개  
- **3번 칸:** 4개  
- ...  
- **64번 칸:** ???  

다음을 구하세요:
1. 특정 칸에 있는 밀알의 개수
2. 체스판 전체의 총 밀알 개수

### Hint
**Schema:**
```sql
CREATE TABLE "grains" ("task" TEXT, "square" INT, "result" INT);
```
**Task:** Update the `grains` table based on `task` (and `square` fields).

### Solution
```sql
UPDATE grains
SET result =
CASE task
    WHEN 'single-square' THEN pow(2, square - 1)
    WHEN 'total' THEN pow(2, 64) - 1
END;
```

### 설명:
- `CASE` 문을 사용하여 `task` 값에 따라 계산 수행
- 특정 칸(`single-square`)의 경우: \(2^{(square-1)}\)
- 전체(`total`)의 경우: \(2^{64} - 1\)
