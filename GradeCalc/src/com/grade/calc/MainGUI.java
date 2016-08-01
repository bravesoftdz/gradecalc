package com.grade.calc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;

public class MainGUI {

	private JFrame frmGradeCalculatorweltec;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.frmGradeCalculatorweltec.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		frmGradeCalculatorweltec = new JFrame();
		frmGradeCalculatorweltec.setResizable(false);
		frmGradeCalculatorweltec.setTitle("Grade Calculator");
		frmGradeCalculatorweltec.setBounds(100, 100, 375, 336);
		frmGradeCalculatorweltec.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGradeCalculatorweltec.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Clear Table");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < 16; i++)
		            for (int a = 0; a < 3; a++)
		            {
		                table.setValueAt(null, i, a);
		            }
			}
		});
		btnNewButton.setBounds(10, 282, 102, 23);
		frmGradeCalculatorweltec.getContentPane().add(btnNewButton);
		
		JButton btnCalculateYourGrade = new JButton("Calculate your grade");
		btnCalculateYourGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DecimalFormat percentageDisplayed = new DecimalFormat("#.#");
		        float percentage = 0;
		        float grade = 0;
		        float weight;
		        //
		        for (int i = 0; i < 16; i++)
		            if (table.getValueAt(i, 0) != null && table.getValueAt(i, 1) != null)
		            {
		                String temp = table.getValueAt(i, 1).toString();
		                String[] marks = temp.split("/");
		                float myMark = Float.parseFloat(marks[0]);
		                float totalMark = Float.parseFloat(marks[1]);
		                percentage = (myMark / totalMark * 100);
		                table.setValueAt(percentageDisplayed.format(percentage) + "%", i, 2);  
		                //
		                temp = table.getValueAt(i, 0).toString();
		                weight = Float.parseFloat(temp) / 100;
		                grade += (weight * (percentage / 100));
		            }
		        //
		        msgBox("Results will be recorded as A to F\n"
		                                          + "C or better is a passing grade\n\n"
		                                          + "Passing grades:\n"
		                                          + "A+     90% to 100%\n"
		                                          + "A       80% to 89%\n"
		                                          + "B+    75% to 79%\n"
		                                          + "B       65% to 74%\n"
		                                          + "C+    60% to 64%\n"
		                                          + "C      50% to 59%\n\n"
		                                          + "Failing grades:\n"
		                                          + "D+    45% to 49%\n"
		                                          + "D      40% to 45%\n"
		                                          + "E+    35% to 39%\n"
		                                          + "E       0% to 34%\n"
		                                          + "F        total >= 50% and exam < 40%\n"
		                                          + "F        total < 50% and exam > 40%\n\n"
		                                          + "Your grade (in %): " + Math.round(grade * 100), "Grade");
			}
		});
		btnCalculateYourGrade.setBounds(209, 282, 160, 23);
		frmGradeCalculatorweltec.getContentPane().add(btnCalculateYourGrade);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 369, 279);
		frmGradeCalculatorweltec.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Weighting (%)", "Mark (x/x)", "%"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		scrollPane.setViewportView(table);
		
		JLabel lblClementCampagna = new JLabel("C. Campagna");
		lblClementCampagna.setBounds(123, 286, 102, 14);
		frmGradeCalculatorweltec.getContentPane().add(lblClementCampagna);
	}
	
	public static void msgBox(String message, String title)
    {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
