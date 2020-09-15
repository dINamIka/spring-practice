package com.yarmak.picture;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public abstract class AnnotationBasedAbstractPictureProcessor {

    @Lookup
    protected abstract DummyPictureRepository getRepo();

    @Lookup("pictureRepo")
    protected abstract Object getRepoByName();

    public Picture findPictureBy(final String id) {
        return this.getRepo().findBy(id);
    }

}
