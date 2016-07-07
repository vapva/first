package testFirst;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import testFirst.*;

@RunWith(Suite.class)
@SuiteClasses({TestStart.class, 
	TestInputHelper.class,
	TestWaterParam.class})
public class AllTests {
}
