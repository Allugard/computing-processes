package lab4;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by allugard on 31.05.17.
 */
public class Matrix {
    private int [][] matrix;

    public Matrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public Matrix(Random r, int size) {
        matrix = new int[size][size];
        for (int i = 0; i < matrix.length; i++) {
            boolean zero = true;
            for (int j = 0; j < matrix.length; j++) {
                int a = r.nextInt(2);
                if(a == 1){
                    zero = false;
                }
                matrix[i][j] = a;
                if(j == matrix.length-1 && zero){
                    matrix[i][j] = 1;
                }
            }
        }
    }

    public int findMinRow(){
        int curPos = -1;
        int curSum = Integer.MAX_VALUE;
        for (int j = 0; j <matrix.length; j++) {
            int sum = 0;
            for (int i = 0; i <matrix.length; i++) {
                sum += matrix[j][i];
            }
            if (sum < curSum){
                curSum = sum;
                curPos = j;
            }
        }
        return curPos;
    }


    public int findMinColumn(int row){
        int curPos = -1;
        int curSum = Integer.MAX_VALUE;
        for (int j = 0; j <matrix.length; j++) {
            if(matrix[row][j] == 1) {
                int sum = 0;
                for (int i = 0; i < matrix.length; i++) {
                    sum += matrix[i][j];
                }
                if (sum < curSum) {
                    curSum = sum;
                    curPos = j;
                }
            }
        }
        return curPos;
    }

    public Matrix deleteRowColumn(int row, int column){
        int [][] matr = new int[matrix.length-1][matrix.length-1];
        for (int i = 0, k = 0; i < matrix.length; i++) {
            if (i != row) {
                for (int j = 0,  l = 0; j < matrix.length; j++) {
                    if (j != column) {
                        matr[k][l] = matrix[i][j];
                        l++;
                    }
                }
                k++;
            }
        }

     return new Matrix(matr);
    }

    public int length(){
        return matrix.length;
    }

    @Override
    public String toString() {
        String s = "Matrix{\n";
        for (int i = 0; i <matrix.length; i++) {
            s += Arrays.toString(matrix[i]) + "\n";
        }
        s += "}";
        return s;
    }
}


