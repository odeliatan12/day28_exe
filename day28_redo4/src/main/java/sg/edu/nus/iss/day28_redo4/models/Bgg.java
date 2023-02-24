package sg.edu.nus.iss.day28_redo4.models;

import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;

public class Bgg {
    
    private String gameId;
    private String name;
    private Integer year;
    private Integer rank;
    private Integer average;
    private Integer usersRated;
    private String url;
    private String thumbnail;
    private static List<Reviews> rev = new LinkedList<>();
    private String timestamp;
    public String getGameId() {
        return gameId;
    }
    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }
    public Integer getRank() {
        return rank;
    }
    public void setRank(Integer rank) {
        this.rank = rank;
    }
    public Integer getAverage() {
        return average;
    }
    public void setAverage(Integer average) {
        this.average = average;
    }
    public Integer getUsersRated() {
        return usersRated;
    }
    public void setUsersRated(Integer usersRated) {
        this.usersRated = usersRated;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getThumbnail() {
        return thumbnail;
    }
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    public List<Reviews> getRev() {
        return rev;
    }
    public void setRev(List<Reviews> rev) {
        this.rev = rev;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public JsonObject toJson(){
        JsonArray reviews = null;
        JsonArrayBuilder build = Json.createArrayBuilder();
        for( Reviews x : getRev())
            build.add((JsonValue) x);
        
        reviews = build.build();
        return Json.createObjectBuilder()
                    .add("game_id", getGameId())
                    .add("name", getName())
                    .add("year", getYear())
                    .add("rank", getRank())
                    .add("users_rated", getUsersRated())
                    .add("url", getUrl())
                    .add("thumbnail", getThumbnail())
                    .add("reviews", reviews.toString())
                    .build();
    }

    
}
