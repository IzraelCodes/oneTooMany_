package oneTwoMany;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.*;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	
	private JLabel creatorCountLabel;
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
		
	} //dashboard header part
	
	private JPanel createHeader() {
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(new Color(32,43,60));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20,25));
		
		JLabel titleLabel = new JLabel("CreatorHub");
		
		titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
		titleLabel.setForeground(Color.white);
		
		JLabel subtitleLabel = new JLabel(
				"YOUTUBE CREATOR MANAGMENT");
		
		subtitleLabel.setFont(new Font("Arial", Font.PLAIN , 11));
		subtitleLabel.setForeground(new Color(32, 43, 60));
		
		JPanel textPanel = new JPanel(new GridLayout(2, 1));
		textPanel.setBackground(new Color(32, 43, 60));
		
		textPanel.add(titleLabel);
		textPanel.add(subtitleLabel);
		
		return panel;
		
		
	}
	
	// creation of the STATS sections
	
	private JPanel createStats() {
		
		JPanel panel = new JPanel(new GridLayout(1,2,15,0));
		
		panel.setBackground(new Color(240,243,248));
		panel.setBorder(BorderFactory.createEmptyBorder(15,15,5,15));
		
		creatorCountLabel = new JLabel("0");
		videoCountLabel = new JLabel("0");
		
		JPanel creatorCard = createStatCard(
				"TOTAL CREATORS", creatorCountLabel);
		
		JPanel videoCard = createStatCard(
				"Total Videos", videoCountLabel);
		
		panel.add(creatorCard);
		panel.add(videoCard);
		
		return panel;
		
		
	}
	
	private JPanel createStatCard(String heading, JLabel numberLabel) {
		
		JPanel panel = new JPanel(new GridLayout(2,1));
		
		panel.setBackground(Color.white);
		panel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(220, 225,233)),
				BorderFactory.createEmptyBorder(10,15,10,15)));
		
		 JLabel headingLabel = new JLabel(heading);
	        headingLabel.setFont(new Font("Arial", Font.BOLD, 12));
	        headingLabel.setForeground(Color.GRAY);

	        numberLabel.setFont(new Font("Arial", Font.BOLD, 25));
	        numberLabel.setForeground(new Color(32, 43, 60));

	        panel.add(headingLabel);
	        panel.add(numberLabel);

	        return panel;
	    

		
	}
	
}
