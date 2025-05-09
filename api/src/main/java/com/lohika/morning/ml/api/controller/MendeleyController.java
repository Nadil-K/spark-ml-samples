package com.lohika.morning.ml.api.controller;

import com.lohika.morning.ml.api.service.MendeleyService;
import com.lohika.morning.ml.spark.driver.service.lyrics.GenrePrediction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;
import java.util.Map;

@Controller
@RequestMapping("/mendeley")
public class MendeleyController {

    @Autowired
    private MendeleyService mendeleyService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "forward:/index.html";
    }

    @RequestMapping(value = "/train", method = RequestMethod.GET)
    ResponseEntity<Map<String, Object>> trainMendeleyClassificationModel() {
        Map<String, Object> modelStatistics = mendeleyService.classifyLyrics();
        return new ResponseEntity<>(modelStatistics, HttpStatus.OK);
    }

    @RequestMapping(value = "/predict", method = RequestMethod.POST)
    ResponseEntity<GenrePrediction> predictGenre(@RequestBody String lyrics) {
        GenrePrediction prediction = mendeleyService.predictGenre(lyrics);
        return new ResponseEntity<>(prediction, HttpStatus.OK);
    }
}
