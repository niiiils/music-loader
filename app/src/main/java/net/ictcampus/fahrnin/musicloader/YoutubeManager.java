package net.ictcampus.fahrnin.musicloader;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class YoutubeManager{

    private static final String API_KEY = "AIzaSyA9l8Ifs6WLlKsQI6HdSz1lXSBg5XOUdH4";

    public YoutubeManager(){

    }

    public List<SearchResult> searchByString(String searchString, long maxResults) {

        YouTube youtube;

        try {
            youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("MusicLoader").build();

            YouTube.Search.List search = youtube.search().list("id,snippet");

            search.setKey(API_KEY);
            search.setQ(searchString);

            search.setType("video");

            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults(maxResults);

            SearchListResponse searchResponse = search.execute();
            List<SearchResult> searchResultList = searchResponse.getItems();

            return searchResultList;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}