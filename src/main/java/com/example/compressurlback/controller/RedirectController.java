package com.example.compressurlback.controller;
import com.example.compressurlback.service.UrlService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectController {
    UrlService urlService;
    RedirectController(UrlService urlService) {
        this.urlService = urlService;
    }



    @PostMapping("/shorten")
    public String shortenUrl(@RequestParam String url) {
        return urlService.createShortUrl(url);
    }

}
