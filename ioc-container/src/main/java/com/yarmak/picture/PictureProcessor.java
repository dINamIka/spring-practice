package com.yarmak.picture;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PictureProcessor {

    private final DummyPictureRepository pictureRepository;


    public Picture findPictureBy(final String id) {
        return this.pictureRepository.findBy(id);
    }

    public List<Picture> findAll() {
        return this.pictureRepository.findAll();
    }
}
