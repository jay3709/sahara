package com.chegg.sahara.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatResponse {
    private String prompt;
    private String tips;
    private String sentiment;
    private String questions;
}
