/**
 * Represents a YouTube video belonging to a specific creator.
 *
 * Stores the video's ID, title, view count and a reference
 * to its associated creator.
 *
 * Supports serialization to allow video data to be saved
 * and restored.
 */
package oneTwoMany;
import java.io.*;
public class Video implements Serializable{
	private int videoId;
	private String videoTitle;
	private int views;
	private Creators creator;
	
	/**
	 * Updates the number of views recorded for this video.
	 *
	 * @param views the updated view count
	 * @throws InvalidCreatorDataException if the view count
	 *         is negative
	 */
	
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
	public void setViews(int views)throws InvalidCreatorDataException {
		if(views < 0) {
			throw new InvalidCreatorDataException("Can't have negative View count");
		}
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
