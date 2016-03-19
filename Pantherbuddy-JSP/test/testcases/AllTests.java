package testcases;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ Test_01_UnitTest_AccountBO.class,
		Test_02_UnitTest_MessageBO.class, Test_03_SubsystemTest_UserDAO.class,
		Test_04_SubsystemTest_MessageDAO.class })
public class AllTests {

}
