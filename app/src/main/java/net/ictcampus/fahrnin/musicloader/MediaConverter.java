package net.ictcampus.fahrnin.musicloader;

import net.ictcampus.fahrnin.musicloader.org.aioobe.cloudconvert.CloudConvertService;
import net.ictcampus.fahrnin.musicloader.org.aioobe.cloudconvert.ConvertProcess;
import net.ictcampus.fahrnin.musicloader.org.aioobe.cloudconvert.ProcessStatus;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

public class MediaConverter {
	private static final String API_KEY = "zpBjje6iVHRLmbWev2Ub6h0cUDnvpREKYUmdSQBfmMJMbJJZsH21TwLbSvfSFnE8";
	
	public static void mp4ToMp3(final File mp4) throws URISyntaxException, IOException, ParseException, InterruptedException {
		// Create service object
		CloudConvertService service = new CloudConvertService(API_KEY);

		// Create conversion process
		ConvertProcess process = service.startProcess("mp4", "mp3");

		// Perform conversion
		process.startConversion(mp4);

		// Wait for result
		ProcessStatus status;
		waitLoop:
		while (true) {
			status = process.getStatus();

			switch (status.step) {
				case FINISHED:
					break waitLoop;
				case ERROR:
					throw new RuntimeException(status.message);
			}

			// Be gentle
			Thread.sleep(200);
		}

		// Download result
		service.download(status.output.url, new File("output.mp3"));

		// Clean up
		process.delete();


	}
}
