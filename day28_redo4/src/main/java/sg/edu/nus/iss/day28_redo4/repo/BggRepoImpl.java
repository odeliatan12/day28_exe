package sg.edu.nus.iss.day28_redo4.repo;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import static sg.edu.nus.iss.day28_redo4.Constants.*;

@Repository
public class BggRepoImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    /*
     * db.games.aggregate(
        [
            {
                $match: {
                    gid : 8,
                }
            },
            {
                $project: {
                    _id: 0,
                    game_id : "$gid",
                    name : 1,
                    year : 1,
                    ranking : "$ranking",
                    users_rated : 1,
                    url : 1,
                    thumbnail : "$image",
                    
                }
            },
            {
                $lookup: {
                    from: "review",
                    foreignField: "gameId",
                    localField: "gid",
                    as: "reviews"
                }
            }
        ])
     */
    public AggregationResults<Document> getReviews(Integer gameId){
        MatchOperation matchRated = Aggregation.match(
            Criteria.where(FIELD_GID).is(gameId)
        );

        ProjectionOperation projectFields = Aggregation.project(
            FIELD_NAME, FIELD_YEAR, FIELD_RANKING, FIELD_USERSRATED,
            FIELD_URL
        )
        .and(FIELD_GID).as(FIELD_GAMEID)
        .and(FIELD_IMAGE).as(FIELD_THUMBNAIL)
        .andExclude(FIELD_ID);

        LookupOperation lookupComments = Aggregation.lookup(COLLECTION_REVIEW, 
        FIELD_GID, FIELD_GAMEID, COLLECTION_REVIEW);

        Aggregation pipeline = Aggregation.newAggregation(matchRated, projectFields, 
        lookupComments);

        return mongoTemplate.aggregate(pipeline, COLLECTION_GAMES, Document.class);
    }

    
    
}
