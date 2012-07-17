package gr.structuraldesign.analysis;

public class BeamPointLoad extends Load {
	
	private int beam;
	private LoadType type;
	private double position;
	private double force;
	
	public BeamPointLoad(int beam, LoadType type, double position, double force) {
		this.beam = beam;
		this.type = type;
		this.position = position;
		this.force = force;
	}

	@Override
	public double[] setValueToLoadMatrix(double[] loadMatrix) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
