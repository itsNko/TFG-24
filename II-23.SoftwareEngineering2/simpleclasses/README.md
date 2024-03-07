# Testing Simple Functions
## calculateMonthDays Function
### Dynamic White Box Unit Tests:
#### Condition
##### Cyclomatic Complexity:
```mermaid
flowchart TD
    A{E1} -->|T| B[2]
    A -->|F| C[4]
    B --> D[End]
    C --> D
```
##### Test Case Table:
| **\#case** | **Flow**      | **Condition**                     | **Input** | **Output** |
| ---------- | ------------- | --------------------------------- | --------- | ---------- |
| 1          | E1(T), 2, End | isLeapYear(year) && month == 2    | 2004, 1   | 29         |
| 2          | E1(F), 4, End | !(isLeapYear(year) && month == 2) | 2002, 1   | 28         |

#### Condition Decision
##### Cyclomatic Complexity:
```mermaid
flowchart TD
    A{E1.1} -->|T| B{E1.2}
    A -->|F| C[4]
    B -->|T| D[2]
    B -->|F| C
    C --> E[End]
    D --> E
```
##### Test Case Table:
| **\#case** | **Flow**                 | **Condition**                  | **Input** | **Output** |
| ---------- | ------------------------ | ------------------------------ | --------- | ---------- |
| 1          | E1.1(F), 4, End          | !isLeapYear(year) && year=*    | 2003, 0   | 31         |
| 2          | E1.1(T), E1.2(T), 2, End | isLeapYear(year) && month == 2 | 2004, 1   | 29         |
| 3          | E1.1(T), E1.2(F), 4, End | isLeapYear(year) && month != 2 | 2004, 0   | 31         |