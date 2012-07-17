package gr.structuraldesign.analysis;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;

public class Console extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Console() {
		setTitle("Structural Design Console");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		Dimension btnDimension = new Dimension(90, 25);
		
		JMenuItem mntmFile = new JMenuItem("File");
		mntmFile.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mntmFile);
		
		JMenuItem mntmEdit = new JMenuItem("Edit");
		mntmEdit.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mntmEdit);
		
		JMenuItem mntmHelp = new JMenuItem("Help");
		mntmHelp.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mntmHelp);
		getContentPane().setLayout(new MigLayout("", "[grow][][][][][][][][][][][][]", "[grow][][][][grow][grow][]"));
		
		JEditorPane dtrpnInput = new JEditorPane();
		dtrpnInput.setBackground(Color.BLACK);
		dtrpnInput.setForeground(Color.YELLOW);
		dtrpnInput.setFont(new Font("Courier New", Font.PLAIN, 12));
		dtrpnInput.setText("Test Input");
		getContentPane().add(dtrpnInput, "cell 0 0 11 3,grow");
		
		JButton btnExecute = new JButton("Execute");
		btnExecute.setToolTipText("Execute the input code");
		getContentPane().add(btnExecute, "cell 12 0");
		btnExecute.setPreferredSize(btnDimension);
		
		JButton btnClearInput = new JButton("Clear");
		btnClearInput.setToolTipText("Clear the input Area");
		getContentPane().add(btnClearInput, "cell 12 1");
		btnClearInput.setPreferredSize(btnDimension);
		
		JTextArea txtrOutput = new JTextArea();
		txtrOutput.setEditable(false);
		txtrOutput.setForeground(Color.GREEN);
		txtrOutput.setBackground(Color.BLACK);
		txtrOutput.setFont(new Font("Courier New", Font.PLAIN, 12));
		txtrOutput.setText("Test Output");
		getContentPane().add(txtrOutput, "cell 0 4 11 3,grow");
		
		JButton btnExport = new JButton(" Export ");
		btnExport.setToolTipText("Export the results to a text file");
		getContentPane().add(btnExport, "cell 12 4");
		btnExport.setPreferredSize(btnDimension);
		
		JButton btnClearOutput = new JButton("Clear");
		btnClearOutput.setToolTipText("Clear the output area");
		getContentPane().add(btnClearOutput, "cell 12 5");
		btnClearOutput.setPreferredSize(btnDimension);
	}

}
