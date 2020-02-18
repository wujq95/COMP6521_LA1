package Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        FileWriteTest.class,
        DataGeneratorTest.class,
        NotRepNumTest.class
})

public class JunitTestSuite {
}
