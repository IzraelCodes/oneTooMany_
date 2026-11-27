/**
 * Custom exception used to reject invalid creator
 * and video data within the CreatorHub application.
 */
package oneTwoMany;

public class InvalidCreatorDataException extends Exception {

		public InvalidCreatorDataException(String message) {
			super(message);
		}
}
