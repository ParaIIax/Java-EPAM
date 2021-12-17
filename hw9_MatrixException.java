package ua.univer.task9;

public class MatrixException extends Exception {
    Matrix a;
    Matrix b;

    public MatrixException(String exMess, Matrix a, Matrix b) {
        super(exMess);
        this.a = a;
        this.b = b;
    }

    public Matrix getMatrixA() {
        return a;
    }
    public Matrix getMatrixB() {
        return b;
    }

}
