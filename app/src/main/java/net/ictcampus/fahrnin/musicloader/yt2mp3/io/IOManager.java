package net.ictcampus.fahrnin.musicloader.yt2mp3.io;

/**
 * The IOManager is a basis of all input/output management. It is easily
 * implemented to create new I/O managers.
 * 
 * @author Ramisme
 * @since Mar 15, 2013
 */
public interface IOManager {

	/**
	 * Setting up the streams includes creating reading and writing input
	 * objects or connection to a URL. The user may also call any other methods
	 * which should be included when beginning the reading of input output data.
	 */
	public void setupReadStreams();

	/**
	 * Setting up the streams includes creating reading and writing input
	 * objects or connection to a URL. The user may also call any other methods
	 * which should be included when beginning the writing of output data.
	 */
	public void setupWriteStreams();

	/**
	 * Closing the streams includes closing any already open streams as well as
	 * any methods that should always be called when ending the reading/writing
	 * of input or output data.
	 */
	public void closeStreams();
}
