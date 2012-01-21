package gr.structuraldesign.analysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
 * @author Manos Bairaktaris
 *
 */
public class Structure implements Serializable {
	
	ArrayList<Node> buildingNodes = new ArrayList<Node>();
	ArrayList<Element> buildingElements = new ArrayList<Element>();
	ArrayList<Section> buildingSections = new ArrayList<Section>();
	
	public void importNodes(File nodesFile) {
		try {
			BufferedReader inNodes = new BufferedReader(new FileReader(nodesFile));
			String line;
			String[] word;
						
			int index=1;
			double x,y,z;
			String degreeOfFreedom;
						
			while((line = inNodes.readLine()) != null) {
				word = line.split(" ");				
				x = Double.parseDouble(word[0].trim());
				y = Double.parseDouble(word[1].trim());
				z = Double.parseDouble(word[2].trim());
				degreeOfFreedom = word[3].trim();				
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
		} catch (Exception ex) {
			System.out.println("problem with file I/O in Node.getAllNodes");
			ex.printStackTrace();
		}
	}
	
	public void importElements(File elementsFile) {
		try {
			BufferedReader inElements = new BufferedReader(new FileReader(elementsFile));
			String line;
			String[] word;
			
			String elementType;			
			int sectionIndex;
			double localAngle;
			Section sect;
						
			int index=0;
			while((line = inElements.readLine()) != null) {
				word = line.split(" ");
				elementType = word[0].trim();
				sectionIndex = Integer.parseInt(word[1].trim());
				sect = buildingSections.get(sectionIndex);
				localAngle = Double.parseDouble(word[2].trim());
				
				Node[] nodes = new Node[word.length-3];
				for (int i=3; i<word.length; i++) {					
					nodes[i-3] = getNodeByIndex( Integer.parseInt( word[i].trim() ) );
				}
							
				switch (elementType) {
				case "BEAM":
					buildingElements.add(new Beam(sectionIndex,sect,localAngle,nodes));
					break;

				default:
					System.out.println("Problem in class Structure, method importElements:");
					System.out.printf("Element with index %d does not correspond to available list of elements \n",index);
					System.out.println("No element added to the structure");
					break;
				}
				index++;
			} // close while
			
		} catch (Exception ex) {
			System.out.println("problem with file I/O in Node.getAllNodes");
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

}
