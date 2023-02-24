package sg.edu.nus.iss.day28_redo4;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import sg.edu.nus.iss.day28_redo4.models.Bgg;
import sg.edu.nus.iss.day28_redo4.models.Reviews;

public class Utils {

    public static Bgg createBgg(Document doc){
        Bgg bgg = new Bgg();
        List<Reviews> reviews = (List<Reviews>) doc.get("reviews");
        List reviewList = new LinkedList<>();
        for(Object a : reviews){
            ObjectId oa = (ObjectId)a;
            reviewList.add("/reviews/" + oa);
        }

        bgg.setGameId(doc.getString("game_id"));
        bgg.setName(doc.getString("name"));
        bgg.setYear(doc.getInteger("year"));
        bgg.setAverage(doc.getInteger("average"));
        bgg.setUsersRated(doc.getInteger("users_rated"));
        bgg.setUrl(doc.getString("url"));
        bgg.setThumbnail(doc.getString("thumbnail"));
        bgg.setRev(reviewList);
        bgg.setTimestamp(doc.getString("timestamp"));
        return bgg;
    }
    
}
