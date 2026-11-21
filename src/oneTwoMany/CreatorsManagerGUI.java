package oneTwoMany;
import java.io.*;
import java.util.ArrayList;
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
	
	private JLabel statusLabel;
}
