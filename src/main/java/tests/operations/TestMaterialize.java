package tests.operations;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tests.operations.utils.RequiredData;

import static bench.V2.*;
import static tests.operations.utils.TestUtils.testQueriesOnSubPlan;

public class TestMaterialize {
    private static final Logger logger = LoggerFactory.getLogger(TestMaterialize.class);
    private static final String expectedPlanType = "Materialize";

    @Test
    public void runSmallTablesTests() {
        String[] args = System.getProperty("args").split("\\s+");
        args(args);
        String query1 = "select * from small_table s1, small_table s2 where s1.x != s2.x";
        requireData(RequiredData.checkTables("small"), "tests/operations/SmallTables.sql");
        String[] queries = new String[]{query1};
        testQueriesOnSubPlan(logger, queries, expectedPlanType);
    }

    @Test
    public void runMediumTablesTests() {
        String[] args = System.getProperty("args").split("\\s+");
        args(args);
        String query1 = "select * from medium_table s1, medium_table s2 where s1.x != s2.x";
        requireData(RequiredData.checkTables("medium"), "tests/operations/MediumTables.sql");
        String[] queries = new String[]{query1};
        testQueriesOnSubPlan(logger, queries, expectedPlanType);
    }
}