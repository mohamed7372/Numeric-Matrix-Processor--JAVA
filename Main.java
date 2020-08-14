package numericMatrixProcessor;

import java.util.Scanner;

public class Main {

	public static final Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		int n1 = sc.nextInt();
		int m1 = sc.nextInt();
		Matrix mat1 = new Matrix(n1, m1, fillMat(n1, m1));
		
		int n2 = sc.nextInt();
		int m2 = sc.nextInt();
		Matrix mat2 = new Matrix(n2, m2, fillMat(n2, m2));

		mat1.som(mat2);
	}
	
	static int[][] fillMat(int n, int m) {
		int[][] arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		return arr;
	}
}

class Matrix{
	int n;
	int m;
	int[][] arr;
	
	Matrix(int n, int m, int[][] arr) {
		this.n = n;
		this.m = m;
		this.arr = arr;
	}
	
	private boolean error(Matrix mat) {
		return this.n == mat.n && this.m == mat.m;
	}
	int[][] som(Matrix mat) {
		if (error(mat)) {
			int[][] s = new int[n][m];
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
}
