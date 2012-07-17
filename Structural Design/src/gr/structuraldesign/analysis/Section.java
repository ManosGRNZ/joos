package gr.structuraldesign.analysis;

import java.io.Serializable;

/**
 * Definition for sections
 * The perimeter nodes are defined with y,z
 * 
 * possible holes are defined with yi and zi. These are 2D arrays
 * because there could be more than one hole
 * 
 * @author Manos G. Bairaktaris (bairaktaris@gmail.com)
 *
 */
public class Section implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	public Section(Material material, double[] y0, double[] z0) {
		Mat = material;
		y = y0;
		z = z0;
	}
	
	public Section(Material material, double[] y0, double[] z0, double[][] y1, double[][] z1) {
		Mat = material;
		y = y0;
		z = z0;
		yi = y1;
		zi = z1;
	}
	
	public Section (Material material, double A,double Ay,double Az,double Iy,double Iz,double Iyz,double It) {
		this.A = A;
		this.Ay = Ay;
		this.Az = Az;
		this.Iy = Iy;
		this.Iz = Iz;
		this.Iyz = Iyz;
		this.It = It;
		this.Mat = material;
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
