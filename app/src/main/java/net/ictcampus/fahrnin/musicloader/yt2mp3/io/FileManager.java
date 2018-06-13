package net.ictcampus.fahrnin.musicloader.yt2mp3.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * An implementation of IOManager that is used specifically for transferring
 * data to and from a specified file.
 * 
 * @author Ramisme
 * @since Mar 15, 2013
 */
public class FileManager implements IOManager {
	protected final File file;
	protected volatile BufferedReader bufferedReader;
	protected volatile BufferedWriter bufferedWriter;

	public static FileManager newManager(final File file) {
		return new FileManager(file);
	}

	private FileManager(final File file) {
		this.file = file;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ramisme.lib.io.IOManager#setupStreams()
	 */
	@Override
	public void setupReadStreams() {
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ramisme.lib.io.IOManager#setupWriteStreams()
	 */
	@Override
	public void setupWriteStreams() {
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(file));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ramisme.lib.io.IOManager#closeStreams()
	 */
	@Override
	public void closeStreams() {
		try {
			if (bufferedReader != null) {
				bufferedReader.close();
			}

			if (bufferedWriter != null) {
				bufferedWriter.close();
			}
		} catch (final IOException e1) {
			e1.printStackTrace();
		}

	}

	/**
	 * @return One input line from the specified file
	 */
	public String readLine() {
		try {
			return bufferedReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Write a specified line, and an empty line after it.
	 * 
	 * @param line
	 *            Specified <tt>String</tt> to be written to the file.
	 */
	public void writeLine(final String line) {
		try {
			bufferedWriter.write(line);
			bufferedWriter.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public File getFile() {
		return file;
	}

}
