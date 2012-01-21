package gr.structuraldesign.analysis;

import java.security.KeyStore.Builder;
import java.util.Vector;

/**
 * This abstract class form the basis for
 * all the elements that can be implemented in 
 * the global stiffness matrix.
 * 
 * @author Manos Bairaktaris
 *
 */
public abstract class Element {
	
	private int index;
	private Section sect;
	
	private Node[] nodes;
	
	public Section getSect() {
		return sect;
	}

	public void setSect(Section sect) {
		this.sect = sect;
	}
	
	public Node[] getNodes() {
		return nodes;
	}

	public void setNodes(Node[] nodes) {
		this.nodes = nodes;
		for (Node i : this.nodes) {
			if (!i.connectedElements.contains(this)) {
				i.connectedElements.add(this);
			}			
		}
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public double[][] localStiffnessMatrix_GlobalSystem;
	
	public double[][] transformationMatrix;
	
	/**
	 * This is a class that all element should implement in
	 * order to be able to add it to the Global System Matrix.
	 * The local system and the transformation matrices are
	 * not included, since they are left in the programming
	 * freedom of the subclasses.
	 */
	abstract public void setLocalStiffnessMatrix_GlobalSystem();
	
}
