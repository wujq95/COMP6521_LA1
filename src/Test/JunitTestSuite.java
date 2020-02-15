package Test;

import Project_LA1.DataGenerator;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        FileTest.class,
        DataGeneratorTest.class
})

public class JunitTestSuite {
}
