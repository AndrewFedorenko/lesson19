package annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(MatrixProperties.class)
public @interface MatrixProperty {
    GeneratorProperty generator();

    int rows();

    int column();

}
