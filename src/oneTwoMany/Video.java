package oneTwoMany;
import java.io.*;
public class Video implements Serializable{
	private int videoId;
	private String videoTitle;
	private int views;
	private Creators creator;
	
	public Video(int videoId, String videoTitle, int views, Creators creator) throws InvalidCreatorDataException {
		
		if(views < 0) {
			throw new InvalidCreatorDataException("Views cant be a negative number");
		}
		this.videoId = videoId;
		this.videoTitle = videoTitle;
		this.views = views;
		this.creator = creator;
	}
	public int getVideoId() {
		return videoId;
	}
	public String getVideoTitle() {
		return videoTitle;
	}
	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	
	public Creators getCreator() {
		return creator;
	}

	
	@Override
	public String toString() {
		return "<html>" +
		           "<b>" + videoTitle + "</b><br>" +
		           "<font color='gray'>Video ID: " + videoId + "</font><br>" +
		           "Creator: " + creator.getName() + "<br>" +
		           "Views: " +  NumberFormatter.formatNumber(views) +
		           "</html>";
	}
}
