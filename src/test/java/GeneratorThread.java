import annotations.MatrixProperty;
import random.Generator;
import random.Matrix;

import java.lang.reflect.Method;

public class GeneratorThread implements Runnable {
    MatrixProperty matrixProperty;
    Method method;
    Object object;

    public GeneratorThread(MatrixProperty matrixProperty, Method method, Object object) {
        this.matrixProperty = matrixProperty;
        this.method = method;
        this.object = object;
    }


    @Override
    public void run() {
        try {
            Generator<?> generator = matrixProperty.generator().value().getGenerator();
            method.invoke(object, new Matrix<>(generator, matrixProperty.rows(), matrixProperty.column()));
        } catch (Exception e) {
            System.err.println(e.getMessage());

        }
    }
}
