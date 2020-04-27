package com.mycompany.matrix;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

class MatrixTest {
    double[][]stringToMatrix(String str) {
        double[][] a;
        String [] rows = str.split("\n");
        a = new double[rows.length][rows.length];
        for (int i =0;i<rows.length;i++) {
            String[] elemsInRow = rows[i].split(",");
            for(int j=0;j<elemsInRow.length;j++) {
                a[i][j] = Double.parseDouble(elemsInRow[j]);
            }
        }
        return a;
    }

    @Test
    void multiply() {
        double[][] a = {{12, 5, 3}, {3, 7, 6}, {3, 2, 1}};
        double[][] b = {{3, 2, 1}, {12, 3, 5}, {8, 9, 9}};
        double[][] expected = {{120, 66, 64}, {141, 81, 92}, {41, 21, 22}};
        assertArrayEquals(expected, Matrix.multiply(a, b));
    }
    @Test
    void sum() {
        double[][] a = {{12, 5, 3}, {3, 7, 6}, {3, 2, 1}};
        double[][] b = {{3, 2, 1}, {12, 3, 5}, {8, 9, 9}};
        double[][] expected = {{15,7,4},{15,10,11},{11,11,10}};
        assertArrayEquals(expected,Matrix.sum(a,b));
    }

    @Test
    void subtract() {
        double[][] a = {{12, 5, 3}, {3, 7, 6}, {3, 2, 1}};
        double[][] b = {{3, 2, 1}, {12, 3, 5}, {8, 9, 9}};
        double[][] expected = {{9,3,2},{-9,4,1},{-5,-7,-8}};
        assertArrayEquals(expected,Matrix.subtract(a,b));
    }
    @Test
    void multiplyByVal() {
        double val = 2;
        double[][] a = {{12, 5, 3}, {3, 7, 6}, {3, 2, 1}};
        double[][] expected = {{24,10,6},{6,14,12},{6,4,2}};
        assertArrayEquals(expected,Matrix.multiplyByVal(a,2));
    }

    @Test
    void devideByVal() {
        double val = 2;
        double[][] a = {{12, 5, 3}, {3, 7, 6}, {3, 2, 1}};
        double[][] expected = {{6,2.5,1.5},{1.5,3.5,3},{1.5,1,0.5}};
        assertArrayEquals(expected,Matrix.devideByVal(a,2));
    }
    @ParameterizedTest
    @CsvSource({"12,5,3,4,60,25,15,20",
                "3,7,6,4,15,35,30,20",
                "3,2,1,8,15,10,5,40"})
    void multiplyByValueParam(String num1,String num2,String num3,String num4,
                              String num5,String num6,String num7,String num8) { //matrix 2x2
        double val = 5;
        double [][] expected = {{Double.parseDouble(num5),Double.parseDouble(num6)},
                {Double.parseDouble(num7),Double.parseDouble(num8)}};
        double [][] a = {{Double.parseDouble(num1),Double.parseDouble(num2)},
                {Double.parseDouble(num3),Double.parseDouble(num4)}};
        assertArrayEquals(expected,Matrix.multiplyByVal(a,val));
    }
    @Test
    void fileMultiply() {
        double[][]a= null;
        double[][]b = null;
        double[][]expected = null;
        try (InputStream inputStream = getClass().getResourceAsStream("/A.txt")) {
            String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            a = stringToMatrix(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (InputStream inputStream = getClass().getResourceAsStream("/B.txt")) {
            String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            b = stringToMatrix(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (InputStream inputStream = getClass().getResourceAsStream("/C_multiply.txt")) {
            String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            expected = stringToMatrix(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertArrayEquals(expected,Matrix.multiply(a,b));
    }

    @Test
    void fileSum() {
        double[][]a= null;
        double[][]b = null;
        double[][]expected = null;
        try (InputStream inputStream = getClass().getResourceAsStream("/A.txt")) {
            String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            a = stringToMatrix(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (InputStream inputStream = getClass().getResourceAsStream("/B.txt")) {
            String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            b = stringToMatrix(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (InputStream inputStream = getClass().getResourceAsStream("/C_sum.txt")) {
            String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            expected = stringToMatrix(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertArrayEquals(expected,Matrix.sum(a,b));
    }

    @Test
    void fileSubtract() {
        double[][]a= null;
        double[][]b = null;
        double[][]expected = null;
        try (InputStream inputStream = getClass().getResourceAsStream("/A.txt")) {
            String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            a = stringToMatrix(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (InputStream inputStream = getClass().getResourceAsStream("/B.txt")) {
            String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            b = stringToMatrix(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (InputStream inputStream = getClass().getResourceAsStream("/C_subtract.txt")) {
            String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            expected = stringToMatrix(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertArrayEquals(expected,Matrix.subtract(a,b));
    }

    @Test
    void fileMultiplyByVal() {
        double[][]a= null;
        double val = 5;
        double[][]expected = null;
        try (InputStream inputStream = getClass().getResourceAsStream("/A.txt")) {
            String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            a = stringToMatrix(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (InputStream inputStream = getClass().getResourceAsStream("/C_multByVal.txt")) {
            String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            expected = stringToMatrix(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertArrayEquals(expected,Matrix.multiplyByVal(a,val));
    }
}