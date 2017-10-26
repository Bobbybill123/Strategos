package strategos.ui;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import strategos.networking.Tests;


@RunWith(Suite.class)
@Suite.SuiteClasses({
		Tests.class,
		FreeRunTest.class
//		ExitListenerTest.class,
//		GridComponentTest.class
})

public class UITests {
}
