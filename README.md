# TFG-24 - Automatic Dynamic Unit and Integration Testing with GitHub Copilot (ChatGPT)
- Here I'll be storing all the projects of my TFG: 2.II 2023-2024. IT Faculty. EHU/UPV.
# Estructure:
## Projects:
### II-233.SoftwareEngineering2
- This directory is comprised of projects and functions developed for the Software Engineering II course.
- I will be implementing and testing those projects for validating automated tests.
## Test Structure:
### Test Class Structure:
- The automatic tests have this naming:
    - \<funciton-name>\<testing-agent>\<type-of-test>Tests
        - \<testing-agent>: Is the name of the Large Language Model (LLM) that has generated the tests.
    - The rest of the tests are assumed to be developed manually and to be correct.
### Test Name Structure:
- \<function-name>\<type-of-test>Test\<case-number>
    - **\<function-name>**: The name of the function to be tested.
    - **\<type-of-test>**: The type which the test belongs to:
        - **DWC**: Dynamic White Box Condition Unit Test.
        - **DWCD**: Dynamic Whit Box Condition Decision Unit Test.
        - **DBPV**: Dynamic Black Box Equivalence Partition and Limit Value Unit Test.
    - **\<case-number>**: The case number referenced in the testing test table. You can find the tables in the README.md file of each project.
## Test Creation Procedure:
- All the test procedures are documented in the */doc* directory of each class.
