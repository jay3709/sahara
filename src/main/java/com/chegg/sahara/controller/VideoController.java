package com.chegg.sahara.controller;

import com.chegg.sahara.model.VideoDetails;
import com.chegg.sahara.model.VideoRequest;
import com.chegg.sahara.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("getVideos")
    public List<VideoDetails> getVideos(@RequestBody VideoRequest videoRequest) {
        return videoService.getRelatedVideos(videoRequest);
    }

}
