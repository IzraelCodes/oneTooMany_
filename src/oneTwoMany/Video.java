package oneTwoMany;
import java.io.*;
public class Video implements Serializable{
	private int videoId;
	private String videoTitle;
	private int views;
	private Creators creator;
	
	public Video(int videoId, String videoTitle, int views, Creators creator) {
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
	public int getViews() {
		return views;
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
		           "Views: " + String.format("%,d", views) +
		           "</html>";
	}
}
