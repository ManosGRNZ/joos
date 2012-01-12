package gr.structuraldesign.analysis;

/**
 * This class provides tools for mathematics through static methods.    
 * 
 * @author Manos Bairaktaris
 *
 */
public class MathExtension {
	
	/**
	 * This method returns the area of a polygon based on the
	 * coordinates of its vertices. NOT TESTED YET
	 * @param x array of x coordinates
	 * @param y array of y coordinates
	 * @return Area of the polygon (double) (returns -1 if there is a problem with the number of coordinates) 
	 */
	public static double polygonArea( double[] x , double[] y ) {
		if ((x.length!=y.length) || (x.length<3)) {
			return -1;  // problem with the number of coordinates
		}
		
		double area = 0;
		
		for (int i=0; i<(x.length-1); i++) {
			area += x[i]*y[i+1] - y[i]*x[i+1];			
		}
		area += x[x.length]*y[0] - y[y.length]*x[0];
		area *= 0.5;
		
		return area;
	}
	
	public static double matrixDeterminant(double[][] matrix) {
		int d = matrix.length;		
		int sign;
		double[][] reduced = new double[d-1][d-1];
		double det=0;
		
		if (d<3) {
			det = matrix[0][0]*matrix[1][1]-matrix[0][1]*matrix[1][0];
		} else {
			for (int i=0; i<d; i++) {
				reduced = getReduced(matrix,i);
				if ((i%2)==0) sign=1; else sign=-1;				
				det += sign*matrix[i][0]*matrixDeterminant(reduced);  // recursive use of matrixDeterminant
			}			
		}
		
		return det;
	}
	
	private static double[][] getReduced(double[][] a, int i) {
		int d = a.length;
		double[][] b = new double[d-1][d-1];
		
		for (int x=0; x<d; x++) for (int y=1; y<d; y++) {
			if (x<i) b[x][y-1]=a[x][y];
			if (x>i) b[x-1][y-1]=a[x][y];
			// x==i --> continue
		}		
		return b;
	}
	
	public static double[][] matrixTranspose(double[][] matrix) {
		int dim = matrix.length;
		double[][] transpose = new double[dim][dim];
		
		for (int x=0; x<dim; x++)
			for (int y=0; y<dim; y++) {
				transpose[x][y] = matrix[y][x];
			}
		return transpose;
	}
	
	public static double[][] multiplyMatrices(double[][] A, double[][] B) {
		int x,y;
		double num=0;
		double[][] multResult = new double[B.length][A[0].length];
		
		for (x=0; x<B.length; x++) {
			for (y=0; y<A[0].length; y++) {
				num=0;
				for (int n=0; n<A.length; n++)   num += A[n][y]*B[x][n];
				multResult[x][y]=num;
			}
		}
		return multResult;
	}
	
	/**
	 * Calculation of the inverse matrix 
	 * using the Gauss-Jordan method 
	 * @param matrix
	 * @return double[][]
	 */
	public static double[][] matrixInverse(double[][] matrix) {
		int x,y,line;
		int dim = matrix.length;
		double[][] reverse = new double[dim][dim];
		
		for (x=0; x<dim; x++) {
			for (y=0; y<dim; y++) {
				if (x==y) reverse[x][y]=1; else reverse[x][y]=0;
			} // close for y
		} // close for x
		
		double divisor;
		double multiplier;
		
		for (line=0; line<dim; line++) {
			divisor=matrix[line][line];
			for (x=0; x<dim; x++) {
				matrix[x][line]=(matrix[x][line])/divisor;
				reverse[x][line]=(reverse[x][line])/divisor;
			}
			for (y=0; y<dim; y++) {
				if (y!=line) {
					multiplier=matrix[line][y];
					for (x=0; x<dim; x++) {
						matrix[x][y]=matrix[x][y]-multiplier*matrix[x][line];
						reverse[x][y]=reverse[x][y]-multiplier*reverse[x][line];
					} // close for x 
				} // close if 
			} //close for y
		} // close for line
		
		return reverse;
	} 

}
