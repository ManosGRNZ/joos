package gr.structuraldesign.analysis;

/**
 * This is the linear static analysis solver
 * 
 * @author Manos Bairaktaris (bairaktaris@gmail.com)
 *
 */
public class LinearSolver {
	
	private double[] loads;
	private StiffnessMatrix stiffnessMatrix;
	private double[] displacements;
	private double[][] reverse;
	
	public LinearSolver(double[] nodeLoadMatrix, StiffnessMatrix stiffMatrix) {
		loads = nodeLoadMatrix;
		stiffnessMatrix = stiffMatrix;
		//AssistanceMethods.printMatrix2D(stiffnessMatrix.getMatrix());
		reverse = stiffnessMatrix.getReverse();
		solve();
	}
	
	private void solve() {
		displacements = new double[loads.length];
		for (int i=0; i<loads.length; i++) {
			displacements[i]=0;
			for (int n=0; n<loads.length; n++) {
				displacements[i] += reverse[n][i]*loads[n];
			}
		}
	}
	
	/**
	 * 
	 * @return an array of the displacements and rotation for each degree of freedom
	 */
	public double[] getDisplacements() {
		return displacements;
	}

}
