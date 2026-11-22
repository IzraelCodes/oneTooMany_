package oneTwoMany;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.*;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class CreatorsManagerGUI extends JFrame implements ActionListener{
	
	private JList<Creators> creatorListDisplay;
	private JList<Video> videoListDisplay;
	
	private JTextField creatorNameField;
	private JTextField creatorIdField;
	
	private JTextField videoIdField;
	private JTextField videoTitleField;
	private JTextField viewsField;
	
	private JButton addCreatorButton;
	private JButton addVideoButton;
	
	private JLabel createrCountLabel;
	private JLabel videoCountLabel;
	private JLabel statusLabel;
	
	private ArrayList<Creators> creatorList;
	
	public CreatorsManagerGUI() {
		super("CreatorHub - Youtube Manager");		
		
		setSize(900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout(15,15));
		
		creatorList = new ArrayList<>();
		
		// background time
		getContentPane().setBackground(new Color(240, 243, 248));
		
		//All panels here!
		
		JPanel headerPanel = createHeader();
		JPanel statsPanel = createStats();
		JPanel creatorPanel = createCreatorPanel();
		JPanel videoPanel = createVideoPanel();
		JPanel footerPanel = createFooter();
		
		// Top section
		
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.add(headerPanel, BorderLayout.NORTH);
		topPanel.add(statsPanel, BorderLayout.CENTER);
		
		//Centre section of the UI
		
		JPanel centerPanel = new JPanel(new GridLayout(1,2,15,15));
		centerPanel.setBackground(new Color(240,243,248));
		centerPanel.setBorder(BorderFactory.createEmptyBorder(10,15,10,15));
		
		centerPanel.add(creatorPanel);
		centerPanel.add(videoPanel);
		
		
		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(footerPanel, BorderLayout.SOUTH);
		
		//Function to load creators
		// Function to Refresh the Creator List
		// Function to refresh the stats (Video deets)
		
		setVisible(true);
		
	}
}
