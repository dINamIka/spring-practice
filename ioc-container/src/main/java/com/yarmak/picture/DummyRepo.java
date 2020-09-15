package com.yarmak.picture;

import java.util.List;

public interface DummyRepo {
    Picture findBy(String id);

    List<Picture> findAll();
}
