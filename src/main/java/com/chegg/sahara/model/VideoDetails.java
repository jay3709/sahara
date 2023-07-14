package com.chegg.sahara.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDetails implements Comparable<VideoDetails>{
    private String name;
    private String thumbnail;
    private String videoUrl;
    private int rating;
    private int ratingCount;
    private String detail;
    private String publisherName;
    private List<String> topics;
    private String mood;

    @Override
    public int compareTo(VideoDetails a)
    {
        return  a.rating - this.rating; //For descending order
    }
}
