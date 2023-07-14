package com.chegg.sahara.service;

import com.chegg.sahara.model.VideoDetails;
import com.chegg.sahara.model.VideoRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class VideoService {

    @Autowired
    private ObjectMapper objectMapper;
    public List<VideoDetails> getRelatedVideos(VideoRequest videoRequest){

        BufferedReader reader;
        List<VideoDetails> videoList = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader("csv/videos.jsonl"));
            String line = reader.readLine();

            while (line != null) {
                VideoDetails videoDetails = objectMapper.readValue(line, VideoDetails.class);
                if(videoDetails.getMood().equals(videoRequest.getMood())){
                    videoList.add(objectMapper.readValue(line, VideoDetails.class));
                }
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(videoList);
        return videoList;
    }

}
