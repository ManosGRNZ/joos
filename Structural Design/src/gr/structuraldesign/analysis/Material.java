package gr.structuraldesign.analysis;

public class Material {
	
	private double E,Eh,G,v;
	private double yieldStrain;


	public Material(double e, double eh, double g, double v, double yieldStrain) {
		super();
		E = e;
		Eh = eh;
		G = g;
		this.v = v;
		this.yieldStrain = yieldStrain;
	}
	
	public Material(double e, double v) {
		super();
		E = e;
		this.v = v;
		G = E/(2*(1+v));
		Eh = 0;
		yieldStrain = 1;
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
	
}
