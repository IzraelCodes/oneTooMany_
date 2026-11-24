package oneTwoMany;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class CreatorsManagerGUI extends JFrame implements ActionListener {
	
	private JList<Creators> creatorListDisplay;
	private JList<Video> videoListDisplay;
	
	private JTextField creatorNameField; //WOI (WOULD HAVE INTEGRATED BUT CONTEMPLATING)
	private JTextField creatorIdField; //WOI
	
	private JTextField videoIdField; //WOI
	private JTextField videoTitleField; //WOI
	private JTextField viewsField; //WOI 
	
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
		
		//Center section of the UI
		
		JPanel centerPanel = new JPanel(new GridLayout(1,2,15,15));
		centerPanel.setBackground(new Color(240,243,248));
		centerPanel.setBorder(BorderFactory.createEmptyBorder(10,15,10,15));
		
		centerPanel.add(creatorPanel);
		centerPanel.add(videoPanel);
		
		
		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(footerPanel, BorderLayout.SOUTH);
		
		
		loadCreators();
		
		refreshCreatorList();
		refreshStats();
		
		setVisible(true);
		
	} //dashboard header part
	
	private JPanel createHeader() {
		
		JPanel panel = new RoundedPanel(25, new BorderLayout());
		panel.setBackground(new Color(32,43,60));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20,25));
		
		JLabel titleLabel = new JLabel("CreatorHub");
		
		titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
		titleLabel.setForeground(Color.white);
		
		JLabel subtitleLabel = new JLabel(
				"YOUTUBE CREATOR MANAGMENT");
		
		subtitleLabel.setFont(new Font("Arial", Font.PLAIN , 11));
		subtitleLabel.setForeground(Color.LIGHT_GRAY);
		
		JPanel textPanel = new JPanel(new GridLayout(2, 1));
		textPanel.setBackground(new Color(32, 43, 60));
		
		textPanel.add(titleLabel);
		textPanel.add(subtitleLabel);
		
		panel.add(textPanel, BorderLayout.WEST);
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
		
		JPanel panel = new RoundedPanel(25, new GridLayout(2, 1));
		
		panel.setBackground(Color.white);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
				
		
		 JLabel headingLabel = new JLabel(heading);
	        headingLabel.setFont(new Font("Arial", Font.BOLD, 12));
	        headingLabel.setForeground(Color.GRAY);

	        numberLabel.setFont(new Font("Arial", Font.BOLD, 25));
	        numberLabel.setForeground(new Color(32, 43, 60));

	        panel.add(headingLabel);
	        panel.add(numberLabel);

	        return panel;
	    

		
	}
	
	private JPanel createVideoPanel() {
		
		JPanel panel = new RoundedPanel(25);
		panel.setLayout(new BorderLayout(5, 10));
		
		panel.setBackground(Color.white);
		panel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
		
		
		JLabel heading =  new JLabel("Selected Creator's Videos");
		heading.setFont(new Font("Arial", Font.BOLD,18));
		
		videoListDisplay = new JList<>(); 
		videoListDisplay.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JScrollPane scrollPane = new JScrollPane(videoListDisplay);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		
		panel.add(heading, BorderLayout.NORTH);
		panel.add(scrollPane, BorderLayout.CENTER);
		
		return panel;
	}
	
	//library for creators
	private JPanel createCreatorPanel() {
		JPanel panel = new RoundedPanel(25, new BorderLayout(5, 10));
		
		panel.setBackground(Color.white);
		panel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
				
		JLabel heading = new JLabel("Creator Library");
		heading.setFont(new Font("Arial", Font.BOLD, 18));
		
		creatorListDisplay = new JList<>();
		creatorListDisplay.setFont(new Font("Arial", Font.PLAIN,14));
		creatorListDisplay.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //YOUTUBE THANK YOU 
		
		JScrollPane scrollPane = new JScrollPane(creatorListDisplay);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		
		creatorListDisplay.addListSelectionListener(e -> {
			if(!e.getValueIsAdjusting()) {
				Creators selected = creatorListDisplay.getSelectedValue();
				
				if(selected != null) {
					showVideos(selected);
				}else {
					videoListDisplay.setListData(new Video[0]);
				}
			} 
		});
		
		panel.add(heading, BorderLayout.NORTH);
		panel.add(scrollPane,BorderLayout.CENTER);
		
		return panel;
		
	}
	
	private JPanel createFooter() {
		
		JPanel panel = new RoundedPanel(15, new BorderLayout());
		
		panel.setBackground(new Color(240, 243, 248));
		panel.setBorder(BorderFactory.createEmptyBorder(5,15,15,15));
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		buttonPanel.setBackground(new Color(240,243,248));
		
		addCreatorButton = new JButton("Add Creator");
		addVideoButton = new JButton("Add Video");
		
		addCreatorButton.addActionListener(this);
		addVideoButton.addActionListener(this);
		
		buttonPanel.add(addCreatorButton);
		buttonPanel.add(addVideoButton);
		
		statusLabel = new JLabel("Welcome to CreatorHub");
		statusLabel.setForeground(new Color(70, 90,110));
		
		panel.add(buttonPanel, BorderLayout.WEST);
		panel.add(statusLabel, BorderLayout.SOUTH);
		
		return panel;
		
	}
	
	//Refresh Creator list
	
	private void refreshCreatorList() {
		creatorListDisplay.setListData(creatorList.toArray(new Creators[0]));
		
	}
	
	//Display selected creators videos.
	private void showVideos(Creators creator) {
		
		videoListDisplay.setListData(creator.getVideos().toArray(new Video[0]));
	}
	private void refreshStats() {
		
		int totalVideos = 0;
		
		for(Creators creator : creatorList) {
			totalVideos += creator.getVideos().size();
		}
		
		creatorCountLabel.setText(String.valueOf(creatorList.size()));
		videoCountLabel.setText(String.valueOf(totalVideos));
	}
	private void sampleData() { //Place HOLDER for now
		
	}
	
	//Load creator from files
	
	private void loadCreators() {
		 try (FileInputStream fis =
                 new FileInputStream("creators.dat");

             ObjectInputStream ois =
                 new ObjectInputStream(fis)) {

            creatorList = (ArrayList<Creators>) ois.readObject();

        } catch (FileNotFoundException ex) {
            // No saved file exists yet
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }	
		
	}
	private void saveCreators() {
		  try (FileOutputStream fos =
	                 new FileOutputStream("creators.dat");

	             ObjectOutputStream oos =
	                 new ObjectOutputStream(fos)) {

	            oos.writeObject(creatorList);

	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	}
	@Override
	//Button features and stuff
	public void actionPerformed(ActionEvent e) {
		//add creator first
		
		if(e.getSource() == addCreatorButton) {
			try {
				String idInput = JOptionPane.showInputDialog(this, "Enter Creator ID:");
				String name   = JOptionPane.showInputDialog(this, "Enter Creator Name:");
				String subscribersInput = JOptionPane.showInputDialog(this, "Enter Creators Subscriber count:");
				
				int id = Integer.parseInt(idInput.trim());
				int subscribers = Integer.parseInt(subscribersInput);
				Creators creator = new Creators(id, name.trim(), subscribers);
				creatorList.add(creator);
				refreshCreatorList();
				refreshStats();
				saveCreators();
				
				statusLabel.setText("Creator added successfully.");
				Timer timer = new Timer(3000, event -> statusLabel.setText(" "));
				timer.setRepeats(false);
				timer.start();
				
			}catch (NumberFormatException | NullPointerException ex) {
				statusLabel.setText("Creator ID must be a whole number.");
				Timer timer = new Timer(3000, event -> statusLabel.setText(" "));
				timer.setRepeats(false);
				timer.start();
			}
		} else if (e.getSource()== addVideoButton) {
			Creators selected = creatorListDisplay.getSelectedValue();
			
			if (selected == null) {
				statusLabel.setText("Select a creator first please");
			}else {
				try {
					String idInput = JOptionPane.showInputDialog(this,"Enter Video ID:");
					String title = JOptionPane.showInputDialog(this,"Enter Video Title:");
					String viewsInput = JOptionPane.showInputDialog(this,"Enter Number of views:");
					
					int id = Integer.parseInt(idInput.trim());
					int views = Integer.parseInt(viewsInput.trim());
					
					Video video = new Video(id, title, views, selected);
					selected.addVideos(video);
					showVideos(selected);
					refreshStats();
					saveCreators();
					statusLabel.setText("Video added successfully.");
					
					Timer timer = new Timer(3000, event -> statusLabel.setText(" "));
					timer.setRepeats(false);
					timer.start();
					
				} catch(NumberFormatException | NullPointerException ex) {
					statusLabel.setText("Video ID and views must be whole numbers!");
				Timer timer = new Timer(3000, event -> statusLabel.setText(" "));
				timer.setRepeats(false);
				timer.start();
				}
			}
		
		}
		
	}
	public static void main(String[] args) {
		new CreatorsManagerGUI();
	
	}
}
