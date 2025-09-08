package com.example.compressurlback.service;

import com.example.compressurlback.UrlDao.UrlMappingRepository;
import com.example.compressurlback.model.UrlMapping;
import com.example.compressurlback.util.Base62Encoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class UrlService {
    public  final UrlMappingRepository urlMappingRepository ;
    public UrlService (UrlMappingRepository urlMappingRepository) {
        this.urlMappingRepository = urlMappingRepository;
    }
    public String createShortUrl(String longUrl) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(longUrl.getBytes(StandardCharsets.UTF_8));

            // Generate long base62 string from hash
            String base62 = Base62Encoder.encode(hash, 43); // SHA-256 → ~43 Base62 chars

            // Try sliding window of 7 chars
            for (int start = 0; start <= base62.length() - 7; start++) {
                String shortCode = base62.substring(start, start + 7);

                // ✅ Check DB
                if (!urlMappingRepository.existsById(shortCode)) {
//                    urlMappingRepository.save(new UrlMapping(shortCode, longUrl, LocalDateTime.now(), null));
                    return shortCode;
                }

                // If same URL already mapped → return existing code (idempotency)
                UrlMapping existing = urlMappingRepository.findById(shortCode).orElse(null);
                if (existing != null && existing.getLongUrl().equals(longUrl)) {
                    return existing.getId();
                }
            }

            // 🚨 Last resort: append random salt if all windows collide (practically impossible)
            String fallbackCode;
            do {
                fallbackCode = base62.substring(0, 6) + new Random().nextInt(62);
            } while (urlMappingRepository.existsById(fallbackCode));

//            urlMappingRepository.save(new UrlMapping(fallbackCode, longUrl, LocalDateTime.now(), null));
            return fallbackCode;

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 not available", e);
        }
    }

}
