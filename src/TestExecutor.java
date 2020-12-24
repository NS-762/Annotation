import java.lang.reflect.Method;

public class TestExecutor {

    static void start(Class cl) throws Exception {

        int counterBeforeSuite = 0; //считать кол-во методов с аннотацией BeforeSuite
        int counterAfterSuite = 0; //считать кол-во методов с аннотацией AfterSuite

        int indexBeforeSuite = 0;
        int indexAfterSuite = 0;

        Method mth;


        if (!cl.isAnnotationPresent(ClassForTesting.class)) {
            throw new Exception("Данный класс не помечен аннотацией ClassForTesting");
        } else {
            Method[] methods = cl.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {

                if (methods[i].isAnnotationPresent(BeforeSuite.class)) {
                    counterBeforeSuite++;
                    indexBeforeSuite = i;
                }
                if (counterBeforeSuite == 2) {
                    throw new RuntimeException("Метод с аннотацией BeforeSuite может быть только один");
                }


                if (methods[i].isAnnotationPresent(AfterSuite.class)) {
                    counterAfterSuite++;
                }
                if (counterAfterSuite == 2) {
                    throw new RuntimeException("Метод с аннотацией AfterSuite может быть только один");
                }
            }


            mth = methods[0]; // поставить метод BeforeSuite в начало массива
            methods[0] = methods[indexBeforeSuite];
            methods[indexBeforeSuite] = mth;

            for (int i = 0; i < methods.length; i++) { //найти индекс метода с аннотацией AfterSuite
                if (methods[i].isAnnotationPresent(AfterSuite.class)) {
                    indexAfterSuite = i;
                }
            }
            mth = methods[methods.length - 1]; //поставить метод AfterSuite в конец массива
            methods[methods.length - 1] = methods[indexAfterSuite];
            methods[indexAfterSuite] = mth;


            int step = 2;
            boolean isSorted = false;

            while (!isSorted) { //сортировка пузырьком
                isSorted = true;

                for (int i = 1; i < methods.length - step; i++) {
                    if (methods[i].getAnnotation(Test.class).priority() >
                            methods[i + 1].getAnnotation(Test.class).priority()) {

                        mth = methods[i];
                        methods[i] = methods[i + 1];
                        methods[i + 1] = mth;

                        isSorted = false;
                    }
                }
                step++;
            }


            for (Method m:methods) {
                m.invoke(null);
                System.out.println(m.getName());
            }
        }
    }
}
