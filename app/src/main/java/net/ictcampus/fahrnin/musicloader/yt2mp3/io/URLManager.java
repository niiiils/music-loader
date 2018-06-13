package net.ictcampus.fahrnin.musicloader.yt2mp3.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class URLManager implements IOManager {
	private volatile HttpURLConnection connection;
	private volatile BufferedReader bufferedReader;
	private final URL url;
	private volatile Proxy proxy;

	private volatile int timeout = 10000;

	/**
	 * Returns a new instance of the private URLManager object.
	 * 
	 * @param url
	 * @return
	 */
	public static URLManager newManager(final URL url) {
		return new URLManager(url);
	}

	/**
	 * The private URLManager class is an implementation of the IOManager
	 * interface and is specifically used for the sole purpose of reading lines
	 * from a specified URL.
	 * 
	 * @param url
	 */
	protected URLManager(final URL url) {
		this.url = url;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ramisme.lib.io.IOManager#setupStreams()
	 */
	@Override
	public void setupReadStreams() {
		try {
			if (this.proxy != null) {
				connection = (HttpURLConnection) this.url.openConnection(proxy);
				connection.setConnectTimeout(this.getTimeout());
				connection.connect();
				bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				return;
			}


			connection = (HttpURLConnection) url.openConnection();
			connection.addRequestProperty("User-Agent", "Mozilla/4.76");
			bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		} catch (final IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * URLs cannot be written to.
	 * 
	 * @see org.ramisme.lib.io.IOManager#setupWriteStreams()
	 */
	@Override
	public void setupWriteStreams() {
		return;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ramisme.lib.io.IOManager#closeStreams()
	 */
	@Override
	public void closeStreams() {
		if (bufferedReader != null) {
			try {
				bufferedReader.close();
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}

		if (connection != null) {
			try {
				connection.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Return a string read from the specified URL.
	 * 
	 * @return
	 * @throws IOException
	 */
	public String readLine() throws IOException {
		return bufferedReader.readLine();
	}

	/**
	 * Get all results on the website very quickly and with ease.
	 * 
	 * @return
	 * 		<i>An array of strings that the webserver returned.</i>
	 * 
	 * @throws IOException
	 */
	public List<String> getResults() throws IOException {
		String line;
		List<String> results = new ArrayList<String>();
		results.clear();

		while ((line = readLine()) != null) {
			results.add(line);
			line = readLine();
		}

		return results;
	}

	/**
	 * Save's the contents of a url to a file.
	 * 
	 * @param filename
	 * @param urlString
	 * @throws MalformedURLException
	 * @throws IOException
	 * 
	 * @author Lynxaa
	 */
	public void saveUrl(String filename, String urlString) throws MalformedURLException, IOException {		
		BufferedInputStream in = null;
		FileOutputStream fout = null;
		try {
			in = new BufferedInputStream(new URL(urlString).openStream());
			fout = new FileOutputStream(filename);

			byte data[] = new byte[1024];
			int count;
			while ((count = in.read(data, 0, 1024)) != -1) {
				fout.write(data, 0, count);
			}
			
			System.out.println(String.format("Downloaded %s successfully.", new Object[] { filename }));
		} finally {
			if (in != null) in.close();
			if (fout != null) fout.close();
		}
	}

	/**
	 * Return the current HttpURLConnection instance.
	 * 
	 * @return
	 */
	public HttpURLConnection getConnection() {
		return this.connection;
	}

	/**
	 * Set the current proxy object.
	 * 
	 * @param proxy
	 */
	public void setProxy(final Proxy proxy) {
		this.proxy = proxy;
	}

	/**
	 * Return the current proxy object.
	 * 
	 * @return
	 */
	public Proxy getProxy() {
		return this.proxy;
	}

	/**
	 * Set the current timeout variable.
	 * 
	 * @param timeout
	 */
	public void setTimeout(final int timeout) {
		this.timeout = timeout;
	}

	/**
	 * Return the current timeout.
	 * 
	 * @return
	 */
	public int getTimeout() {
		return this.timeout;
	}

}
