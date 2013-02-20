package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JSplitPane;
import javax.swing.JInternalFrame;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Choice;
import javax.swing.JComboBox;

public class LuceneSearchUI {

	private JFrame frmInGroup;
	private JTextField textSearchStd;
	private JTextField textFrom;
	private JTextField textTo;
	private JTextField textSubject;
	private JTextField textBodyHas;
	private JTextField textBodyDoesNot;
	private JTextField textFromPos;
	private JTextField textToPos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LuceneSearchUI window = new LuceneSearchUI();
					window.frmInGroup.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LuceneSearchUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInGroup = new JFrame();
		frmInGroup.setResizable(false);
		frmInGroup.setTitle("IN4325 - Group 11 - Lucene Search");
		frmInGroup.setBounds(100, 100, 450, 300);
		frmInGroup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInGroup.setSize(800,600);
		
		JMenuBar menuBar = new JMenuBar();
		frmInGroup.setJMenuBar(menuBar);
		
		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);
		
		JMenuItem mntmUpdateIndex = new JMenuItem("Update Index");
		mnTools.add(mntmUpdateIndex);
		
		JMenuItem mntmClose = new JMenuItem("Close");
		mnTools.add(mntmClose);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmHelp = new JMenuItem("Help");
		mnHelp.add(mntmHelp);
		
		JTabbedPane searchTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmInGroup.getContentPane().add(searchTabbedPane, BorderLayout.CENTER);
		
		JPanel stdPanel = new JPanel();
		searchTabbedPane.addTab("Standard", null, stdPanel, null);
		
		textSearchStd = new JTextField();
		textSearchStd.setColumns(25);
		
		JButton btnSearchStd = new JButton("Search");
		
		JLabel lblLucene = new JLabel("Lucene !");
		lblLucene.setHorizontalAlignment(SwingConstants.CENTER);
		lblLucene.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GroupLayout gl_stdPanel = new GroupLayout(stdPanel);
		gl_stdPanel.setHorizontalGroup(
			gl_stdPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_stdPanel.createSequentialGroup()
					.addGroup(gl_stdPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_stdPanel.createSequentialGroup()
							.addGap(83)
							.addComponent(textSearchStd, GroupLayout.PREFERRED_SIZE, 623, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_stdPanel.createSequentialGroup()
							.addGap(362)
							.addComponent(btnSearchStd))
						.addGroup(gl_stdPanel.createSequentialGroup()
							.addGap(349)
							.addComponent(lblLucene, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(83, Short.MAX_VALUE))
		);
		gl_stdPanel.setVerticalGroup(
			gl_stdPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_stdPanel.createSequentialGroup()
					.addGap(37)
					.addComponent(lblLucene)
					.addGap(27)
					.addComponent(textSearchStd)
					.addGap(11)
					.addComponent(btnSearchStd)
					.addGap(381))
		);
		stdPanel.setLayout(gl_stdPanel);
		
		JPanel advPanel = new JPanel();
		searchTabbedPane.addTab("Advanced", null, advPanel, null);
		
		JButton btnSearchAdv = new JButton("Search");
		btnSearchAdv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		textFrom = new JTextField();
		textFrom.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("From");
		
		JLabel lblToccbcc = new JLabel("TO/CC/BCC");
		
		textTo = new JTextField();
		textTo.setColumns(10);
		
		JLabel lblSubject = new JLabel("Subject");
		
		textSubject = new JTextField();
		textSubject.setColumns(10);
		
		JLabel lblHasThe = new JLabel("Has the words");
		
		JLabel lblDoesntTheWords = new JLabel("Doesn't have");
		
		textBodyHas = new JTextField();
		textBodyHas.setColumns(10);
		
		textBodyDoesNot = new JTextField();
		textBodyDoesNot.setColumns(10);
		
		JLabel lblDate = new JLabel("Date");
		
		JComboBox comboBoxDate = new JComboBox();
		
		JLabel lblFromPos = new JLabel("From Pos");
		
		textFromPos = new JTextField();
		textFromPos.setColumns(10);
		
		textToPos = new JTextField();
		textToPos.setColumns(10);
		
		JLabel lblToPos = new JLabel("TO Pos");
		GroupLayout gl_advPanel = new GroupLayout(advPanel);
		gl_advPanel.setHorizontalGroup(
			gl_advPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_advPanel.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_advPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnSearchAdv)
						.addGroup(gl_advPanel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_advPanel.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblDoesntTheWords, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(textBodyDoesNot, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_advPanel.createSequentialGroup()
								.addGroup(gl_advPanel.createParallelGroup(Alignment.TRAILING)
									.addGroup(gl_advPanel.createSequentialGroup()
										.addGroup(gl_advPanel.createParallelGroup(Alignment.TRAILING)
											.addGroup(gl_advPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(lblToccbcc, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNewLabel))
											.addComponent(lblSubject, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
										.addGap(35))
									.addGroup(gl_advPanel.createSequentialGroup()
										.addComponent(lblHasThe, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
										.addGap(18)))
								.addGroup(gl_advPanel.createParallelGroup(Alignment.LEADING)
									.addComponent(textBodyHas, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)
									.addComponent(textSubject, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_advPanel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(textFrom, Alignment.LEADING)
										.addComponent(textTo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))))))
					.addGap(49)
					.addGroup(gl_advPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_advPanel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblFromPos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblToPos, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)))
					.addGroup(gl_advPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_advPanel.createSequentialGroup()
							.addGap(35)
							.addComponent(textFromPos, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_advPanel.createSequentialGroup()
							.addGap(35)
							.addGroup(gl_advPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBoxDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textToPos, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)))))
		);
		gl_advPanel.setVerticalGroup(
			gl_advPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_advPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_advPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_advPanel.createSequentialGroup()
							.addGroup(gl_advPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(textFrom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(12)
							.addGroup(gl_advPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblToccbcc)
								.addComponent(textTo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_advPanel.createSequentialGroup()
							.addGroup(gl_advPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(textFromPos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_advPanel.createSequentialGroup()
									.addGap(3)
									.addComponent(lblFromPos)))
							.addGap(12)
							.addGroup(gl_advPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_advPanel.createSequentialGroup()
									.addGap(3)
									.addComponent(lblToPos))
								.addComponent(textToPos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGroup(gl_advPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_advPanel.createSequentialGroup()
							.addGap(3)
							.addGroup(gl_advPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_advPanel.createSequentialGroup()
									.addGroup(gl_advPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(textSubject, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDate))
									.addGap(6)
									.addGroup(gl_advPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(textBodyHas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblHasThe))
									.addGap(12))
								.addGroup(gl_advPanel.createSequentialGroup()
									.addGroup(gl_advPanel.createSequentialGroup()
										.addComponent(lblSubject)
										.addGap(18))
									.addGap(23)))
							.addGroup(gl_advPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(textBodyDoesNot, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDoesntTheWords))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnSearchAdv))
						.addGroup(gl_advPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBoxDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(323, Short.MAX_VALUE))
		);
		advPanel.setLayout(gl_advPanel);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
	}
}
