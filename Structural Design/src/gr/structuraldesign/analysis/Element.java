package gr.structuraldesign.analysis;

public abstract class Element {
	
	private Material mat;
	
	private Section sect;
	
	public Material getMat() {
		return mat;
	}

	public void setMat(Material mat) {
		this.mat = mat;
	}
	
	public Section getSect() {
		return sect;
	}

	public void setSect(Section sect) {
		this.sect = sect;
	}
	
	public double[][] localStiffnessMatrix;
	
	public double[][] transformationMatrix;
	
}
