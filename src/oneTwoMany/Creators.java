/**
 * Represents a YouTube creator and manages the collection
 * of videos associated with that creator.
 *
 * Demonstrates a one to many relationship between
 * creators and videos and supports object serialisation.
 */
 
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
	/**
	 * Adds a video to this creator's collection of videos.
	 *
	 * @param video the video to be added
	 */
	public void addVideos(Video video) {
		videos.add(video);
	}
	/**
	 * Removes a video from this creator's collection.
	 *
	 * @param video the video to be removed
	 */
	public void removeVideo(Video video) {
		videos.remove(video);
	}
	/**
	 * Calculates the combined number of views across
	 * all videos belonging to this creator.
	 *
	 * @return total number of video views
	 */
	public long getTotalViews() { //Long chosen because mr Beast has a lot of views per creator also
		long totalViews = 0;
		
		for(Video video : videos) {
			totalViews += video.getViews();
		}
		return totalViews;
	}
	/**
	 * calculates the average number of views per video
	 * belonging to this creator.
	 *
	 * @return average video views, or zero if no videos exist
	 */
	public double getAverageViews() { //per creator
		if(videos.isEmpty()) {
			return 0;
		}
		return (double) getTotalViews() / videos.size();
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
