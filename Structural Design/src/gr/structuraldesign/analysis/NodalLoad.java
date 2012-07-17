package gr.structuraldesign.analysis;

public class NodalLoad extends Load {
	
	private int node;
	private LoadType type;
	private double loadValue;
	
	public NodalLoad(int nd, LoadType lt, double f) {
		this.node = nd;
		this.type = lt;
		this.loadValue = f;
	}
	
	public LoadType getType() {
		return type;
	}

	public void setType(LoadType type) {
		this.type = type;
	}

	public double getLoadValue() {
		return loadValue;
	}

	public void setLoadValue(double loadValue) {
		this.loadValue = loadValue;
	}

	@Override
	public double[] setValueToLoadMatrix(double[] loadMatrix) {
		int dof=0;
		switch (type) {
			case NODE_PX:
				dof = 0;
				break;
			case NODE_PY:
				dof = 1;
				break;
			case NODE_PZ:
				dof = 2;
				break;
			case NODE_MX:
				dof = 3;
				break;
			case NODE_MY:
				dof = 4;
				break;
			case NODE_MZ:
				dof = 5;
				break;
		}
		dof += (node-1)*6;
		loadMatrix[dof] += loadValue;
		return loadMatrix;
	}

	
}
