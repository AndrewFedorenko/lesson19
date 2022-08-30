import annotations.GeneratorProperty;
import annotations.MatrixProperties;
import annotations.MatrixProperty;
import main.GeneratorTypes;
import random.Generator;
import random.Matrix;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestMatrix {

    @MatrixProperty(generator = @GeneratorProperty(GeneratorTypes.INT), rows = 3, column = 3)
    @MatrixProperty(generator = @GeneratorProperty(GeneratorTypes.DOUBLE), rows = 5, column = 5)
    public <T extends Number> void testMatrixWithTwoAnnotations(Matrix<T> matrix) {
        matrix.printMatrix();
    }

    public static void main(String[] args) {
        TestMatrix testMatrix = new TestMatrix();
        testMatrixThread(testMatrix);
    }
    private static void testMatrixThread(Object object) {
        Method[] declaredMethods = object.getClass().getDeclaredMethods();
        for (Method method : declaredMethods) {
            MatrixProperties matrixProperties = method.getAnnotation(MatrixProperties.class);
            if (matrixProperties == null) {
                continue;
            }
            long btime = System.nanoTime();
            ExecutorService executor = Executors.newFixedThreadPool(2);
            for (MatrixProperty matrixProperty : matrixProperties.value()) {
                //executor.execute(new GeneratorThread(matrixProperty, method, object));
                executor.execute(() -> {
                    try {
                        Generator<?> generator = matrixProperty.generator().value().getGenerator();
                        method.invoke(object, new Matrix<>(generator, matrixProperty.rows(), matrixProperty.column()));
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                });
            }
            executor.shutdown();
            System.out.println("Time is " + (System.nanoTime() - btime));
        }
    }

}
