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
	
	private Material Mat;

	private double[] y;
	private double[] z;
		
	/* Definitions for holes (2D arrays)
	 * xi[h][i]: 
	 * h: number of hole
	 * i: number of section node
	 */
	private double[][] yi;
	private double[][] zi;
	
	private double A,Ay,Az,Iy,Iz,Iyz,It;
	
	/**
	 * Section without holes
	 */
	public Section(Material m, double[] y0, double[] z0) {
		Mat = m;
		y = y0;
		z = z0;
	}
	
	public Section(Material m, double[] y0, double[] z0, double[][] y1, double[][] z1) {
		Mat = m;
		y = y0;
		z = z0;
		yi = y1;
		zi = z1;
	}

	public Material getMat() {
		return Mat;
	}

	public void setMat(Material mat) {
		Mat = mat;
	}

	public double[] getY() {
		return y;
	}

	public void setY(double[] y) {
		this.y = y;
	}

	public double[] getZ() {
		return z;
	}

	public void setZ(double[] z) {
		this.z = z;
	}

	public double[][] getYi() {
		return yi;
	}

	public void setYi(double[][] yi) {
		this.yi = yi;
	}

	public double[][] getZi() {
		return zi;
	}

	public void setZi(double[][] zi) {
		this.zi = zi;
	}

	public double getA() {
		return A;
	}

	public void setA(double a) {
		A = a;
	}

	public double getAy() {
		return Ay;
	}

	public void setAy(double ay) {
		Ay = ay;
	}

	public double getAz() {
		return Az;
	}

	public void setAz(double az) {
		Az = az;
	}

	public double getIy() {
		return Iy;
	}

	public void setIy(double iy) {
		Iy = iy;
	}

	public double getIz() {
		return Iz;
	}

	public void setIz(double iz) {
		Iz = iz;
	}

	public double getIyz() {
		return Iyz;
	}

	public void setIyz(double iyz) {
		Iyz = iyz;
	}

	public double getIt() {
		return It;
	}

	public void setIt(double it) {
		It = it;
	}
	
}
