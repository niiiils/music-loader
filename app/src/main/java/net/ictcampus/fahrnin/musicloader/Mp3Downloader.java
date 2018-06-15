package net.ictcampus.fahrnin.musicloader;

import android.os.Environment;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.Scanner;

public class Mp3Downloader {

	
	private static BufferedOutputStream outStream;
	
	private static URLConnection conn;
	private static InputStream is;
	
	public static void downloadAsMp3(String videoId){
		
		String videoLink = "https://www.youtube.com/watch?v=" + videoId;
		String requestURL = "https://you-link.herokuapp.com/?url="+videoLink;
		
		try {
			URL wikiRequest = new URL(requestURL);
			URLConnection connection = wikiRequest.openConnection();  
			connection.setDoOutput(true);  
			
			Scanner scanner = new Scanner(wikiRequest.openStream());
			String response = scanner.useDelimiter("\\Z").next();
			scanner.close();

			System.out.print("RESPONSE "+response);

			String videoPath = JSONParser.parseURL(response);
			
			System.out.println("URL "+videoPath);
			
	        URL url;
	        byte[] buf;
	        int byteRead, byteWritten = 0;
	        url = new URL(videoPath);
	        String localFileName = "download.mp4";

	        outStream = new BufferedOutputStream(new FileOutputStream(new File(Environment.getExternalStorageDirectory().toString() + "/" + localFileName)));

	        conn = url.openConnection();
	        is = conn.getInputStream();
	        buf = new byte[100000000];
	        while ((byteRead = is.read(buf)) != -1) {
	            outStream.write(buf, 0, byteRead);
	            byteWritten += byteRead;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            is.close();
	            outStream.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    System.out.print("Downloaded Video");

        try {
        	System.out.print("Starting to convert Video");
			System.out.print("File found: "+new File(Environment.getExternalStorageDirectory().toString() + "/download.mp4").exists());
            MediaConverter.mp4ToMp3(new File(Environment.getExternalStorageDirectory().toString() + "/download.mp4"));
            System.out.print("Sucessfuly converted video into audio");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
	
	public static String getFinalLocation(String address) throws IOException{
	    URL url = new URL(address);
	    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	    int status = conn.getResponseCode();
	    if (status != HttpURLConnection.HTTP_OK) 
	    {
	        if (status == HttpURLConnection.HTTP_MOVED_TEMP
	            || status == HttpURLConnection.HTTP_MOVED_PERM
	            || status == HttpURLConnection.HTTP_SEE_OTHER)
	        {
	            String newLocation = conn.getHeaderField("Location");
	            return getFinalLocation(newLocation);
	        }
	    }
	    return address;
	}
}
