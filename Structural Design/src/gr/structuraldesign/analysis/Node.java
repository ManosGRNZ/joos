package gr.structuraldesign.analysis;

public class Node {
	
	// Coordinates
	private double x,y,z;
	
	// Restrains
	private boolean restrainDX,restrainDY,restrainDZ,restrainMX,restrainMY,restrainMZ;
	
	public Node() {
		this.x=this.y=this.z=0;
		this.restrainDX=this.restrainDY=this.restrainDZ=this.restrainMX=this.restrainMY=this.restrainMZ=false;		
	}
	
	public Node(double x, double y, double z) {		
		this.x = x;
		this.y = y;
		this.z = z;
		this.restrainDX=this.restrainDY=this.restrainDZ=this.restrainMX=this.restrainMY=this.restrainMZ=false;		
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public boolean isRestrainDX() {
		return restrainDX;
	}

	public void setRestrainDX(boolean restrainDX) {
		this.restrainDX = restrainDX;
	}

	public boolean isRestrainDY() {
		return restrainDY;
	}

	public void setRestrainDY(boolean restrainDY) {
		this.restrainDY = restrainDY;
	}

	public boolean isRestrainDZ() {
		return restrainDZ;
	}

	public void setRestrainDZ(boolean restrainDZ) {
		this.restrainDZ = restrainDZ;
	}

	public boolean isRestrainMX() {
		return restrainMX;
	}

	public void setRestrainMX(boolean restrainMX) {
		this.restrainMX = restrainMX;
	}

	public boolean isRestrainMY() {
		return restrainMY;
	}

	public void setRestrainMY(boolean restrainMY) {
		this.restrainMY = restrainMY;
	}

	public boolean isRestrainMZ() {
		return restrainMZ;
	}

	public void setRestrainMZ(boolean restrainMZ) {
		this.restrainMZ = restrainMZ;
	}

	
}
