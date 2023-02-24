package sg.edu.nus.iss.day28_redo4.service;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.day28_redo4.Utils;
import sg.edu.nus.iss.day28_redo4.models.Bgg;
import sg.edu.nus.iss.day28_redo4.repo.BggRepoImpl;

@Service
public class BggService {

    @Autowired
    private BggRepoImpl bggRepo;

    public <Optional>Bgg getReviews(Integer gameId){
        AggregationResults<Document> results = bggRepo.getReviews(gameId);

        // The iterator() method returns an iterator over the results of an aggregation operation. In the context of the code you provided, it is used to retrieve the first (and only) document in the results of the aggregation operation, using the next() method of the iterator. If there are no results, calling next() on the iterator will throw a NoSuchElementException.
        Document doc = results.iterator().next();
        Bgg bgg = Utils.createBgg(doc);
        return bgg;
    }
    
}
