package jdbcStepDefs;

import driver.jdbDriver;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.assertj.core.api.SoftAssertions;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.*;

public class ConnectionSteps {

    private final jdbDriver driver = new jdbDriver();
    private Connection connection;
    private Statement stmt;
    private ResultSet rs;
    SoftAssertions softly = new SoftAssertions();

    @Before
    public void setUp() throws SQLException {
        //Load mysql jdbc driver
        driver.setUpJDBC();
        //Create Connection to DB
        this.connection = driver.getConnection();
        //Create Statement Object
        this.stmt = connection.createStatement();
    }

    @After
    public void tearDown() throws SQLException {
        driver.closeConnection();
    }

    @Given("Connect to local db")
    public void connectionToDb() {
        assertNotNull(connection);
    }

    @Then("^Get all data from scientist table$")
    public void getAllDataFromScientistTable(DataTable dataTable) throws SQLException {
        List<Map<String, String>> scientists = dataTable.asMaps();
        ArrayList<List<String>> expectedList = new ArrayList<>();
        ArrayList<List<String>> actualDbList = new ArrayList<>();

        for (Map<String, String> form : scientists) {
            String id = form.get("id");
            String firstName = form.get("firstName");
            String lastName = form.get("lastName");
            expectedList.add(Arrays.asList(id, firstName, lastName));
        }

        // Execute the SQL Query. Store results in ResultSet
        rs = stmt.executeQuery("select *  from scientist;");
        // While Loop to iterate through all data and print results
        while (rs.next()) {
            String dbId = rs.getString(1);
            String dbFirstName = rs.getString(2);
            String dbLastName = rs.getString(3);
            actualDbList.add(Arrays.asList(dbId, dbFirstName, dbLastName));
        }

        System.out.println("Expected list: " + expectedList);
        System.out.println("Actual DB list: " + actualDbList);
        softly.assertThat(actualDbList).isEqualTo(expectedList);
        softly.assertAll();
    }


    @Then("Get all data from Employees and Departments")
    public void getAllDataFromEmployeesAndDepartments(DataTable dataTable) throws SQLException {
        List<Map<String, String>> data = dataTable.asMaps();
        ArrayList<List<String>> expectedList = new ArrayList<>();
        ArrayList<List<String>> actualDbList = new ArrayList<>();

        for (Map<String, String> form : data) {
            String id = form.get("Employee_id");
            String firstName = form.get("Employee_name");
            String dob = form.get("Employee_DOB");
            String depName = form.get("Department_Name");
            expectedList.add(Arrays.asList(id, firstName, dob, depName));
        }

        // Execute the SQL Query. Store results in ResultSet
        rs = stmt.executeQuery("SELECT Employee_id,Employee_name, Employee_DOB, Department_Name\n" +
                "FROM Departments INNER JOIN Employees\n" +
                "                ON Departments.Department_id = Employees.Department_ID;");
        // While Loop to iterate through all data and print results
        while (rs.next()) {
            String dbId = rs.getString(1);
            String dbFirstName = rs.getString(2);
            String dbDob = rs.getString(3);
            String dbDepName = rs.getString(4);
            List<String> dbResult = Arrays.asList(dbId, dbFirstName, dbDob, dbDepName);
            actualDbList.add(dbResult);
        }

        System.out.println("Expected list: " + expectedList);
        System.out.println("Actual DB list: " + actualDbList);
        softly.assertThat(actualDbList).isEqualTo(expectedList);
        softly.assertAll();
    }
}
