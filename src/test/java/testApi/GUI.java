package testApi;

import java.awt.BorderLayout;
//import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.jfree.ui.RefineryUtilities;

import type.Graph;

public class GUI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		GUI gui = new GUI("OSM 2 NEO4J");
		gui.pack();
		gui.setVisible(true);

	}

	/**
	 * DB browse button
	 */
	private JButton dbPathButton;
	private JTextField dbPathField;
	private JComboBox<String> serverList;
	public static JLabel osmServerTest;
	private JButton start;
	private JButton cancel;
	JTextField osmPathFieldS;
	JTextField osmPathFieldW;
	JTextField osmPathFieldN;
	JTextField osmPathFieldE;
	JCheckBox dbMergeBox;
	
	
	

	/**
	 * Class constructor, initializing frame with title
	 * 
	 * @param title
	 *            frame title
	 */
	public GUI(String title) {
		super(title);
		initializeLookAndFeel();
		fillFrame();

		RefineryUtilities.positionFrameOnScreen(this, 0.3, 0.3);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Initializes the application look and feel depending on the used operating
	 * system
	 */
	private void initializeLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fills the frame with components
	 */
	private void fillFrame() {
		this.getContentPane().setLayout(new BorderLayout());

		JPanel dbPanel = createDBPanel();
		JPanel osmPanel = createOSMPanel();

		this.getContentPane().add(dbPanel, BorderLayout.NORTH);
		this.getContentPane().add(osmPanel, BorderLayout.CENTER);

	}

	/**
	 * creates DB panel
	 * 
	 * @return DB panel
	 */
	private JPanel createDBPanel() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		Border border = BorderFactory.createTitledBorder("DB");
		panel.setBorder(border);
		
		JLabel dbPathLabel = new JLabel();
		dbPathLabel.setText("DB path");
		dbPathField= new JTextField();
		dbPathField.setColumns(20);
		dbPathField.setText("Test");
		dbPathButton = new JButton("Browse");

		dbPathButton.addActionListener(this);
		
		dbMergeBox=new JCheckBox("Merge",false);
		dbMergeBox.isSelected();
		
		GridBagConstraints cons = new GridBagConstraints();
		cons.insets = new Insets(2, 2, 2, 2);
		
		cons.gridy = 0;
		cons.gridx = 0;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weighty = 0.5;
		cons.weightx = 0.2;
		cons.anchor = GridBagConstraints.EAST;
		cons.fill = GridBagConstraints.NONE;
		panel.add(dbPathLabel, cons);
		
		cons.gridy = 0;
		cons.gridx = 1;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weighty = 0.5;
		cons.weightx = 0.6;
		cons.anchor = GridBagConstraints.WEST;
		cons.fill = GridBagConstraints.HORIZONTAL;
		panel.add(dbPathField, cons);
		
		cons.gridy = 0;
		cons.gridx = 2;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weighty = 0.5;
		cons.weightx = 0.2;
		cons.anchor = GridBagConstraints.WEST;
		cons.fill = GridBagConstraints.HORIZONTAL;
		panel.add(dbPathButton, cons);
		
		cons.gridy = 1;
		cons.gridx = 0;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weighty = 0.5;
		cons.weightx = 0.2;
		cons.anchor = GridBagConstraints.WEST;
		cons.fill = GridBagConstraints.NONE;
		panel.add(dbMergeBox, cons);
		
		
		

		// TODO Create DB components
		
		return panel;
	}

	/**
	 * creates OSM panel
	 * 
	 * @return OSM panel
	 */
	private JPanel createOSMPanel() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		Border border = BorderFactory.createTitledBorder("OSM");
		panel.setBorder(border);
		
		JLabel osmPathLabelS = new JLabel();
		osmPathLabelS.setText("South:");
		JLabel osmPathLabelW = new JLabel();
		osmPathLabelW.setText("West:");
		JLabel osmPathLabelN = new JLabel();
		osmPathLabelN.setText("North:");
		JLabel osmPathLabelE = new JLabel();
		osmPathLabelE.setText("East:");
		
		osmPathFieldS= new JTextField();
		osmPathFieldS.setColumns(10);
		osmPathFieldW= new JTextField();
		osmPathFieldW.setColumns(10);
		osmPathFieldN= new JTextField();
		osmPathFieldN.setColumns(10);
		osmPathFieldE= new JTextField();
		osmPathFieldE.setColumns(10);
		osmServerTest = new JLabel();
		osmServerTest.setText("...");
		
		
		
		serverList= new JComboBox<String>(DataReader.SERVER_ROOT_URI);
		serverList.addActionListener(this);
		
		start=new JButton("Start");
		start.addActionListener(this);
		cancel=new JButton("Close");
		cancel.addActionListener(this);
		
		
		GridBagConstraints cons = new GridBagConstraints();
		cons.insets = new Insets(2, 2, 2, 2);
		
		cons.gridy = 0;
		cons.gridx = 0;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weighty = 0.1;
		cons.weightx = 0.2;
		cons.anchor = GridBagConstraints.WEST;
		cons.fill = GridBagConstraints.NONE;
		panel.add(osmPathLabelS, cons);
		
		
		cons.gridy = 1;
		cons.gridx = 0;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weighty = 0.1;
		cons.weightx = 0.2;
		cons.anchor = GridBagConstraints.WEST;
		cons.fill = GridBagConstraints.NONE;
		panel.add(osmPathLabelW, cons);
		
		
		cons.gridy = 2;
		cons.gridx = 0;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weighty = 0.1;
		cons.weightx = 0.2;
		cons.anchor = GridBagConstraints.WEST;
		cons.fill = GridBagConstraints.NONE;
		panel.add(osmPathLabelN, cons);
		
		
		cons.gridy = 3;
		cons.gridx = 0;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weighty = 0.1;
		cons.weightx = 0.2;
		cons.anchor = GridBagConstraints.WEST;
		cons.fill = GridBagConstraints.NONE;
		panel.add(osmPathLabelE, cons);
		
		
		cons.gridy = 0;
		cons.gridx = 1;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weighty = 0.1;
		cons.weightx = 0.2;
		cons.anchor = GridBagConstraints.CENTER;
		cons.fill = GridBagConstraints.NONE;
		panel.add(osmPathFieldS, cons);
		
		
		cons.gridy = 1;
		cons.gridx = 1;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weighty = 0.1;
		cons.weightx = 0.2;
		cons.anchor = GridBagConstraints.CENTER;
		cons.fill = GridBagConstraints.NONE;
		panel.add(osmPathFieldW, cons);
		
		
		cons.gridy = 2;
		cons.gridx = 1;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weighty = 0.1;
		cons.weightx = 0.2;
		cons.anchor = GridBagConstraints.CENTER;
		cons.fill = GridBagConstraints.NONE;
		panel.add(osmPathFieldN, cons);
		
		
		cons.gridy = 3;
		cons.gridx = 1;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weighty = 0.1;
		cons.weightx = 0.2;
		cons.anchor = GridBagConstraints.CENTER;
		cons.fill = GridBagConstraints.NONE;
		panel.add(osmPathFieldE, cons);
		
		
		
		
		cons.gridy = 1;
		cons.gridx = 2;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weighty = 0.4;
		cons.weightx = 0.6;
		cons.anchor = GridBagConstraints.EAST;
		cons.fill = GridBagConstraints.NONE;
		panel.add(serverList, cons);
		
		
		cons.gridy = 3;
		cons.gridx = 2;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weighty = 0.4;
		cons.weightx = 0.6;
		cons.anchor = GridBagConstraints.CENTER;
		cons.fill = GridBagConstraints.NONE;
		panel.add(osmServerTest, cons);
		
		cons.gridy = 6;
		cons.gridx = 0;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weighty = 0.2;
		cons.weightx = 0.5;
		cons.anchor = GridBagConstraints.WEST;
		cons.fill = GridBagConstraints.NONE;
		panel.add(cancel, cons);
		
		cons.gridy = 6;
		cons.gridx = 2;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weighty = 0.2;
		cons.weightx = 0.5;
		cons.anchor = GridBagConstraints.EAST;
		cons.fill = GridBagConstraints.NONE;
		panel.add(start, cons);
		
		
		
		
		return panel;
	}

	/**
	 * Actions necessary to be done when browse button has been clicked
	 */
	private void browseButtonClicked() {
		
		JFileChooser dbFileChooser = new JFileChooser();

		if(dbFileChooser.showSaveDialog(this) == JFileChooser.DIRECTORIES_ONLY) {
			File dbFile = dbFileChooser.getSelectedFile();
			dbPathField.setText(dbFile.getPath());
			
		}
	}
	
	
	
	private boolean fieldsChecker() {
		
		
		if(osmPathFieldS.getText().toString().equals("")||osmPathFieldW.getText().toString().equals("")||osmPathFieldN.getText().toString().equals("")||osmPathFieldE.getText().toString().equals("")){
			osmServerTest.setText("Fill all the geogeraphical coordinate");
			return true;
		}else if((Tool.isValidLongitude(Float.parseFloat((osmPathFieldW).getText().toString()))==false)||(Tool.isValidLongitude(Float.parseFloat((osmPathFieldE).getText().toString()))==false)||(Tool.isValidLatitude(Float.parseFloat((osmPathFieldS).getText().toString()))==false)||(Tool.isValidLatitude(Float.parseFloat((osmPathFieldN).getText().toString()))==false)){
		
			osmServerTest.setText("Set the right coordinate");
			return true;
		}else
		
		return false;
		
	}
	
	
	private void startButtonClicked() {
		
		
		
		File file=new File("export.osm");
		osmServerTest.setText("Download Map");
		try {
			DataReader.downloadSample(file,osmPathFieldS.getText(),osmPathFieldW.getText(),osmPathFieldN.getText(),osmPathFieldE.getText());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	try {
			
	        
			SAXParserFactory factory = SAXParserFactory.newInstance();
	         SAXParser saxParser = factory.newSAXParser();
	         UserHandler userhandler = new UserHandler();
	         saxParser.parse(file, userhandler);
	         Graph graph = userhandler.graph;
	         
	         osmServerTest.setText("Start Writing");
	         GraphWriter writer= new GraphWriter(dbPathField.getText());
	         writer.storeGraph(graph,dbMergeBox.isSelected());
	         osmServerTest.setText("Done");
		} catch (Exception e) {
			// TODO: handle exception
		}
		

	
		
	}
	
	
	
	private void serverStatusWriter() {
		@SuppressWarnings("unused")
		int selected=serverList.getSelectedIndex();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == dbPathButton) {
			browseButtonClicked();
		}
		if (e.getSource() == serverList) {
			serverStatusWriter();
		}
		if (e.getSource() == cancel) {
			System.exit(0);
		}
		if (e.getSource() == start) {
			boolean test = fieldsChecker();
			if (test == false) {
				osmServerTest.setEnabled(true);
				osmServerTest.setText("Start");
				startButtonClicked();
				
			}
		}

	}


	
}
