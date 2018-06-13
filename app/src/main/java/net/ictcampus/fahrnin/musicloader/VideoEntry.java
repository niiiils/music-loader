package net.ictcampus.fahrnin.musicloader;

public class VideoEntry {
    private String videoID;
    private String thumbnail;
    private String title;
    private int views;
    private int duration;

    public VideoEntry(String videoID,String thumbnail, String title, int views, int duration){
        this.thumbnail = thumbnail;
        this.title = title;
        this.views = views;
        this.duration = duration;
        this.videoID = videoID;
    }

    public String getVideoID() { return this.videoID; }

    public String getTitle(){
        return this.title;
    }

    public int getViews() {
        return this.views;
    }

    public int getDuration(){
        return this.duration;
    }

    public String getThumbnail(){
        return this.thumbnail;
    }
}
