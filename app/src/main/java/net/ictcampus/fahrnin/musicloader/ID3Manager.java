package net.ictcampus.fahrnin.musicloader;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

public class ID3Manager {

    private static ID3v1Tag tag;
    private static Mp3File file;

    private static boolean isBound;


    /**
     * Bind an MP3File to write tags to it
     *
     * @param f - the File to bind in order to write tags to it
     *
     */
    public static void bind(Mp3File f){

        file = f;

        if (file.hasId3v2Tag()) {

            tag = (ID3v1Tag) file.getId3v1Tag();

        }else {

            tag = new ID3v1Tag();
            file.setId3v1Tag(tag);

        }

        isBound = true;
    }

    /**
     * Sets the stored image file and tag to a null value
     *
     * isBound() can be called to check whether there is a file being bound
     */
    public static void unbind(){

        tag = null;
        file = null;

        isBound = false;
    }

    public static void setTitle(String title){
        tag.setTitle(title);
    }

    public static void setArtist(String artist){
        tag.setArtist(artist);
    }

    public static void setAlbum(String album){
        tag.setAlbum(album);
    }

    public static void setYear(String year){
        tag.setYear(year);
    }

    public static void setGenre(int genreId){
        tag.setGenre(genreId);
    }

    public static void setComment(String comment){
        tag.setComment(comment);
    }

    public static void write(String filename){
        try {
            file.save(filename + ".mp3");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotSupportedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the album art to the given Bitmap
     *
     * @param bmp the image you want the cover to be
     */
    /*public static void setCover(Bitmap bmp) {

        int size = bmp.getRowBytes() * bmp.getHeight();
        ByteBuffer b = ByteBuffer.allocate(size);

        bmp.copyPixelsToBuffer(b);

        byte[] bytes = new byte[size];

        try {
            b.get(bytes, 0, bytes.length);
        } catch (BufferUnderflowException e) {
            // always happens
        }

        tag.(bytes, "image/bmp");
    }*/

    public static boolean isBound(){
        return isBound;
    }
}

