package operations;

import bench.V2;
import operations.testplan.TestPlan;
import operations.utils.RequiredData;
import operations.utils.TestUtils;

import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static bench.V2.*;

public class TestWindowAgg extends TestPlan {

    private static final Logger logger = LoggerFactory.getLogger(TestWindowAgg.class);
    private static final String expectedPlanType = "WindowAgg";

    @Test(alwaysRun = true, priority = 1)
    public void runFunctionTests() {
        
        String query1 = "select x, sum(x) over() from generate_series(1, 10) as f(x)";
        String query2 = "select x, sum(x) over() from generate_series(1, 1000) as f(x)";
        String query3 = "select x, sum(x) over() from generate_series(1, 100000) as f(x)";
        String[] queries = new String[]{query1, query2, query3};
        TestUtils.testQueriesOnMainPlan(logger, queries, expectedPlanType);
    }

    @Test(alwaysRun = true, priority = 2)
    public void runSmallTablesTests() {
        
        String query1 = "select x, sum(x) over() from small_table as f(x)";
        V2.requireData(RequiredData.checkTables("small"), "tests/operations/SmallTables.sql");
        String[] queries = new String[]{query1};
        TestUtils.testQueriesOnMainPlan(logger, queries, expectedPlanType);
    }

    @Test(alwaysRun = true, priority = 3)
    public void runMediumTablesTests() {
        
        String query1 = "select x, sum(x) over() from medium_table as f(x)";
        requireData(RequiredData.checkTables("medium"), "tests/operations/MediumTables.sql");
        String[] queries = new String[]{query1};
        TestUtils.testQueriesOnMainPlan(logger, queries, expectedPlanType);
    }

    @Test(alwaysRun = true, priority = 4)
    public void runLargeTablesTests() {
        
        String query1 = "select x, sum(x) over() from large_table as f(x)";
        requireData(RequiredData.checkTables("large"), "tests/operations/LargeTables.sql");
        String[] queries = new String[]{query1};
        TestUtils.testQueriesOnMainPlan(logger, queries, expectedPlanType);
    }

    
}
