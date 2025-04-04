package src;

public interface Scalar<T extends Scalar<T>> {
    T getValue();

    T getAddInverse();
    void invertAdditive();
    T getMulInverse();
    void invertMultiplicative();

    void add(T scalar);
    void subtract(T scalar);
    void multiply(T scalar);
    void divide(T scalar);

    default T adding(T scalar1, T scalar2) {
        T temp = scalar1.getValue();
        temp.add(scalar2);
        return temp;
    }

    default T subtract(T scalar1, T scalar2) {
        T temp = scalar1.getValue();
        temp.subtract(scalar2);
        return temp;
    }

    default T multiply(T scalar1, T scalar2) {
        T temp = scalar1.getValue();
        temp.multiply(scalar2);
        return temp;
    }

    default T divide(T scalar1, T scalar2) {
        T temp = scalar1.getValue();
        temp.divide(scalar2);
        return temp;
    }
}
