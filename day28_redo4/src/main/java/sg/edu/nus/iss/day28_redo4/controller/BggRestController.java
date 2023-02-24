package sg.edu.nus.iss.day28_redo4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import sg.edu.nus.iss.day28_redo4.models.Bgg;
import sg.edu.nus.iss.day28_redo4.service.BggService;

@RestController
@RequestMapping(path = "/game")
public class BggRestController {

    @Autowired
    private BggService service;
    
    @GetMapping(path = "/{gameId}/reviews")
    public ResponseEntity<String> getReviews(@PathVariable("gameId") Integer gameId){
        
        Bgg bgg = service.getReviews(gameId);
        System.out.println(">>>>>>" + bgg);
        JsonObjectBuilder builder = Json.createObjectBuilder();
        JsonObject bld = builder.add("review", bgg.toJson()).build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(bld.toString());
    }

    
}
