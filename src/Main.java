import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        try {
            TestExecutor.start(MyClassWithTests.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
