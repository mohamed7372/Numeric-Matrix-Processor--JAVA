package numericMatrixProcessor;

import java.util.Scanner;

public class Main {

	public static final Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		boolean loop = true;
		while(loop) {
			menu();
			int choise = sc.nextInt();
			switch (choise) {
			case 1: 
				addMat();
				System.out.println();
				break;
			case 2: 
				multMatByConst();
				System.out.println();
				break;
			case 3:
				multMat();
				System.out.println();
				break;
			case 4:
				subMenu();
				transposition();
				System.out.println();
				break;
			case 5:
				det();
				System.out.println();
				break;
			case 0:
				loop = false;
				break;
			default:
				System.out.println("error");
			}
		}
	}

	static void menu() {
		System.out.println("1. Add matrices");
		System.out.println("2. Multiply matrix to a constant");
		System.out.println("3. Multiply matrices");
		System.out.println("4. Transpose matrix");
		System.out.println("5. Calculate a determinant");
		System.out.println("0. Exit");
		System.out.print("Your choice: ");
	}
	static void subMenu() {
		System.out.println("1. Main diagonal");
		System.out.println("2. Side diagonal");
		System.out.println("3. Vertical line");
		System.out.println("4. Horizontal line");
		System.out.print("Your choice: ");
	}
	static void addMat() {
		System.out.print("Enter size of first matrix: ");
		int n1 = sc.nextInt();
		int m1 = sc.nextInt();
		System.out.println("Enter first matrix: ");
		Matrix mat1 = new Matrix(n1, m1, fillMat(n1, m1));
		
		System.out.print("Enter size of second matrix: ");
		int n2 = sc.nextInt();
		int m2 = sc.nextInt();
		System.out.println("Enter second matrix: ");
		Matrix mat2 = new Matrix(n2, m2, fillMat(n2, m2));
		
		System.out.println("The somme result is: ");
		mat1.som(mat2);
	}
	static void multMatByConst() {
		System.out.print("Enter size of first matrix: ");
		int n1 = sc.nextInt();
		int m1 = sc.nextInt();
		System.out.println("Enter first matrix: ");
		Matrix mat1 = new Matrix(n1, m1, fillMat(n1, m1));
		
		System.out.print("Enter a constant: ");
		int nbr = sc.nextInt();
		
		System.out.println("The multiplication by constant result is: ");
		mat1.multConst(nbr);
	}
	static void multMat() {
		System.out.print("Enter size of first matrix: ");
		int n1 = sc.nextInt();
		int m1 = sc.nextInt();
		System.out.println("Enter first matrix: ");
		Matrix mat1 = new Matrix(n1, m1, fillMat(n1, m1));
		
		System.out.print("Enter size of second matrix: ");
		int n2 = sc.nextInt();
		int m2 = sc.nextInt();
		System.out.println("Enter second matrix: ");
		Matrix mat2 = new Matrix(n2, m2, fillMat(n2, m2));
		
		System.out.println("The multiplication result is: ");
		mat1.mult(mat2);
	}
	static double[][] fillMat(int n, int m) {
		double[][] arr = new double[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				arr[i][j] = sc.nextDouble();
			}
		}
		return arr;
	}
	static void transposition() {
		int choice = sc.nextInt();
		System.out.print("Enter matrix size: ");
		int n1 = sc.nextInt();
		int m1 = sc.nextInt();
		System.out.println("Enter matrix: ");
		Matrix mat1 = new Matrix(n1, m1, fillMat(n1, m1));
		
		System.out.println("The result is: ");
		switch (choice) {
		case 1: 
			mat1.transposeMainDiag();
			break;
		case 2: 
			mat1.transposeSideDiag();
			break;
		case 3: 
			mat1.transposeVer();
			break;
		case 4: 
			mat1.transposeHor();
			break;
		default:
			System.out.println("ERROR");
		}
	}
	static void det() {
		System.out.print("Enter matrix size: ");
		int n1 = sc.nextInt();
		int m1 = sc.nextInt();
		System.out.println("Enter matrix: ");
		Matrix mat1 = new Matrix(n1, m1, fillMat(n1, m1));
		
		System.out.println("The result is: " + mat1.matrixDeterminant(mat1.arr));
	}
}

class Matrix{
	int n;
	int m;
	double[][] arr;
	
	Matrix(int n, int m, double[][] arr) {
		this.n = n;
		this.m = m;
		this.arr = arr;
	}
	
	private boolean error(Matrix mat) {
		return this.n == mat.n && this.m == mat.m;
	}
	double[][] som(Matrix mat) {
		if (error(mat)) {
			double[][] s = new double[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					s[i][j] = this.arr[i][j] + mat.arr[i][j];
					System.out.print(s[i][j] + " ");
				}
				System.out.println();
			}
			return s;
		}
		System.out.println("ERROR");
		return null;
	}
	double[][] multConst(int nbr) {
		double[][] s = new double[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				s[i][j] = this.arr[i][j] * nbr;
				System.out.print(s[i][j] + " ");
			}
			System.out.println();
		}
		return s;
	}
	double[][] mult(Matrix mat) {
		if (this.m == mat.n ) {
			double[][] s = new double[n][mat.m];
			for (int i = 0; i < this.n; i++) {
				for (int j = 0; j < mat.m; j++) {
					double res = 0;
					for (int k = 0; k < this.m; k++) {
						res += this.arr[i][k] * mat.arr[k][j];
					}
					s[i][j] = res;
					System.out.print(res + " ");
				}
				System.out.println();
			}
			return s;			
		}
		System.out.println("ERROR");
		return null;
	}
	void transposeMainDiag() {
		double[][] tra = new double[this.n][this.m];
		for (int i = 0; i < this.n; i++) {
			for (int j = 0; j < this.m; j++) {
				tra[j][i] = this.arr[i][j];
			}
		}
		printArr(tra);
	}
	void transposeSideDiag() {
		double[][] tra = new double[this.n][this.m];
		for (int i = 0; i < this.n; i++) {
			for (int j = 0; j < this.m; j++) {
				tra[i][j] = this.arr[this.m - 1 - j][this.n - 1 - i];
			}
		}
		printArr(tra);
	}
	void transposeVer() {
		double[][] tra = new double[this.n][this.m];
		for (int i = 0; i < this.n; i++) {
			for (int j = 0; j < this.m; j++) {
				tra[i][this.m - 1 -j] = this.arr[i][j];
			}
		}
		printArr(tra);
	}
	void transposeHor() {
		double[][] tra = new double[this.n][this.m];
		for (int i = 0; i < this.n; i++) {
			for (int j = 0; j < this.m; j++) {
				tra[this.n - 1 -i][j] = this.arr[i][j];
			}
		}
		printArr(tra);
	}
	double matrixDeterminant (double[][] matrix) {
		double temporary[][];
		double result = 0;

		if (matrix.length == 1) {
			result = matrix[0][0];
			return (result);
		}

		if (matrix.length == 2) {
			result = ((matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]));
			return (result);
		}

		for (int i = 0; i < matrix[0].length; i++) {
			temporary = new double[matrix.length - 1][matrix[0].length - 1];

			for (int j = 1; j < matrix.length; j++) {
				for (int k = 0; k < matrix[0].length; k++) {
					if (k < i) {
						temporary[j - 1][k] = matrix[j][k];
					} else if (k > i) {
						temporary[j - 1][k - 1] = matrix[j][k];
					}
				}
			}

			result += matrix[0][i] * Math.pow (-1, (double) i) * matrixDeterminant (temporary);
		}
		return (result);
	}

	private void printArr(double[][] mat) {
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				System.out.print(mat[i][j] + " ");
			}
			System.out.println();
		}
	}
}
