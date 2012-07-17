package gr.structuraldesign.analysis;

import java.awt.Dimension;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GenTest {
	
	private Structure structure;
		
	public static void main(String[] args) {		
		GenTest test = new GenTest();
		test.setup_linux();		
	}
	
	private void setup_linux() {
		structure = new Structure();
		structure.importMaterials(new File("/home/mb/Dropbox/StructuralDesignTest/materials.dat"));
		structure.importSectionProperties(new File("/home/mb/Dropbox/StructuralDesignTest/sections.dat"));
		structure.importNodes(new File("/home/mb/Dropbox/StructuralDesignTest/nodes.dat"));
		structure.importElements(new File("/home/mb/Dropbox/StructuralDesignTest/elements.dat"));
		structure.importLoads(new File("/home/mb/Dropbox/StructuralDesignTest/loads.dat"));
		structure.setStructureStiffnessMatrix();
		structure.linearAnalysis_printDisplacements(new File("/home/mb/Dropbox/StructuralDesignTest/displacements.out"));
	}
	
	private void setup_win() {
		structure = new Structure();
		structure.importMaterials(new File("C:/Documents and Settings/MANOS/My Documents/Dropbox/StructuralDesignTest/materials.dat"));
		structure.importSectionProperties(new File("C:/Documents and Settings/MANOS/My Documents/Dropbox/StructuralDesignTest/sections.dat"));
		structure.importNodes(new File("C:/Documents and Settings/MANOS/My Documents/Dropbox/StructuralDesignTest/nodes.dat"));
		structure.importElements(new File("C:/Documents and Settings/MANOS/My Documents/Dropbox/StructuralDesignTest/elements.dat"));
		structure.importLoads(new File("C:/Documents and Settings/MANOS/My Documents/Dropbox/StructuralDesignTest/loads.dat"));
		structure.setStructureStiffnessMatrix();
		structure.linearAnalysis_printDisplacements(new File("C:/Documents and Settings/MANOS/My Documents/Dropbox/StructuralDesignTest/displacements.out"));
	}
	
	@SuppressWarnings({ "unused", "deprecation" })
	private void consoleTest() {
		Console cnl = new Console();
		cnl.setSize(new Dimension(650, 350));
		cnl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cnl.show(true);
	}
	
	@SuppressWarnings("unused")
	private void testGUI() {
		int i = AssistanceMethods.getIntegerGUI("Getting an Integer", "Type and Integer");
		double d = AssistanceMethods.getDoubleGUI("Getting a Double", "Type a Real number");	
		double sum = i+d;
		JOptionPane.showMessageDialog(null, String.valueOf(sum));		
	}
}
