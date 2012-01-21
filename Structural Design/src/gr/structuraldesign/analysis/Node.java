package gr.structuraldesign.analysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Node {
	
	// Node index (more like an identifier)
	private int index;
	
	// Coordinates
	private double x,y,z;
	
	// Restrains
	private boolean restrainDX,restrainDY,restrainDZ,restrainMX,restrainMY,restrainMZ;
	
	public Node() {
		this.index = -1; // node does not have a index - should be set later along with other fields 
		this.x=this.y=this.z=0;
		this.restrainDX=this.restrainDY=this.restrainDZ=this.restrainMX=this.restrainMY=this.restrainMZ=false;		
	}
	
	public Node(int i, double x, double y, double z) {
		this.index = i;
		this.x = x;
		this.y = y;
		this.z = z;
		this.restrainDX=this.restrainDY=this.restrainDZ=this.restrainMX=this.restrainMY=this.restrainMZ=false;		
	}
	
	/**
	 * Should be updated for every created element.
	 * This should implemented within each Element object 
	 */
	public ArrayList<Element> connectedElements = new ArrayList<>();
	
	public int getIndex() {
		return index;
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
	
	public void setRestrainAllD(boolean rsD) {
		this.restrainDX=this.restrainDY=this.restrainDZ=rsD;
	}
	
	public void setRestrainAllM(boolean rsD) {
		this.restrainMX=this.restrainMY=this.restrainMZ=rsD;
	}
	
}
