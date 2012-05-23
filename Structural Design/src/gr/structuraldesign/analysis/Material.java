package gr.structuraldesign.analysis;

import java.io.Serializable;

/**
 * This is a class containing general data for materials
 * It supports only very basic nonlinear data.  
 * @author Manos Bairaktaris (bairaktaris@gmail.com)
 *
 */

public class Material implements Serializable {
	
	private double E,Eh,G,v;
	private double yieldStrain;
	private double ultimateStrain;
	/**
	 * Constructor for material objects with full parameters
	 * @param E  Young modulus
	 * @param Eh Modulus after yield strain
	 * @param G Shear modulus
	 * @param v Poisson ratio
	 * @param yieldStrain
	 * @param ultimateStrain
	 */
	public Material(double E, double Eh, double G, double v, double yieldStrain, double ultimateStrain) {
		this.E = E;
		this.Eh = Eh;
		this.G = G;
		this.v = v;
		this.yieldStrain = yieldStrain;
		this.setUltimateStrain(ultimateStrain);
	}
	/**
	 * Constructor with few parameters
	 * @param E Young modulus
	 * @param v Poisson ratio
	 */
	public Material(double E, double v) {
		this.E = E;
		this.v = v;
		G = E/(2.*(1.+v));
		Eh = 0.1*E;
		yieldStrain = Double.MAX_VALUE;
		ultimateStrain = Double.MAX_VALUE;
	}
	/**
	 * Constructor with only E (young modulus)
	 * considering a default poisson ratio
	 * v=0.2
	 * @param E Young modulus
	 */
	public Material(double E) {
		this.E = E;
		this.v = 0.2;
		G = E/(2.*(1.+v));		
		Eh = 0.1*E;
		yieldStrain = Double.MAX_VALUE;
		ultimateStrain = Double.MAX_VALUE;
	}

	public double stress(double strain) {
		double result;
		
		if (Math.abs(strain)<Math.abs(yieldStrain)) {
			result = E*strain;
		} else {
			result = sign(strain)*E*Math.abs(yieldStrain)+Eh*(strain-sign(strain)*yieldStrain);
		}
		
		return result; 
	}
	
	public double getE() {
		return E;
	}

	public void setE(double e) {
		E = e;
	}

	public double getEh() {
		return Eh;
	}

	public void setEh(double eh) {
		Eh = eh;
	}

	public double getG() {
		return G;
	}

	public void setG(double g) {
		G = g;
	}

	public double getV() {
		return v;
	}

	public void setV(double v) {
		this.v = v;
	}

	public double getYieldStrain() {
		return yieldStrain;
	}

	public void setYieldStrain(double yieldStrain) {
		this.yieldStrain = yieldStrain;
	}

	private double sign(double x) {
		if (x==0) 
			return 0; 
		else
			return x/Math.abs(x);
	}

	public double getUltimateStrain() {
		return ultimateStrain;
	}

	public void setUltimateStrain(double ultimateStrain) {
		this.ultimateStrain = ultimateStrain;
	}
	
}
