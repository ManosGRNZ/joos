package gr.structuraldesign.analysis;

public class Beam extends Element {
	
	private double localAngle;
		
	public Beam(Section sect, double angle, int[] nodes) {
		setSect(sect);
		setNodes(nodes);
		localAngle = angle;
		setTransformationMatrix();
		setLocalStiffnessMatrix_LocalSystem();
		setLocalStiffnessMatrix_GlobalSystem();
	}
	
	private boolean releasePX, releasePY, releasePZ, releaseMX, releaseMY, releaseMZ;   
	
	public double getLocalAngle() {
		return localAngle;
	}

	public void setLocalAngle(double localAngle) {
		this.localAngle = localAngle;
	}

	public boolean isReleaseMX() {
		return releaseMX;
	}

	public void setReleaseMX(boolean releaseMX) {
		this.releaseMX = releaseMX;
	}

	public boolean isReleaseMY() {
		return releaseMY;
	}

	public void setReleaseMY(boolean releaseMY) {
		this.releaseMY = releaseMY;
	}

	public boolean isReleaseMZ() {
		return releaseMZ;
	}

	public void setReleaseMZ(boolean releaseMZ) {
		this.releaseMZ = releaseMZ;
	}

	public double[][] getLocalStiffnessMatrix_GlobalSystem() {
		return localStiffnessMatrix_GlobalSystem;
	}

	public void setLocalStiffnessMatrix_GlobalSystem(
			double[][] localStiffnessMatrix_GlobalSystem) {
		this.localStiffnessMatrix_GlobalSystem = localStiffnessMatrix_GlobalSystem;
	}

	public void setLocalStiffnessMatrix_LocalSystem(
			double[][] localStiffnessMatrix_LocalSystem) {
		this.localStiffnessMatrix_LocalSystem = localStiffnessMatrix_LocalSystem;
	}

	private double[][] localStiffnessMatrix_LocalSystem = new double[12][12];
	private double[][] transformationMatrix = new double[12][12];
	private double[][] transformationMatrix_T;
	public double[][] localStiffnessMatrix_GlobalSystem = new double[12][12];
	
	private void setTransformationMatrix() {
		// to Do: code! and use localAngle
	}
	
	public double[][] getLocalStiffnessMatrix_LocalSystem() {
		return localStiffnessMatrix_LocalSystem;
	}

	public void setLocalStiffnessMatrix_LocalSystem() {
		// to Do: code! 
	}
	
	public void setLocalStiffnessMatrix_GlobalSystem() {
		double[][] tempMatrix = transformationMatrix.clone();
		transformationMatrix_T = MathExtension.matrixTranspose(tempMatrix);
		tempMatrix = MathExtension.multiplyMatrices(transformationMatrix_T, getLocalStiffnessMatrix_LocalSystem());
		localStiffnessMatrix_GlobalSystem = MathExtension.multiplyMatrices(tempMatrix, transformationMatrix);		
	}
	
}
