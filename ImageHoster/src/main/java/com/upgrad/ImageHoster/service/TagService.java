package com.upgrad.ImageHoster.service;

import com.upgrad.ImageHoster.model.Tag;
import com.upgrad.ImageHoster.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public Tag getTagByName(String title) {
        return tagRepository.findTag(title);
    }
    
    public Tag createTag(Tag tag) {
        return tagRepository.createTag(tag);
    }
}
