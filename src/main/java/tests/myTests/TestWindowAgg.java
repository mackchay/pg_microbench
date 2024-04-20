package tests.myTests;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tests.myTests.testUtils.RequiredData;
import tests.myTests.testUtils.TestUtils;

import static bench.V2.*;

public class TestWindowAgg {

    private static final Logger logger = LoggerFactory.getLogger(TestWindowAgg.class);
    private static final String expectedPlanType = "WindowAgg";

    @Test
    public void runFunctionTests() {
        String[] args = System.getProperty("args").split("\\s+");
        args(args);
        String query1 = "select x, sum(x) over() from generate_series(1, 10) as f(x)";
        String query2 = "select x, sum(x) over() from generate_series(1, 1000) as f(x)";
        String query3 = "select x, sum(x) over() from generate_series(1, 100000) as f(x)";
        String[] queries = new String[]{query1, query2, query3};
        TestUtils.testQueries(logger, queries, expectedPlanType);
    }

    @Test
    public void runSmallTablesTests() {
        String[] args = System.getProperty("args").split("\\s+");
        args(args);
        String query1 = "select x, sum(x) over() from small_table as f(x)";
        requireData(RequiredData.checkTables("small"), "myTests/SmallTables.sql");
        String[] queries = new String[]{query1};
        TestUtils.testQueries(logger, queries, expectedPlanType);
    }

    @Test
    public void runMediumTablesTests() {
        String[] args = System.getProperty("args").split("\\s+");
        args(args);
        String query1 = "select x, sum(x) over() from medium_table as f(x)";
        requireData(RequiredData.checkTables("medium"), "myTests/MediumTables.sql");
        String[] queries = new String[]{query1};
        TestUtils.testQueries(logger, queries, expectedPlanType);
    }

    @Test
    public void runLargeTablesTests() {
        String[] args = System.getProperty("args").split("\\s+");
        args(args);
        String query1 = "select x, sum(x) over() from large_table as f(x)";
        requireData(RequiredData.checkTables("large"), "myTests/LargeTables.sql");
        String[] queries = new String[]{query1};
        TestUtils.testQueries(logger, queries, expectedPlanType);
    }
}