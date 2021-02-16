Feature: Verify JDBC


  @Test
  Scenario: Run simple DB test
    Given Connect to local db
    Then Get all data from scientist table
      | id | firstName | lastName |
      | 1  | albert    | einstein |
      | 2  | isaac     | newton   |
      | 3  | marie     | curie    |

  @Test
  Scenario: Join two tables
    Given Connect to local db
    Then Get all data from Employees and Departments
      | Employee_id | Employee_name | Employee_DOB | Department_Name |
      | 1           | Alan Smith    | 1989-01-01   | Human Resources |
      | 2           | Sultan Nader  | 1992-01-01   | Human Resources |
      | 3           | Mohd Rasheed  | 1999-01-01   | Development     |
      | 4           | Brian Wallace | 1979-01-01   | Sales           |

