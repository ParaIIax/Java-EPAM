package ua.univer.task9;

public class Matrix {
    private double[][] matrix;

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
    }
    public Matrix(int elementsRow, int elementsColumn) {
        if (elementsRow <= 0 || elementsColumn <= 0) {
            throw new IllegalArgumentException("Wrong row or column length");
        }
        matrix = new double[elementsRow][elementsColumn];
    }

    public int getElementsRow() {
        return matrix.length;
    }
    public int getElementsColumn() {
        return matrix[0].length;
    }
    public double[][] getMatrix() {
        return matrix;
    }

    public void emptyMatrix(int elementsRow, int elementsColumn, double _matrix) {
        if (elementsRow < 0 || elementsColumn < 0 ||
                elementsRow >= getElementsRow() || elementsColumn >= getElementsColumn()) {
            throw new IllegalArgumentException("Wrong row or column length");
        }
        matrix[elementsRow][elementsColumn] = _matrix;
    }

    public Matrix addition(Matrix _matrix) throws MatrixException {
        if (getElementsRow() != _matrix.getElementsRow() || getElementsColumn() != _matrix.getElementsColumn()) {
            throw new
                    MatrixException("Attempted operations with matrices of different sizes", this, _matrix);
        }
        double[][] res = new double[getElementsRow()][getElementsColumn()];

        for (int i = 0; i < getElementsRow(); i++) {
            for (int j = 0; j < getElementsColumn(); j++) {
                res[i][j] = getMatrix()[i][j] + _matrix.getMatrix()[i][j];
            }
        }
        return new Matrix(res);
    }

    public Matrix deduction(Matrix _matrix) throws MatrixException {
        if (getElementsRow() != _matrix.getElementsRow() || getElementsColumn() != _matrix.getElementsColumn()) {
            throw new
                    MatrixException("Attempted operations with matrices of different sizes", this, _matrix);
        }
        double[][] res = new double[getElementsRow()][getElementsColumn()];

        for (int i = 0; i < getElementsRow(); i++) {
            for (int j = 0; j < getElementsColumn(); j++) {
                res[i][j] = getMatrix()[i][j] - _matrix.getMatrix()[i][j];
            }
        }
        return new Matrix(res);
    }

    public Matrix multiplication(Matrix _matrix) throws MatrixException {
        if (getElementsColumn() != _matrix.getElementsRow()) {
            throw new
                    MatrixException("Attempted operations with matrices of different sizes", this, _matrix);
        }
        double[][] res = new double[getElementsRow()][_matrix.getElementsColumn()];

        for (int i = 0; i < getElementsRow(); i++) {
            for (int j = 0; j < _matrix.getElementsColumn(); j++) {
                for (int k = 0; k < getElementsColumn(); k++) {
                    res[i][j] += getMatrix()[i][k] * _matrix.getMatrix()[k][j];
                }
            }
        }
        return new Matrix(res);
    }

}
