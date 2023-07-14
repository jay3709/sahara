package com.chegg.sahara;

import java.util.Arrays;
import java.util.List;

public enum Sentiment {

    happy,
    stressed,
    sad,
    anxiety,
    low;

    public static List<String> getSentiments(){
        return Arrays.asList(happy.name(),
                stressed.name(),
                sad.name(),
                anxiety.name(),
                low.name());
    }
}
