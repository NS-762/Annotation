@ClassForTesting
public class MyClassWithTests {


    @BeforeSuite
    static void methodBefore() {
    }

    @AfterSuite
    static void methodAfter() {
    }

    @Test(priority = 2)
    static void methodWithPriority2() {
    }

    @Test(priority = 7)
    static void methodWithPriority7() {
    }

    @Test(priority = 4)
    static void methodWithPriority4() {
    }


    @Test(priority = 3)
    static void methodWithPriority3() {
    }

    @Test(priority = 1)
    static void methodWithPriority1() {
    }

}
