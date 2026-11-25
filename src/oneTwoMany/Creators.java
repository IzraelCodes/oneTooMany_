package oneTwoMany;
import java.io.Serializable;
import java.util.ArrayList;
public class Creators implements Serializable {

	private int creatorId;
	private String name;
	private int Subscribers;
	private ArrayList<Video> videos;
	
	public Creators(int creatorId, String name, int Subscribers) throws InvalidCreatorDataException {
		
		if(Subscribers < 0) {
			throw new InvalidCreatorDataException("Subscriber count cannot be negative.");
		}
		this.creatorId = creatorId;
		this.name=name;
		this.Subscribers = Subscribers;
		this.videos = new ArrayList<>();
	}
	
	public int getCreatorId() {
		return creatorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getSubscribers() {
		return Subscribers;
	}
	public void setSubscribers(int Subscribers) throws InvalidCreatorDataException{
		if(Subscribers < 0) {
			throw new InvalidCreatorDataException("Subscriber count can't be negative");
		}
		this.Subscribers = Subscribers;
	}
	public ArrayList<Video> getVideos(){
		return videos;
	}
	
	// Functions
	public void addVideos(Video video) {
		videos.add(video);
	}
	
	public void removeVideo(Video video) {
		videos.remove(video);
	}
	
	@Override
	public String toString() {
		return "<html>" +
		           "<b>" + name + "</b><br>" +
		           "<font color='gray'>Creator ID: " + creatorId + "</font><br>" +
		           "Subscribers: " +  NumberFormatter.formatNumber(Subscribers) + "<br>" +
		           "Videos: " + videos.size() +
		           "</html>";
	}

	

}
