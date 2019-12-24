package com.yarmak.picture;

import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class DummyPictureRepository {

    public static final Picture WINTER_SHOES_PIC = new Picture("1", "winter_shoes_front", URI.create("/tmp/images/winter_shoes_front.jpg"), new Resolution(480, 320));
    public static final Picture SNEAKERS_PIC = new Picture("2", "sneakers_front", URI.create("/tmp/images/sneakers_front.jpg"), new Resolution(480, 320));
    public static final Picture T_SHIRT_PIC = new Picture("3", "green_t-shirt_front", URI.create("/tmp/images/green_t-shirt_front.jpg"), new Resolution(480, 320));


    private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    private final String repoId;

    public DummyPictureRepository(final String repoId) {
        this.repoId = repoId;
        LOGGER.info(String.format("Repository initialized with ID: %s", this.repoId));
    }

    private final Map<String, Picture> storage = Map.of(
            "1", WINTER_SHOES_PIC,
            "2", SNEAKERS_PIC,
            "3", T_SHIRT_PIC);


    public Picture findBy(final String id) {
        return this.storage.get(id);
    }

    public List<Picture> findAll() {
        return new ArrayList<>(this.storage.values());
    }

}
