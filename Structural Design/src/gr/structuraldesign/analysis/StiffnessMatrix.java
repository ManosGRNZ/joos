package gr.structuraldesign.analysis;

/**
 * The creation of the stiffness matrix and the
 * reverse take place within the constructor.
 * i.e. the created object is fully functional immediately. 
 * 
 * @author Manos Bairaktaris (bairaktaris@gmail.com)
 *
 */
public class StiffnessMatrix {
	
	private Node[] nodes;
	private Element[] elements;
	
	private double[][] matrix;
	private double[][] reverse;
	
	public double[][] getMatrix() {
		return matrix;
	}
	
	public double[][] getReverse() {
		return reverse;
	}

	public StiffnessMatrix(Node[] nodesOfStructure, Element[] elementsOfStructure) {
		nodes = nodesOfStructure;
		elements = elementsOfStructure;
		matrix = new double[nodes.length*6][nodes.length*6];
		formStiffnessMatrix();
		reverse = MathExtension.matrixInverse(matrix);
	}
	
	private void formStiffnessMatrix() {
		for (Element elem : elements) {
			Node node0 = elem.getNodes()[0];
			Node node1 = elem.getNodes()[1];
			for (int x=0; x<6; x++) {
				for (int y=0; y<6; y++) {					
					matrix[node0.getIndex()+x][node0.getIndex()+y] += elem.localStiffnessMatrix_GlobalSystem[x][y];					
					matrix[node1.getIndex()+x][node1.getIndex()+y] += elem.localStiffnessMatrix_GlobalSystem[x+6][y+6];
				}				
			}
			
		}
		//AssistanceMethods.printMatrix2D("\n Matrix before removing supports:", matrix);
		System.out.println();
		int n;
		for (Node node : nodes) {
			if (node.isRestrainDX()) {
				n=node.getIndex();
				for (int k=0; k<matrix.length; k++) { 
					matrix[k][n]=0;
					matrix[n][k]=0;
				}					
				matrix[n][n]=1;
			}
			if (node.isRestrainDY()) {
				n=node.getIndex()+1;
				for (int k=0; k<matrix.length; k++) { 
					matrix[k][n]=0;
					matrix[n][k]=0;
				}					
				matrix[n][n]=1;
			}
			if (node.isRestrainDZ()) {
				n=node.getIndex()+2;
				for (int k=0; k<matrix.length; k++) { 
					matrix[k][n]=0;
					matrix[n][k]=0;
				}					
				matrix[n][n]=1;
			}
			if (node.isRestrainMX()) {
				n=node.getIndex()+3;
				for (int k=0; k<matrix.length; k++) { 
					matrix[k][n]=0;
					matrix[n][k]=0;
				}					
				matrix[n][n]=1;
			}
			if (node.isRestrainMY()) {
				n=node.getIndex()+4;
				for (int k=0; k<matrix.length; k++) { 
					matrix[k][n]=0;
					matrix[n][k]=0;
				}					
				matrix[n][n]=1;
			}
			if (node.isRestrainMZ()) {
				n=node.getIndex()+5;
				for (int k=0; k<matrix.length; k++) { 
					matrix[k][n]=0;
					matrix[n][k]=0;
				}					
				matrix[n][n]=1;
			}
		}
	}
	
	/**
	 * Resetting, like running the constructor again
	 * @param nodesOfStructure
	 * @param elementsOfStructure
	 */
	public void reset(Node[] nodesOfStructure, Element[] elementsOfStructure) {
		nodes = nodesOfStructure;
		elements = elementsOfStructure;
		matrix = new double[nodes.length*6][nodes.length*6];
		formStiffnessMatrix();
		reverse = MathExtension.matrixInverse(matrix);
	}
	
	/**
	 * Resetting, useful for geometrically non-linear analysis
	 * @param nodesOfStructure
	 */
	public void reset(Node[] nodesOfStructure) {
		nodes = nodesOfStructure;
		matrix = new double[nodes.length*6][nodes.length*6];
		formStiffnessMatrix();
		reverse = MathExtension.matrixInverse(matrix);
	}
	
	/**
	 * Resetting, useful for non-linear analysis
	 * @param elementsOfStructure
	 */
	public void reset(Element[] elementsOfStructure) {
		elements = elementsOfStructure;
		matrix = new double[nodes.length*6][nodes.length*6];
		formStiffnessMatrix();
		reverse = MathExtension.matrixInverse(matrix);
	}

}
