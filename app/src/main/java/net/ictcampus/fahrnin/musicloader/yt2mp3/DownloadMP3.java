package net.ictcampus.fahrnin.musicloader.yt2mp3;

import java.util.concurrent.Executors;

/**
 * Sets up the data used to submit the download thread for parsing & downloading.
 * 
 * @see info.lynxaa.yt2mp3#DownloadMP3Thread
 * 
 * @author Lynxaa
 * @since Feb 12, 2014 3:21:22 PM
 */
public class DownloadMP3 {
	private String videoID;

	public static DownloadMP3 downloadMP3(final String videoID, final String savePath) {
		return new DownloadMP3(videoID, savePath);
	}

	private DownloadMP3(final String videoID, final String savePath) {
		this.setVideoID(videoID);

		Executors.newSingleThreadExecutor().execute(new DownloadMP3Thread(videoID, savePath));
	}

	public String getVideoID() {
		return videoID;
	}

	public void setVideoID(String videoID) {
		this.videoID = videoID;
	}
}
