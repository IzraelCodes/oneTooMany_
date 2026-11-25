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

	private JButton addCreatorButton;
	private JButton addVideoButton;
	private JButton editCreatorButton;
	private JButton editVideoButton;
	private JButton deleteCreatorButton;
	private JButton deleteVideoButton;
	
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
		
		// All panels here!
		
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
		
	} // dashboard header part
	
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
		
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 15, 0));
		buttonPanel.setOpaque(false);

		
		buttonPanel.setBackground(new Color(240,243,248));
		
		addCreatorButton = new JButton("Add Creator");
		editCreatorButton = new JButton("Edit Creator");
		deleteCreatorButton = new JButton("Delete Creator");
		
		addVideoButton = new JButton("Add Video");
		editVideoButton = new JButton("Edit Video");
		deleteVideoButton = new JButton("Delete Video");
		
		addCreatorButton.addActionListener(this);
		addVideoButton.addActionListener(this);
		
		editCreatorButton.addActionListener(this);
		editVideoButton.addActionListener(this);
		deleteVideoButton.addActionListener(this);
		deleteCreatorButton.addActionListener(this);
		
		JPanel creatorButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
		creatorButtons.setOpaque(false);

		creatorButtons.add(addCreatorButton);
		creatorButtons.add(editCreatorButton);
		creatorButtons.add(deleteCreatorButton);

		JPanel videoButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		videoButtons.setOpaque(false);

		videoButtons.add(addVideoButton);
		videoButtons.add(editVideoButton);
		videoButtons.add(deleteVideoButton);
		

		buttonPanel.add(creatorButtons);
		buttonPanel.add(videoButtons);
		
		statusLabel = new JLabel("Welcome to CreatorHub");
		statusLabel.setForeground(new Color(70, 90,110));
		
		panel.add(buttonPanel, BorderLayout.CENTER);
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
	private void sampleData() { 
		
		Creators c1 = new Creators(0001,"MrBeast", 528_479_835);
		Creators c2 = new Creators(0002,"IShowspeed", 63_386_487);
		Creators c3 = new Creators(0003, "Beta Squad", 12_230_104);
		
		// Videos for MRBEAST
		
		Video v1 = new Video(1, "$250,000 Hunger Games in Real Life", 102_000_000, c1);
		Video v2 = new Video(2, "Beat Professional Athlete, Win $100,000", 105_000_000, c1);
		Video v3 = new Video(3, "Lose 150 Lbs Together, Win $250,000", 202_000_000, c1);
		
		// Videos / LiveStreams for IShowspeed
		
		Video v4 = new Video(1, "FIRST TO MAKE $1 MILLION IN GTA 6 WINS! ft.Kai Cenat", 5_000_000,c2);
		Video v5 = new Video(2, "EXPLORING VICE CITY - GTA 6 STREAM (EASTER EGGS)", 10_000_000,c2);
		Video v6 = new Video(3, "BEATING GTA 6 RIGHT NOW 100% (FINAL MISSION)", 24_000_000,c2);
		
		//Videos for Beta Squad
		
		Video v7 = new Video(1, "BETA SQUAD BAKE OFF", 4_100_000, c3);
		Video v8 = new Video(2, "We Opened a Drive-Thru", 5_700_000, c3);
		Video v9 = new Video(3, "CHEAP VS EXPENSIVE ROAD TRIP (USA EDITION)", 7_400_000,c3);
		
		//adding them 
		c1.addVideos(v1);
		c1.addVideos(v2);
		c1.addVideos(v3);
		
		c2.addVideos(v4);
		c2.addVideos(v5);
		c2.addVideos(v6);
		
		c3.addVideos(v7);
		c3.addVideos(v8);
		c3.addVideos(v9);
		
		//adds creators to main Array list
		creatorList.add(c1);
		creatorList.add(c2);
		creatorList.add(c3);
		
		refreshCreatorList();
		refreshStats();
		saveCreators();
	}
	
	
	//Load creator from files
	
	private void loadCreators() {
		 try (FileInputStream fis =
                 new FileInputStream("creators.dat");

             ObjectInputStream ois =
                 new ObjectInputStream(fis)) {

            creatorList = (ArrayList<Creators>) ois.readObject();
            
            refreshCreatorList();
            refreshStats();

        } catch (FileNotFoundException ex) {
                sampleData();
        } catch (IOException | ClassNotFoundException ex) {
            statusLabel.setText("Could not load creator data.");
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
		
		} else if(e.getSource()== editCreatorButton) {
			
			Creators selected = creatorListDisplay.getSelectedValue();
			
			if(selected != null) {
				try {
					String name = JOptionPane.showInputDialog(this, "Edit Creator Name:", selected.getName());
					
					String subscribersInput = JOptionPane.showInputDialog(this, "Edit Subscriber Count:", selected.getSubscribers());
					
					if(name == null || subscribersInput == null) {
						return;
					}
					int subscribers = Integer.parseInt(subscribersInput);
					
					if(name.isEmpty() || subscribers < 0) {
						statusLabel.setText("Invalid creator details.");
						return;
					}
					selected.setName(name);
					selected.setSubscribers(subscribers);
					
					refreshCreatorList();
					creatorListDisplay.setSelectedValue(selected, true);
					showVideos(selected);
					refreshStats();
					saveCreators();
					
					statusLabel.setText("Creator edited successfully.");
					
					Timer timer = new Timer(3000, event -> statusLabel.setText(" "));
					timer.setRepeats(false);
					timer.start();
				} catch (NumberFormatException ex) {
					statusLabel.setText("Enter a valid subscriber count.");
					
					Timer timer = new Timer(3000, event -> statusLabel.setText(" "));
					timer.setRepeats(false);
					timer.start();
				}
			}
			
		} else if(e.getSource() == editVideoButton) {
			
			Video selected = videoListDisplay.getSelectedValue();
			
			if( selected != null) {
				try {
					String title = JOptionPane.showInputDialog(this, "Edit Video Title: ", selected.getVideoTitle());
					String viewsInput = JOptionPane.showInputDialog(this, "Edit Videos Views: ", selected.getViews());
					
					if(title == null || viewsInput == null) {
						return;
					}
					int views = Integer.parseInt(viewsInput);
					
					if(title.isEmpty() || views < 0) {
						statusLabel.setText("Invalid video details");
						return;
					}
					
					selected.setVideoTitle(title);
					selected.setViews(views);
					
					showVideos(selected.getCreator());
					videoListDisplay.setSelectedValue(selected, true);
					refreshStats();
					saveCreators();
					
					statusLabel.setText("Video has been Editted successfully");
					
					Timer timer = new Timer(3000, event -> statusLabel.setText(" "));
					timer.setRepeats(false);
					timer.start();
					
				} catch(NumberFormatException ex) {
					statusLabel.setText("Enter a valid view count.");
					Timer timer = new Timer(3000, event -> statusLabel.setText(" "));
					timer.setRepeats(false);
					timer.start();
				}
			}
			
		} else if(e.getSource() == deleteCreatorButton) {
			
			Creators selected = creatorListDisplay.getSelectedValue();
			
			if(selected != null) {
				
				int confirm = JOptionPane.showConfirmDialog(this, 
						"Are you sure you want to delete " + selected.getName()
						+"?\nAll videos bt them will also be deleted.",
						"Delete Creator",
						JOptionPane.YES_NO_OPTION);
				
				if(confirm == JOptionPane.YES_OPTION) {
					creatorList.remove(selected);
					
					refreshCreatorList();
					videoListDisplay.setListData(new Video [0]);
					refreshStats();
					saveCreators();
					
					statusLabel.setText("Creator deleted successfully");
					Timer timer = new Timer(3000, event -> statusLabel.setText(" "));
					timer.setRepeats(false);
					timer.start();
					
				}
			}
		} else if(e.getSource() == deleteVideoButton) {
			
			Video selected = videoListDisplay.getSelectedValue();
			
			if (selected != null) {
				
				int confirm = JOptionPane.showConfirmDialog(this,
						"Are you sure you want to delete " +
						selected.getVideoTitle() + "?",
						"Delete Video", JOptionPane.YES_NO_OPTION);
				
				if(confirm == JOptionPane.YES_OPTION) {
					Creators creator = selected.getCreator();
					creator.removeVideo(selected);
					
					refreshCreatorList();
					creatorListDisplay.setSelectedValue(creator, true);
					showVideos(creator);
					refreshStats();
					saveCreators();
					
					statusLabel.setText("Video Deleted successfully");
					Timer timer = new Timer(3000, event -> statusLabel.setText(" "));
					timer.setRepeats(false);
					timer.start();
					
				} else {
					statusLabel.setText("Select a video first ");
				}
			}
		}
		
	}
	public static void main(String[] args) {
		new CreatorsManagerGUI();
	
	}
}
// TODO CHART WHICH IS AVG VIEW PER VIDEO OF EACH CREATOR
// Average views = Total video views / number of videos

//Custom Exception

//Maybe work on some more GUI related stuff

//EDIT and DELETE /50%

