package gr.structuraldesign.analysis;

import static java.lang.Math.*;

public class Beam extends Element {
	
	private double localAngle;
	
	private double EA, GAy, GAz, GIt, EIy, EIz; // stiffness
	private double l; // length;
			
	public Beam(int index, Section sect, double angle, Node[] nodes) {
		this.setIndex(index);
		setSect(sect);
		Node[] nodesToKeep = new Node[2];
		if (nodes.length>2) {
			System.out.printf("\n Beam %d is defined with more than 2 nodes.\n Extra nodes are ignored\n", index);			
		}
		nodesToKeep[0] = nodes[0];
		nodesToKeep[1] = nodes[1];
		setNodes(nodesToKeep);
		localAngle = angle;
		setProperties();
		setTransformationMatrix();
		setLocalStiffnessMatrix_LocalSystem();
		setLocalStiffnessMatrix_GlobalSystem();
	}
	
	public void setProperties() {
		Node[] nd = getNodes();
		l = sqrt(pow(nd[0].getX()-nd[1].getX(),2)+pow(nd[0].getY()-nd[1].getY(),2)+pow(nd[0].getZ()-nd[1].getZ(),2));
		EA = ( getSect().getMat().getE() ) * ( getSect().getA() );
		GAy = ( getSect().getMat().getG() ) * ( getSect().getAy() );
		GAz = ( getSect().getMat().getG() ) * ( getSect().getAz() );
		GIt = ( getSect().getMat().getE() ) * ( getSect().getIt() );
		EIy = ( getSect().getMat().getE() ) * ( getSect().getIy() );
		EIz = ( getSect().getMat().getE() ) * ( getSect().getIz() );
	}
	
	public double getLength() {
		return l;
	}
	
	private boolean releasePX, releasePY, releasePZ, releaseMX, releaseMY, releaseMZ; 
		
	public double getLocalAngle_DEG() {
		return localAngle*180./Math.PI;
	}
	
	public double getLocalAngle_RAD() {
		return localAngle;
	}

	public void setLocalAngle_DEG(double deg) {
		this.localAngle = deg*Math.PI/180.;
	}
	
	public void setLocalAngle_RAD(double rad) {
		this.localAngle = rad;
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
	private double[][] transformationMatrix;
	private double[][] transformationMatrix_T;
		
	private void setTransformationMatrix() {
		// to Do: code!		
	}
	
	public double[][] getLocalStiffnessMatrix_LocalSystem() {
		return localStiffnessMatrix_LocalSystem;
	}

	public void setLocalStiffnessMatrix_LocalSystem() {		
		for (int x=0; x<12; x++) for (int y=0; y<12; y++) localStiffnessMatrix_LocalSystem[x][y]=0;
		
		double fy = 12*EIz/(GAy*l*l);
		double fz = 12*EIy/(GAz*l*l);
		double k22 = 12*EIz / ( pow(l,3)*(1+fy) );
		double k33 = 12*EIy / ( pow(l,3)*(1+fz) );
		double k55 = (4+fz)*EIy / ( l*(1+fz) );
		double k66 = (4+fy)*EIz / ( l*(1+fy) );
		double k68 = 6*EIz / ( l*l*(1+fy) );
		double k59 = 6*EIy / ( l*l*(1+fz) );
		double k511 = (2+fz)*EIy / ( l*(1+fz) );
		double k612 = (2+fy)*EIz / ( l*(1+fy) );
		
		localStiffnessMatrix_LocalSystem[0][0] = EA/l;
		localStiffnessMatrix_LocalSystem[1][1] = k22*cos(localAngle) + k33*sin(localAngle);   
		localStiffnessMatrix_LocalSystem[2][2] = k33*cos(localAngle) + k22*sin(localAngle);  
		localStiffnessMatrix_LocalSystem[3][3] = GIt/l;
		localStiffnessMatrix_LocalSystem[4][4] = k55*cos(localAngle) + k66*sin(localAngle);
		localStiffnessMatrix_LocalSystem[5][5] = k66*cos(localAngle) + k55*sin(localAngle);
		localStiffnessMatrix_LocalSystem[4][2] = -k59*cos(localAngle) - k68*sin(localAngle);
		localStiffnessMatrix_LocalSystem[5][1] = -k68*cos(localAngle) - k59*sin(localAngle);
		
		for (int x=0; x<3; x++) for (int y=0; y<3; y++) {
			localStiffnessMatrix_LocalSystem[x+6][y] = -localStiffnessMatrix_LocalSystem[x][y];
			localStiffnessMatrix_LocalSystem[x][y+6] = -localStiffnessMatrix_LocalSystem[x][y];
			localStiffnessMatrix_LocalSystem[x+6][y+6] = localStiffnessMatrix_LocalSystem[x][y];
		}
		for (int x=3; x<6; x++) for (int y=0; y<3; y++) {
			localStiffnessMatrix_LocalSystem[x+6][y] = localStiffnessMatrix_LocalSystem[x][y];
			localStiffnessMatrix_LocalSystem[x][y+6] = -localStiffnessMatrix_LocalSystem[x][y];
			localStiffnessMatrix_LocalSystem[x+6][y+6] = -localStiffnessMatrix_LocalSystem[x][y];
		}
		for (int x=0; x<3; x++) for (int y=3; y<6; y++) {
			localStiffnessMatrix_LocalSystem[x+6][y] = -localStiffnessMatrix_LocalSystem[x][y];
			localStiffnessMatrix_LocalSystem[x][y+6] = localStiffnessMatrix_LocalSystem[x][y];
			localStiffnessMatrix_LocalSystem[x+6][y+6] = -localStiffnessMatrix_LocalSystem[x][y];
		}
		for (int x=3; x<6; x++) for (int y=3; y<6; y++) {
			localStiffnessMatrix_LocalSystem[x+6][y+6] = localStiffnessMatrix_LocalSystem[x][y];
		}
		localStiffnessMatrix_LocalSystem[9][3] = -GIt/l;
		localStiffnessMatrix_LocalSystem[10][4] = k511*cos(localAngle) + k612*sin(localAngle);
		localStiffnessMatrix_LocalSystem[11][5] = k612*cos(localAngle) + k511*sin(localAngle);
		for (int x=9; x<12; x++) for (int y=3; y<6; y++) {
			localStiffnessMatrix_LocalSystem[x-6][y+6] = localStiffnessMatrix_LocalSystem[x][y];
		}
				  
	}
	
	public void setLocalStiffnessMatrix_GlobalSystem() {
		double[][] tempMatrix = transformationMatrix.clone();
		transformationMatrix_T = MathExtension.matrixTranspose(tempMatrix);
		tempMatrix = MathExtension.multiplyMatrices(transformationMatrix_T, getLocalStiffnessMatrix_LocalSystem());
		localStiffnessMatrix_GlobalSystem = MathExtension.multiplyMatrices(tempMatrix, transformationMatrix);		
	}
	
}
