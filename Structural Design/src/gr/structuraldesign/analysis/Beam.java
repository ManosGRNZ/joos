package gr.structuraldesign.analysis;

import static java.lang.Math.*;

/**
 * The Beam is a subclass of Element. It is a standard beam 
 * that develops all internal forces (N,Vy,Vz,T,My,Mz)
 * and its local stiffness matrix is formed by taking into
 * account the effect of the shear.
 * 
 * @author Manos Bairaktaris  (bairaktaris@gmail.com)
 *
 */
public class Beam extends Element {
	
	private double localAngle;
	
	// The stiffness is separated at each node in order to implement the end releases and the nodal degrees of freedom
	// Additionally it will be possible to introduce separate plastic behavior at the ends
	private double EA0, GAy0, GAz0, GIt0, EIy0, EIz0; // stiffness at start node
	private double EA1, GAy1, GAz1, GIt1, EIy1, EIz1; // stiffness at end node
	private double l, dx, dy, dz; // length and rotation in global system
			
	public Beam(int index, Section sect, double angle, Node[] nodes) {
		this.setIndex(index);
		setSect(sect);
		Node[] nodesToKeep = new Node[2];
		if (nodes.length>2) {
			System.out.printf("\n Beam %d is defined with more than 2 nodes.\n The extra nodes are ignored\n", index);			
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
		
		double dx = nd[1].getX()-nd[0].getX();
		double dy = nd[1].getY()-nd[0].getY();
		double dz = nd[1].getZ()-nd[0].getZ();
		
		l = sqrt(dx*dx+dy*dy+dz*dz);
		
		EA0=EA1=GAy0=GAy1=GAz0=GAz1=GIt0=GIt1=EIy0=EIy1=EIz0=EIz1=0;
		
		if (!isReleaseStartPX()) EA0 = ( getSect().getMat().getE() ) * ( getSect().getA() );
		if (!isReleaseEndPX()) EA1 = ( getSect().getMat().getE() ) * ( getSect().getA() );
		
		if (!isReleaseStartPY()) GAy0 = ( getSect().getMat().getG() ) * ( getSect().getAy() ); 
		if (!isReleaseEndPY()) GAy1 = ( getSect().getMat().getG() ) * ( getSect().getAy() );
		
		if (!isReleaseStartPZ()) GAz0 = ( getSect().getMat().getG() ) * ( getSect().getAz() ); 
		if (!isReleaseEndPZ()) GAz1 = ( getSect().getMat().getG() ) * ( getSect().getAz() );
		
		if (!isReleaseStartMX()) GIt0 = ( getSect().getMat().getE() ) * ( getSect().getIt() ); 
		if (!isReleaseEndMX()) GIt1 = ( getSect().getMat().getE() ) * ( getSect().getIt() );
		
		if (!isReleaseStartMY()) EIy0 = ( getSect().getMat().getE() ) * ( getSect().getIy() );
		if (!isReleaseEndMY()) EIy1 = ( getSect().getMat().getE() ) * ( getSect().getIy() );
		
		if (!isReleaseStartMZ()) EIz0 = ( getSect().getMat().getE() ) * ( getSect().getIz() );
		if (!isReleaseEndMZ()) EIz1 = ( getSect().getMat().getE() ) * ( getSect().getIz() );
	}
	
	public double getLength() {
		return l;
	}
	
	private boolean releaseStartPX, releaseStartPY, releaseStartPZ, releaseStartMX, releaseStartMY, releaseStartMZ;
	private boolean releaseEndPX, releaseEndPY, releaseEndPZ, releaseEndMX, releaseEndMY, releaseEndMZ;
	
	public boolean isReleaseStartPX() {
		return releaseStartPX;
	}

	public void setReleaseStartPX(boolean releaseStartPX) {
		this.releaseStartPX = releaseStartPX;
	}

	public boolean isReleaseStartPY() {
		return releaseStartPY;
	}

	public void setReleaseStartPY(boolean releaseStartPY) {
		this.releaseStartPY = releaseStartPY;
	}

	public boolean isReleaseStartPZ() {
		return releaseStartPZ;
	}

	public void setReleaseStartPZ(boolean releaseStartPZ) {
		this.releaseStartPZ = releaseStartPZ;
	}

	public boolean isReleaseStartMX() {
		return releaseStartMX;
	}

	public void setReleaseStartMX(boolean releaseStartMX) {
		this.releaseStartMX = releaseStartMX;
	}

	public boolean isReleaseStartMY() {
		return releaseStartMY;
	}

	public void setReleaseStartMY(boolean releaseStartMY) {
		this.releaseStartMY = releaseStartMY;
	}

	public boolean isReleaseStartMZ() {
		return releaseStartMZ;
	}

	public void setReleaseStartMZ(boolean releaseStartMZ) {
		this.releaseStartMZ = releaseStartMZ;
	}

	public boolean isReleaseEndPX() {
		return releaseEndPX;
	}

	public void setReleaseEndPX(boolean releaseEndPX) {
		this.releaseEndPX = releaseEndPX;
	}

	public boolean isReleaseEndPY() {
		return releaseEndPY;
	}

	public void setReleaseEndPY(boolean releaseEndPY) {
		this.releaseEndPY = releaseEndPY;
	}

	public boolean isReleaseEndPZ() {
		return releaseEndPZ;
	}

	public void setReleaseEndPZ(boolean releaseEndPZ) {
		this.releaseEndPZ = releaseEndPZ;
	}

	public boolean isReleaseEndMX() {
		return releaseEndMX;
	}

	public void setReleaseEndMX(boolean releaseEndMX) {
		this.releaseEndMX = releaseEndMX;
	}

	public boolean isReleaseEndMY() {
		return releaseEndMY;
	}

	public void setReleaseEndMY(boolean releaseEndMY) {
		this.releaseEndMY = releaseEndMY;
	}

	public boolean isReleaseEndMZ() {
		return releaseEndMZ;
	}

	public void setReleaseEndMZ(boolean releaseEndMZ) {
		this.releaseEndMZ = releaseEndMZ;
	}	
		
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
	
	/**
	 * The transformation matrix is set by defining 
	 * the direction cosines.
	 * The naming convention is
	 * cosLG, where:
	 * L means local axis (x,y,z)
	 * G means global axis (X,Y,Z)
	 * Their definition takes into account the localAngle
	 * which if set 0, the local z axis faces towards the 
	 * global Z
	 */
	public void setTransformationMatrix() {
		// Direction cosines for the Axial Force and the Torsion
		double cosxX = dx/l;
		double cosxY = dy/l;
		double cosxZ = dz/l;	
		
		double cosyX = -cosxY * cos(localAngle);
		double cosyY = cosxX * cos(localAngle);
		double cosyZ = cosxZ * cos(localAngle);
		
		double coszX = cosxX * cos(localAngle);
		double coszY = cosxY * cos(localAngle);
		double coszZ = -sin(Math.acos(cosxZ)) * cos(localAngle);
		
		double[][] tempMatrix = 
			{  { cosxX, cosxY, cosxZ,    0 ,    0 ,    0 , cosxX, cosxY, cosxZ,    0 ,    0 ,    0 },
			   { cosyX, cosyY, cosyZ,    0 ,    0 ,    0 , cosyX, cosyY, cosyZ,    0 ,    0 ,    0 },
			   { coszX, coszY, coszZ,    0 ,    0 ,    0 , coszX, coszY, coszZ,    0 ,    0 ,    0 },
			   {    0 ,    0 ,    0 , cosxX, cosxY, cosxZ,    0 ,    0 ,    0 , cosxX, cosxY, cosxZ},
			   {    0 ,    0 ,    0 , cosyX, cosyY, cosyZ,    0 ,    0 ,    0 , cosyX, cosyY, cosyZ},
			   {    0 ,    0 ,    0 , coszX, coszY, coszZ,    0 ,    0 ,    0 , coszX, coszY, coszZ},
			   { cosxX, cosxY, cosxZ,    0 ,    0 ,    0 , cosxX, cosxY, cosxZ,    0 ,    0 ,    0 },
			   { cosyX, cosyY, cosyZ,    0 ,    0 ,    0 , cosyX, cosyY, cosyZ,    0 ,    0 ,    0 },
			   { coszX, coszY, coszZ,    0 ,    0 ,    0 , coszX, coszY, coszZ,    0 ,    0 ,    0 },
			   {    0 ,    0 ,    0 , cosxX, cosxY, cosxZ,    0 ,    0 ,    0 , cosxX, cosxY, cosxZ},
			   {    0 ,    0 ,    0 , cosyX, cosyY, cosyZ,    0 ,    0 ,    0 , cosyX, cosyY, cosyZ},
			   {    0 ,    0 ,    0 , coszX, coszY, coszZ,    0 ,    0 ,    0 , coszX, coszY, coszZ}  };
		
		transformationMatrix = tempMatrix;
		transformationMatrix_T = MathExtension.matrixTranspose(transformationMatrix);
		
	}
	
	public double[][] getLocalStiffnessMatrix_LocalSystem() {
		return localStiffnessMatrix_LocalSystem;
	}

	public void setLocalStiffnessMatrix_LocalSystem() {		
		for (int x=0; x<12; x++) for (int y=0; y<12; y++) localStiffnessMatrix_LocalSystem[x][y]=0;
		
		// The last "s" means "start" node
		double fys = 12*EIz0 / (GAy0*l*l);
		double fzs = 12*EIy0 / (GAz0*l*l);
		double k22s = 12*EIz0 / ( pow(l,3)*(1+fys) );
		double k33s = 12*EIy0 / ( pow(l,3)*(1+fzs) );
		double k55s = (4+fzs)*EIy0 / ( l*(1+fzs) );
		double k66s = (4+fys)*EIz0 / ( l*(1+fys) );
		double k68s = 6*EIz0 / ( l*l*(1+fys) );
		double k59s = 6*EIy0 / ( l*l*(1+fzs) );
		double k511s = (2+fzs)*EIy0 / ( l*(1+fzs) );
		double k612s = (2+fys)*EIz0 / ( l*(1+fys) );
		
		//The last "e" means "end" node
		double fye = 12*EIz1/(GAy1*l*l);
		double fze = 12*EIy1/(GAz1*l*l);
		double k22e = 12*EIz1 / ( pow(l,3)*(1+fye) );
		double k33e = 12*EIy1 / ( pow(l,3)*(1+fze) );
		double k55e = (4+fze)*EIy1 / ( l*(1+fze) );
		double k66e = (4+fye)*EIz1 / ( l*(1+fye) );
		double k68e = 6*EIz1 / ( l*l*(1+fye) );
		double k59e = 6*EIy1 / ( l*l*(1+fze) );
		double k511e = (2+fze)*EIy1 / ( l*(1+fze) );
		double k612e = (2+fye)*EIz1 / ( l*(1+fye) );
		
		localStiffnessMatrix_LocalSystem[0][0] = EA0/l;
		localStiffnessMatrix_LocalSystem[1][1] = k22s*cos(localAngle) + k33s*sin(localAngle);   
		localStiffnessMatrix_LocalSystem[2][2] = k33s*cos(localAngle) + k22s*sin(localAngle);  
		localStiffnessMatrix_LocalSystem[3][3] = GIt0/l;
		localStiffnessMatrix_LocalSystem[4][4] = k55s*cos(localAngle) + k66s*sin(localAngle);
		localStiffnessMatrix_LocalSystem[5][5] = k66s*cos(localAngle) + k55s*sin(localAngle);
		localStiffnessMatrix_LocalSystem[4][2] = -k59s*cos(localAngle) - k68s*sin(localAngle);
		localStiffnessMatrix_LocalSystem[5][1] = -k68s*cos(localAngle) - k59s*sin(localAngle);
		
		localStiffnessMatrix_LocalSystem[6][0] = -EA1/l;
		localStiffnessMatrix_LocalSystem[7][1] = -k22e*cos(localAngle) - k33e*sin(localAngle);   
		localStiffnessMatrix_LocalSystem[8][2] = -k33e*cos(localAngle) - k22e*sin(localAngle);  
		localStiffnessMatrix_LocalSystem[9][3] = -GIt1/l;
		localStiffnessMatrix_LocalSystem[10][4] = k511e*cos(localAngle) + k612e*sin(localAngle);
		localStiffnessMatrix_LocalSystem[11][5] = k612e*cos(localAngle) + k511e*sin(localAngle);
		localStiffnessMatrix_LocalSystem[10][2] = -k59e*cos(localAngle) - k68e*sin(localAngle);
		localStiffnessMatrix_LocalSystem[11][1] = -k68e*cos(localAngle) - k59e*sin(localAngle);
		
		localStiffnessMatrix_LocalSystem[0][6] = -EA0/l;
		localStiffnessMatrix_LocalSystem[1][7] = -k22s*cos(localAngle) - k33s*sin(localAngle);   
		localStiffnessMatrix_LocalSystem[2][8] = -k33s*cos(localAngle) - k22s*sin(localAngle);  
		localStiffnessMatrix_LocalSystem[3][9] = -GIt0/l;
		localStiffnessMatrix_LocalSystem[4][10] = k511e*cos(localAngle) + k612e*sin(localAngle);
		localStiffnessMatrix_LocalSystem[5][11] = k612e*cos(localAngle) + k511e*sin(localAngle);
		localStiffnessMatrix_LocalSystem[4][8] = k59s*cos(localAngle) + k68s*sin(localAngle);
		localStiffnessMatrix_LocalSystem[5][7] = k68s*cos(localAngle) + k59s*sin(localAngle);
		
		localStiffnessMatrix_LocalSystem[6][6] = EA1/l;
		localStiffnessMatrix_LocalSystem[7][7] = k22e*cos(localAngle) + k33e*sin(localAngle);   
		localStiffnessMatrix_LocalSystem[8][8] = k33e*cos(localAngle) + k22e*sin(localAngle);  
		localStiffnessMatrix_LocalSystem[9][9] = GIt1/l;
		localStiffnessMatrix_LocalSystem[10][10] = k55e*cos(localAngle) + k66e*sin(localAngle);
		localStiffnessMatrix_LocalSystem[11][11] = k66e*cos(localAngle) + k55e*sin(localAngle);
		localStiffnessMatrix_LocalSystem[10][8] = -k59e*cos(localAngle) - k68e*sin(localAngle);
		localStiffnessMatrix_LocalSystem[11][7] = -k68e*cos(localAngle) - k59e*sin(localAngle);
		
		printBeamLocalStiffnessMatrix();
				  
	}
	
	public void setLocalStiffnessMatrix_GlobalSystem() {
		double[][] tempMatrix = transformationMatrix.clone();
		transformationMatrix_T = MathExtension.matrixTranspose(tempMatrix);
		tempMatrix = MathExtension.multiplyMatrices(transformationMatrix_T, getLocalStiffnessMatrix_LocalSystem());
		localStiffnessMatrix_GlobalSystem = MathExtension.multiplyMatrices(tempMatrix, transformationMatrix);		
	}
	
	private void printBeamLocalStiffnessMatrix() {
		System.out.println("\nThis is the local stiffness matrix of beam: "+this.toString());
		for (int y=0; y<12; y++) {
			for (int x=0; x<12; x++) {
				System.out.printf("%12.2e", this.localStiffnessMatrix_LocalSystem[x][y]);
			}
			System.out.print("\n");
		}
	}
	
}
