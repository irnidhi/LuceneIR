package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextField;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.AbstractAction;
import javax.swing.Action;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;

import data.Email;

import work.LuceneSearch;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

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
	private LuceneSearch luceneSearch = null;
	private Email email = null;
	private ArrayList<Document> listResult = null;
	private String dateFrom = "";
	private String dateTo = "";
	private String[] columNames = { "From", "Subject", "Date", "Body"};
	private JTable tableStd;
	private JTable tableAdv;

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
		luceneSearch = new LuceneSearch();
		email = new Email();
		listResult = new ArrayList<Document>();
		

		
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
		mntmClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmInGroup.dispose();
			}
		});
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
		
		final JButton btnSearchStd = new JButton("Search");
		btnSearchStd.setEnabled(false);
		btnSearchStd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Search standard will be performed");
				listResult = null;
				if (!textSearchStd.getText().equals("")) {
					try {
						listResult = luceneSearch.standardQuery(textSearchStd.getText());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
				viewResult(listResult, tableStd);
			}
		});

		
		JLabel lblLucene = new JLabel("Lucene !");
		lblLucene.setHorizontalAlignment(SwingConstants.CENTER);
		lblLucene.setFont(new Font("Tahoma", Font.PLAIN, 16));
		

		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_stdPanel = new GroupLayout(stdPanel);
		gl_stdPanel.setHorizontalGroup(
			gl_stdPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_stdPanel.createSequentialGroup()
					.addGroup(gl_stdPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_stdPanel.createSequentialGroup()
							.addGap(362)
							.addComponent(btnSearchStd))
						.addGroup(gl_stdPanel.createSequentialGroup()
							.addGap(349)
							.addComponent(lblLucene, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_stdPanel.createSequentialGroup()
							.addGap(83)
							.addComponent(textSearchStd, GroupLayout.PREFERRED_SIZE, 623, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_stdPanel.createSequentialGroup()
							.addGap(70)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 648, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(71, Short.MAX_VALUE))
		);
		gl_stdPanel.setVerticalGroup(
			gl_stdPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_stdPanel.createSequentialGroup()
					.addGap(37)
					.addComponent(lblLucene)
					.addGap(27)
					.addComponent(textSearchStd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(btnSearchStd)
					.addGap(41)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
					.addGap(200))
		);
		Object[][] data = {
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""},

			};
		tableStd = new JTable(data, columNames);
		tableStd.setEnabled(false);
		scrollPane.setViewportView(tableStd);
		stdPanel.setLayout(gl_stdPanel);
		
		JPanel advPanel = new JPanel();
		searchTabbedPane.addTab("Advanced", null, advPanel, null);
		


		
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
		
		final JButton btnSearchAdv = new JButton("Search");
		btnSearchAdv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Search advanced will be performed");
				listResult = null;
				try {
					email = new Email("", dateFrom, dateTo, "", textFrom.getText(), textFromPos.getText(), 
							"", textTo.getText(), textToPos.getText(), textSubject.getText()
							, textBodyHas.getText() + " AND (NOT " + textBodyDoesNot+ ")" , "");
					listResult = luceneSearch.assistedQuery(email);
					viewResult(listResult, tableAdv);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 

			}
		});
		btnSearchAdv.setEnabled(false);
		
		JLabel lblToPos = new JLabel("TO Pos");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_advPanel = new GroupLayout(advPanel);
		gl_advPanel.setHorizontalGroup(
			gl_advPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_advPanel.createSequentialGroup()
					.addGroup(gl_advPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_advPanel.createSequentialGroup()
							.addGap(28)
							.addGroup(gl_advPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnSearchAdv)
								.addGroup(gl_advPanel.createSequentialGroup()
									.addGroup(gl_advPanel.createParallelGroup(Alignment.LEADING)
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
												.addGroup(gl_advPanel.createSequentialGroup()
													.addGroup(gl_advPanel.createParallelGroup(Alignment.LEADING)
														.addComponent(textSubject, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)
														.addGroup(gl_advPanel.createParallelGroup(Alignment.TRAILING, false)
															.addComponent(textFrom, Alignment.LEADING)
															.addComponent(textTo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)))
													.addGap(42)
													.addGroup(gl_advPanel.createParallelGroup(Alignment.LEADING)
														.addComponent(lblFromPos)
														.addComponent(lblToPos, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
													.addGap(40)
													.addGroup(gl_advPanel.createParallelGroup(Alignment.LEADING, false)
														.addComponent(textFromPos, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
														.addComponent(comboBoxDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(textToPos)))))
										.addGroup(gl_advPanel.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblDoesntTheWords, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(textBodyDoesNot, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED))))
						.addGroup(gl_advPanel.createSequentialGroup()
							.addGap(57)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 674, GroupLayout.PREFERRED_SIZE)))
					.addGap(182))
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
								.addComponent(textTo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
									.addComponent(lblSubject)
									.addGap(41)))
							.addGroup(gl_advPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(textBodyDoesNot, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDoesntTheWords))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnSearchAdv))
						.addGroup(gl_advPanel.createSequentialGroup()
							.addGroup(gl_advPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFromPos)
								.addComponent(textFromPos, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_advPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblToPos)
								.addComponent(textToPos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBoxDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
					.addGap(27))
		);
		
		tableAdv = new JTable(data,columNames);
		tableAdv.setVisible(false);
		scrollPane_1.setViewportView(tableAdv);
		advPanel.setLayout(gl_advPanel);
		
		mntmUpdateIndex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					luceneSearch.buildIndex();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				btnSearchStd.setEnabled(true);
				btnSearchAdv.setEnabled(true);
			}
		});
	}

	private void viewResult(ArrayList<Document> listResult, JTable table){
		String[][] data = {
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""},

			};	
		if (listResult!=null){
			Iterator<Document> iter = listResult.iterator();
			int i = 0;
			
			while (iter.hasNext()){
				Document doc = iter.next();
				data [i][0] = doc.get("senderName") + " : " + doc.get("senderEmails");
				data [i][1] = doc.get("subject");
				data [i][2] = doc.get("date");
				data [i][3] = doc.get("body");
				i++;
			}
			
		}
			
		table.setModel(new DefaultTableModel(data, columNames));
			
	}
}
