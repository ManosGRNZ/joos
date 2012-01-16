package gr.structuraldesign.analysis;

public class Beam extends Element {
	
	private double localAngle;
		
	public Beam(Section sect, double angle, int[] nodes) {
		setSect(sect);
		setNodes(nodes);
		localAngle = angle;		
	}
	
}
