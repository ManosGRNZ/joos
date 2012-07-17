package gr.structuraldesign.analysis;

import javax.swing.JOptionPane;

/**
 * This class provides assistance static methods
 * for use by other classes, in order to provide
 * better and easier coding of the essential parts
 * of the program.
 * 
 * @author Manos Bairaktaris (bairaktaris@gmail.com)
 *
 */
public class AssistanceMethods {
	
	public static int getIntegerGUI(String title, String message) {
		int i = Integer.MIN_VALUE;
		while (i==Integer.MIN_VALUE) {
			try {
				i = Integer.parseInt(JOptionPane.showInputDialog(null, message, title, JOptionPane.PLAIN_MESSAGE));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Please provide an Integer value");
				i = Integer.MIN_VALUE;
			}
		}
		return i;
	}
	
	public static double getDoubleGUI(String title, String message) {
		double n = Double.MIN_VALUE;
		while (n==Double.MIN_VALUE) {
			try {
				n = Double.parseDouble(JOptionPane.showInputDialog(null, message, title, JOptionPane.PLAIN_MESSAGE));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Please provide a Real number");
				n = Double.MIN_VALUE;
			}
		}
		return n;
	}
	
	public static void printMatrix2D(double[][] mat) {
		for (int y=0; y<mat[0].length; y++) {			
			for (int x=0; x<mat.length; x++) {
				System.out.printf("%12.3E", mat[x][y]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void printMatrix2D(String message, double[][] mat) {
		System.out.println(message);
		printMatrix2D(mat);
	}
	
	public static double[][] copy2Darray(double[][] source) {
		double[][] target = new double[source.length][source[0].length];
		for (int x=0; x<source.length; x++) {
			for (int y=0; y<source[0].length; y++) {
				target[x][y] = source[x][y];
			}
		} 
		return target;
	}
	
	public static double[][][] copy3Darray(double[][][] source) {
		double[][][] target = new double[source.length][source[0].length][source[0][0].length];
		for (int x=0; x<source.length; x++) {
			for (int y=0; y<source[0].length; y++) {
				for (int z=0; z<source[0][0].length; z++) {
					target[x][y][z] = source[x][y][z];
				}
			}
		}
		return target;
	}
}
