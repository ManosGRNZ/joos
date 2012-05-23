package gr.structuraldesign.analysis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This is the core class that holds all the structure.
 * It uses an arraylist of nodes. It uses the index of the arraylist
 * and not an individual index for each node. In the input file there 
 * are no index fields. 
 * The arraylist of elements can hold elements of all types.
 * The Element class is designed to be abstract.
 * 
 * @author Manos Bairaktaris (bairaktaris@gmail.com)
 *
 */
public class Structure implements Serializable {
	
	private ArrayList<Node> buildingNodes = new ArrayList<Node>();
	private ArrayList<Element> buildingElements = new ArrayList<Element>();
	private ArrayList<Section> buildingSections = new ArrayList<Section>();
	private ArrayList<Load> buildingLoads = new ArrayList<Load>();
	private ArrayList<Material> materials = new ArrayList<Material>();
	
	private StiffnessMatrix structureStiffnessMatrix;
	
	public void setStructureStiffnessMatrix() {
		this.structureStiffnessMatrix = new 
				StiffnessMatrix( buildingNodes.toArray(new Node[buildingNodes.size()]), 
						buildingElements.toArray(new Element[buildingElements.size()]));
	}
	
	private String[] getParameters(String line) {
		String[] result;
		
		result = ( line.toUpperCase() ).split(" ");
		for (int i=0; i<result.length; i++) result[i] = result[i].trim();
		
		return result;
	}
	
	public void importMaterials(File materialsFile) {
		try {
			BufferedReader matInput = new BufferedReader(new FileReader(materialsFile));
			String line;
			String[] word;
			
			int index=1;
			
			Double E,Eh,G,v,yieldStrain,ultimateStrain;
			
			while ((line = matInput.readLine()) != null) {
				word = getParameters(line);
				switch (word.length) {
				case 1:
					E = Double.parseDouble(word[0]);
					materials.add(new Material(E));
					break;
				case 2:
					E = Double.parseDouble(word[0]);
					v = Double.parseDouble(word[1]);
					materials.add(new Material(E,v));
					break;
				case 3:
					E = Double.parseDouble(word[0]);
					Eh = Double.parseDouble(word[1]);
					G = Double.parseDouble(word[2]);
					v = Double.parseDouble(word[3]);
					yieldStrain = Double.parseDouble(word[4]);
					ultimateStrain = Double.parseDouble(word[5]);
					materials.add(new Material(E, Eh, G, v, yieldStrain, ultimateStrain));
					break;
				default:
					System.err.printf("Problem in line %d for the materials input. \n",index);
					System.err.println("Material set to E=1E+7 and v=0.2");
					materials.add(new Material(1E+7));
					System.out.printf("\nMaterial with index %d set to: \n %s \n",index,materials.get(index).toString());
				}
				
				index++;
				
			}
			
			matInput.close();
		} catch (Exception ex) {
			System.err.println("problem with file I/O in Structure.importMaterials");
			ex.printStackTrace();
		}
	}
	
	/**
	 * Every row of the file contains the following:
	 * material (integer)
	 * number of outer points (integer)
	 * pairs of y0 and z0 (double[]) for the number of outer points
	 * [optional]
	 * number of internal holes (integer, 0 is redundant) 
	 * [for each hole]
	 * number of inner points (integer)
	 * pairs of y1 and z1 (double[][]) for the number of inner points
	 * 
	 * {see definition of inner holes in the Section.java comments}
	 *  
	 * @param sectionFile
	 */
	public void importSectionGeometry(File sectionFile) {
		try {
			BufferedReader sectionInput = new BufferedReader(new FileReader(sectionFile));
			String line;
			String[] word;			
			
			Material mat;
			int material;
			int numOfExternalPoints=0;
			int numOfInternalHoles=0;
			int numOfInternalPoints=0;
			double[] y0;
			double[] z0;
			double[][] y1;
			double[][] z1;
			
			while ((line = sectionInput.readLine()) != null) {				
				word = getParameters(line);
				
				material = Integer.parseInt(word[0]);
				mat = materials.get(material);
				
				numOfExternalPoints = Integer.parseInt(word[1]);
				
				y0 = new double[numOfExternalPoints];
				z0 = new double[numOfExternalPoints];
				for (int point=0; point<numOfExternalPoints; point++) {
					y0[point] = Double.parseDouble(word[1+point*2]);
					z0[point] = Double.parseDouble(word[2+point*2]);					
				}
				
				if (word.length>(2+numOfExternalPoints*2)) {  // If there are internal holes
					numOfInternalHoles = Integer.parseInt(word[3+numOfExternalPoints*2]);
					
					y1 = new double[numOfInternalHoles][];
					z1 = new double[numOfInternalHoles][];
					for (int hole=0; hole<numOfInternalHoles; hole++) {					
						numOfInternalPoints = Integer.parseInt(word[4+numOfExternalPoints*2+hole*2]);
						y1 = new double[hole][numOfInternalPoints];
						z1 = new double[hole][numOfInternalPoints];
						for (int point=0; point<numOfInternalPoints; point++) {
							y1[hole][point] = Integer.parseInt(word[4+numOfExternalPoints*2+hole*2+point]);
							z1[hole][point] = Integer.parseInt(word[4+numOfExternalPoints*2+hole*2+point+1]);
						}
					}
					buildingSections.add(new Section(mat, y0, z0, y1, z1));
				} else {  // if there are no internal holes
					buildingSections.add(new Section(mat, y0, z0));
				}
				
			}
		} catch (Exception ex) {
			System.err.println("problem in Structure.importSectionGeometry");
			ex.printStackTrace();
		}
	}
	
	public void importSectionProperties(File sectionFile) {
		try {
			BufferedReader sectionInput = new BufferedReader(new FileReader(sectionFile));
			String line;
			String[] word;			
			
			Material mat;
			int material;
			
			while ((line = sectionInput.readLine()) != null) {				
				word = getParameters(line);				
				material = Integer.parseInt(word[0]);
				mat = materials.get(material);
				double A = Double.parseDouble(word[1]);
				double Ay = Double.parseDouble(word[2]);
				double Az = Double.parseDouble(word[3]);
				double Iy = Double.parseDouble(word[4]);
				double Iz = Double.parseDouble(word[5]);
				double Iyz = Double.parseDouble(word[6]);
				double It = Double.parseDouble(word[7]);
				buildingSections.add(new Section(mat, A, Ay, Az, Iy, Iz, Iyz, It));
			}
		} catch (Exception ex) {
			System.err.println("problem in Structure.importSectionProperties");
			ex.printStackTrace();
		}
	}

	public void importNodes(File nodesFile) {
		try {
			BufferedReader nodesInput = new BufferedReader(new FileReader(nodesFile));
			String line;
			String[] word;
						
			int index=1;
			double x,y,z;
			String degreeOfFreedom;
						
			while((line = nodesInput.readLine()) != null) {
				word = getParameters(line);				
				x = Double.parseDouble(word[0]);
				y = Double.parseDouble(word[1]);
				z = Double.parseDouble(word[2]);
				degreeOfFreedom = word[3];				
				buildingNodes.add(new Node(index,x,y,z));
				
				if (word.length>3) for (int i=3; i<word.length; i++) {
					if (degreeOfFreedom=="FIX") {
						buildingNodes.get(index).setRestrainAllD(true);
						buildingNodes.get(index).setRestrainAllM(true);
					}
					if (degreeOfFreedom=="PP") {
						buildingNodes.get(index).setRestrainAllD(true);					
					}
					if (degreeOfFreedom=="PX") {
						buildingNodes.get(index).setRestrainDX(true);
					}
					if (degreeOfFreedom=="PY") {
						buildingNodes.get(index).setRestrainDY(true);
					}
					if (degreeOfFreedom=="PZ") {
						buildingNodes.get(index).setRestrainDY(true);
					}
					if (degreeOfFreedom=="MX") {
						buildingNodes.get(index).setRestrainMX(true);
					}
					if (degreeOfFreedom=="MY") {
						buildingNodes.get(index).setRestrainMY(true);
					}
					if (degreeOfFreedom=="MZ") {
						buildingNodes.get(index).setRestrainMY(true);
					}				
				} // end if/for
				// There is no ELSE. It means that if there are no argument all degrees are free 
					index++;
			} // end while
			nodesInput.close();
		} catch (Exception ex) {
			System.err.println("problem with file I/O in Structure.importNodes");
			ex.printStackTrace();
		}
	}
	
	public void importElements(File elementsFile) {
		try {
			BufferedReader elementsInput = new BufferedReader(new FileReader(elementsFile));
			String line;
			String[] word;
			
			ElementType elementType;			
			int sectionIndex;
			double localAngle;
			Section sect;
						
			int index=0;
			while((line = elementsInput.readLine()) != null) {
				word = getParameters(line);				
				elementType = ElementType.valueOf( word[0] );
				sectionIndex = Integer.parseInt(word[1]);
				sect = buildingSections.get(sectionIndex-1);
				localAngle = Double.parseDouble(word[2]);
				
				Node[] nodes = new Node[word.length-3];
				for (int i=3; i<word.length; i++) {					
					nodes[i-3] = getNodeByIndex( Integer.parseInt( word[i] ) );
				}
							
				switch (elementType) {
				case BEAM:
					buildingElements.add(new Beam(sectionIndex,sect,localAngle,nodes));
					break;
				
				default:
					System.err.println("Problem in class Structure, method importElements:");
					System.err.printf("Element with index %d does not correspond to available list of elements \n",index);
					System.err.println("No element added to the structure");
					break;
				}
				index++;
			} // close while
			elementsInput.close();
			
		} catch (Exception ex) {
			System.err.println("problem with file I/O in Structure.importElements");
			ex.printStackTrace();
		}
	}
	
	public void importLoads(File loadsFile) {
		try {
			BufferedReader loadsInput = new BufferedReader(new FileReader(loadsFile));
			String line;
			String[] word;
			
			int loadIndex;  // This is not the index of the element or node - perhaps redundant
			LoadType load;
			int element;  // or node
			double[] loadParameters; // different number for each load case
			
			while ((line = loadsInput.readLine()) != null) {
				word = getParameters(line);				
				loadIndex = Integer.parseInt(word[0]);
				load = LoadType.valueOf(word[1]);
				
				/*
				 * *** HAVE TO SET LOADS CLASSES FIRST ***
				 * Then implement a switch statement and add to
				 * the corresponding arraylist
				 */
				
				switch (load) {
					case NODE_PX:
					case NODE_PY:
					case NODE_PZ:
					case NODE_MX:
					case NODE_MY:
					case NODE_MZ:
						int node = Integer.parseInt(word[2]);
						double force = Double.parseDouble(word[3]);
						buildingLoads.add(new NodalLoad(node, load, force));
						break;
					case BEAM_GLOBAL_POINT_PX:
					case BEAM_GLOBAL_POINT_PY:
					case BEAM_GLOBAL_POINT_PZ:
					case BEAM_GLOBAL_POINT_MX:
					case BEAM_GLOBAL_POINT_MY:
					case BEAM_GLOBAL_POINT_MZ:
						int beam = Integer.parseInt(word[2]);
						double loadLocation = Double.parseDouble(word[3]);
						force = Double.parseDouble(word[4]);
						buildingLoads.add(new BeamPointLoad(beam, load, loadLocation, force));	
						break;
					
					default:
						System.out.println("Problem in Structure.importLoads.\n No corresponding Load Type.");
						break;
				}
				
			}
			loadsInput.close();
			
		} catch (Exception ex) {
			System.err.println("problem with file I/O in Structure.importLoads");
			ex.printStackTrace();
		}
	}
	
	private Node getNodeByIndex(int index) {
		Node returnNode=buildingNodes.get(0);
		for (Node n : buildingNodes) {
			if (n.getIndex()==index) {
				returnNode=n;
			}
		}
		return returnNode;
	}
	
	private void transferLoadsToNodes(double[] nodalLoadMatrix, ArrayList<Load> loads) {
		double[] nodalLoads = new double[loads.size()];		
		for (int i=0; i<nodalLoads.length; i++) {
			nodalLoadMatrix = loads.get(i).setValueToLoadMatrix(nodalLoadMatrix);		
		}
	}
	
	public void linearAnalysis_printDisplacements(File displacementFile) {

		double[] nodeLoadMatrix = new double[6*buildingNodes.size()]; 
		
		transferLoadsToNodes(nodeLoadMatrix, buildingLoads);
		
		LinearSolver solver = new LinearSolver(nodeLoadMatrix, structureStiffnessMatrix);
		double[] degreeOfFreedomDisplacements = solver.getDisplacements();
		
		try {
			BufferedWriter dispOut = new BufferedWriter(new FileWriter(displacementFile));
			String line;
			System.out.printf("Number of building nodes: %d \n", buildingNodes.size());
			System.out.printf("Number of Degrees of Freedom: %d \n", degreeOfFreedomDisplacements.length);
			for (int n=0; n<buildingNodes.size(); n++) {
				line =  String.valueOf(n); //buildingNodes.get(n).toString();
				for (int i=0; i<6; i++) {
					line += "\t"+Double.toString(degreeOfFreedomDisplacements[i+6*n]);
				}
				line += "\n";
				dispOut.append(line);
				System.out.print("n = "+line);
			}
			dispOut.close();
		} catch (Exception ex) {
			System.err.println("problem in Structure.linearAnalysis_printDisplacements");
			ex.printStackTrace();
		}		
	}
}
