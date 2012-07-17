package gr.structuraldesign.testing;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFrameTest extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtTest;
	private JTextField textField;
	private JTextField textField_1;
	public JFrameTest() {
		this.setSize(300, 200);
		setTitle("Sum of two numbers calculation");
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNum = new JLabel("Num1");
		getContentPane().add(lblNum, "4, 4, right, default");
		
		txtTest = new JTextField();
		getContentPane().add(txtTest, "8, 4, left, default");
		txtTest.setColumns(6);
		
		JLabel lblNum_1 = new JLabel("Num2");
		getContentPane().add(lblNum_1, "4, 8, right, default");
		
		textField = new JTextField();
		getContentPane().add(textField, "8, 8, left, default");
		textField.setColumns(6);
		
		JButton btnCalculate = new JButton("Calculate Sum");
		getContentPane().add(btnCalculate, "4, 16, left, default");
		btnCalculate.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						double n1,n2;
						String s1,s2;
						s1 = txtTest.getText();
						n1 = getDouble(s1);
						txtTest.setText(String.format("%5.3f",n1));
						s2 = textField.getText();
						n2 = getDouble(s2);
						textField.setText(String.format("%5.3f",n2));
						textField_1.setText(String.format("%5.3f", (n1+n2)));
					}
				});
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_1.setEditable(false);
		getContentPane().add(textField_1, "8, 16, left, default");
		textField_1.setColumns(6);
	}
	
	private double getDouble(String s) {
		double d;
		try {
			d = Double.parseDouble(s);			
		} catch (Exception ex) {
			d=0;
		}
		return d;
	}
	

	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		JFrameTest set = new JFrameTest();
		set.show(true);
		set.setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}

}
