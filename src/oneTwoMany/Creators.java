package oneTwoMany;
import java.io.Serializable;
import java.util.ArrayList;
public class Creators implements Serializable {

	private int creatorId;
	private String name;
	private int Subscribers;
	private ArrayList<Video> videos;
	
	public Creators(int creatorId, String name, int Subscribers) {
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
	public int getSubscribers() {
		return Subscribers;
	}
	public ArrayList<Video> getVideos(){
		return videos;
	}
	
	public void addVideos(Video video) {
		videos.add(video);
	}
	
	@Override
	public String toString() {
		return "Creator ID: " + creatorId +
				"\nCreator name: " + name +
				"\n Videos: " + videos.size();
	}

	

}
