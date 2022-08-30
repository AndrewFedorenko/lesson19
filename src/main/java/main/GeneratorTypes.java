package main;

import random.DoubleGenerator;
import random.Generator;
import random.IntGenerator;

public enum GeneratorTypes {
    INT{
        @Override
        public Generator<? extends Number> getGenerator() {
            return new IntGenerator();
        }
    },
    DOUBLE {
        @Override
        public Generator<? extends Number> getGenerator() {
            return new DoubleGenerator();
        }
    };

    public abstract Generator<? extends Number> getGenerator();

}
