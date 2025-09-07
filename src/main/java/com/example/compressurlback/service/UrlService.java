package com.example.compressurlback.service;

import org.springframework.stereotype.Service;

@Service
public class UrlService {

    public String compressUrl(String url) {

        return "compressed url for:"+url;
    }
}
