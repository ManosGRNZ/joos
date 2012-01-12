package gr.structuraldesign.analysis;

/**
 * Definition for sections
 * The perimeter nodes are defined with y,z
 * 
 * possible holes are defined with yi and zi. These are 2D arrays
 * because there could be more than one hole
 * 
 * @author Manos G. Bairaktaris
 *
 */
public class Section {
	
	private double[] z;
	private Material Mat;
	
	/* Definitions for holes 
	 * xi[h][i]: 
	 * h: number of hole
	 * i: number of section node
	 */
	private double[][] yi;
	private double[][] zi;
	
	public double A,Ay,Az,Iy,Iz,Iyz,It;	

}
