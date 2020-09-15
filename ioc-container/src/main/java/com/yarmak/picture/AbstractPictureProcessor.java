package com.yarmak.picture;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class AbstractPictureProcessor {

    protected abstract DummyPictureRepository getRepo();

    public Picture findPictureBy(final String id) {
        return this.getRepo().findBy(id);
    }

}
