beans {
    pictureRepo(DummyPictureRepository, "xml-based-repository-id-001") { bean ->
        bean.alias = "pictureDao"
    }
}