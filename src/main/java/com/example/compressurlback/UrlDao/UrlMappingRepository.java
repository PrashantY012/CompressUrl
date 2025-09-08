package com.example.compressurlback.UrlDao;

import com.example.compressurlback.model.UrlMapping;
import org.springframework.data.repository.CrudRepository;

//check why interface and not class?
public interface UrlMappingRepository extends CrudRepository<UrlMapping, String> {
//    UrlMapping findById(String id);
}
