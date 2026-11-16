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
}
