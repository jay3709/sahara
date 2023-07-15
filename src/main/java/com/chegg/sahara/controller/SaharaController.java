package com.chegg.sahara.controller;

import com.chegg.sahara.model.VideoRequest;
import com.chegg.sahara.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SaharaController {

    @Autowired
    VideoService videoService;

    @GetMapping("/")
    public String getLinks(Model model){
        model.addAttribute("name", "Steve");
        return "index";
    }

    @GetMapping("/suggested-videos/{mood}")
    public String suggestedVideos(Model model, @PathVariable("mood") String mood){
        model.addAttribute("videoList", videoService.getRelatedVideos(new VideoRequest(mood)));
        return "suggested-videos";
    }

    @GetMapping("/video/{videoId}")
    public String video(Model model, @PathVariable("videoId") String videoId) {
        model.addAttribute("src", "vid-1.mp4");
        return "video";
    }
}

