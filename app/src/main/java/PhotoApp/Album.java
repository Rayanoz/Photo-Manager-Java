package PhotoApp;

public class Album {
    private String name;
    private String condition;
    private PhotoManager manager;
    private int nbComps;

    public Album(String name, String condition, PhotoManager manager) {
        this.name = name;
        this.condition = condition;
        this.manager = manager;
        this.nbComps = 0;
    }

    public String getName() {
        return name;
    }

    public String getCondition() {
        return condition;
    }

    public PhotoManager getManager() {
        return manager;
    }

    public int getNbComps() {
        return nbComps;
    }

    public LinkedList<Photo> getPhotos() {
        // get rif of the condition"AND" and get the photos of the album.
        String[] tags = condition.split(" AND ");
        for (int i = 0; i < tags.length; i++) {
            tags[i] = tags[i].trim();
        }
        // iterate in every photo and check if the tags of the photo are a subset of the
        // tags.
        // if it is, add the photo to the album.
        LinkedList<Photo> photos = new LinkedList<Photo>();
        manager.getPhotos().findFirst();
        while (!manager.getPhotos().last()) {
            Photo photo = manager.getPhotos().retrieve();
            if (photo != null) {
                if (subset(photo.getTags(), tags)) {
                    photos.insert(photo);
                }
            }
            manager.getPhotos().findNext();
        }
        if (manager.getPhotos().retrieve() != null) {
            Photo photo = manager.getPhotos().retrieve();
            if (subset(photo.getTags(), tags)) {
                photos.insert(photo);
            }
        }
        return photos;

    }

    public boolean tagContain(LinkedList<String> tags, String tag) {
        tags.findFirst();
        while (!tags.last()) {
            if (tags.retrieve().equals(tag))
                return true;
            tags.findNext();
        }
        if (tags.retrieve().equals(tag))
            return true;
        return false;
    }

    // this method is used to check if the tags of a photo are a subset of the tags
    // of an album.
    // it returns true if the tags of the photo are a subset of the tags of the
    // album, false otherwise.
    public boolean subset(LinkedList<String> tags, String[] tag) {
        for (int i = 0; i < tag.length; i++) {
            if (!tagContain(tags, tag[i]))
                return false;
        }
        return true;
    }

}
