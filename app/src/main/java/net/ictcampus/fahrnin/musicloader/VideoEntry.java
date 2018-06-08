package net.ictcampus.fahrnin.musicloader;

public class VideoEntry {

    private int image;
    private String title;
    private int views;
    private int duration;

    public VideoEntry(int image, String title, int views, int duration){
        this.image = image;
        this.title = title;
        this.views = views;
        this.duration = duration;
    }

    public String getTitle(){
        return this.title;
    }

    public int getViews() {
        return this.views;
    }

    public int getDuration(){
        return this.duration;
    }

    public int getImage(){
        return this.image;
    }
}
