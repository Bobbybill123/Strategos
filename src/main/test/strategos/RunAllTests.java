package strategos;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import strategos.behaviour.BehaviourTests;
import strategos.mapcreation.MapGenerationTests;
import strategos.model.AllModelTests;
import strategos.ui.UITests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        IntegrationTests.class,
        UIIntegrationTests.class,
        MapGenerationTests.class,
        UITests.class,
        BehaviourTests.class,
        AllModelTests.class
})


public class RunAllTests {
    //This runs all the tests for each library independently by simply calling the test classes in each library
	//Networking tests must be run independently
}
