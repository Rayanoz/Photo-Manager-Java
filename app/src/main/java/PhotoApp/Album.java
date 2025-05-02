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
        // get rid of the condition"AND" and get the photos of the album.
        String[] tags = condition.split(" AND ");
        for (int i = 0; i < tags.length; i++) {
            tags[i] = tags[i].trim();
        }
        // iterate in every photo and check if the tags of the photo are a subset of the
        // tags.
        // if it is, add the photo to the album.
        LinkedList<Photo> res = new LinkedList<Photo>();
        if (manager.getPhotos() == null) { // Check if the photo manager is empty
            return null;
        }
        manager.getPhotos().findFirst();
        while (!manager.getPhotos().last()) {
            Photo photo = manager.getPhotos().retrieve();
            if (photo != null) {
                if (subset(photo.getTags(), tags)) {
                    res.insert(photo);
                }
            }
            manager.getPhotos().findNext();
        }
        if (manager.getPhotos().retrieve() != null) {
            Photo photo = manager.getPhotos().retrieve();
            if (subset(photo.getTags(), tags)) {
                res.insert(photo);
            }
        }
        return res;

    }

    // this method is used to check if a tag is exist in the Photo. it returns true
    // if the tag is in the list of tags, false otherwise.
    public boolean tagExist(LinkedList<String> l, String tag) {
        l.findFirst();
        while (!l.last()) {
            nbComps++;
            if (l.retrieve().equals(tag))
                return true;
            l.findNext();
        }
        nbComps++;
        if (l.retrieve().equals(tag))
            return true;
        return false;
    }

    public boolean photoExist(LinkedList<Photo> l, Photo photo) {
        l.findFirst();
        while (!l.last()) {
            if (l.retrieve().getPath().equals(photo.getPath()))
                return true;
            l.findNext();
        }
        if (l.retrieve().getPath().equals(photo.getPath()))
            return true;
        return false;
    }

    // this method is used to get the photos of a tag. it returns a list of photos
    // that have the tag.
    public LinkedList<Photo> getTagPhotos(String tag) {
        LinkedList<Photo> res = new LinkedList<Photo>();
        LinkedList<Photo> photos = manager.getPhotos();
        if (photos == null) { // Check if the photo manager is empty
            return null;
        }
        if (manager.getPhotos() == null) { // Check if the photo manager is empty
            return null;
        }
        photos.findFirst();
        while (!photos.last()) {
            Photo photo = photos.retrieve();
            if (photo != null) {
                if (tagExist(photo.getTags(), tag)) {
                    res.insert(photos.retrieve());
                }
            }
            photos.findNext();
        }
        if (photos.retrieve() != null) {
            Photo photo = photos.retrieve();
            if (tagExist(photo.getTags(), tag)) {
                res.insert(photos.retrieve());
            }
        }
        return res;
    }

    // this method is used to check if the tags of a photo are a subset of the tags
    // of an album.
    // it returns true if the tags of the photo are a subset of the tags of the
    // album, false otherwise.
    public boolean subset(LinkedList<String> tags, String[] tag) {
        for (int i = 0; i < tag.length; i++) {
            if (!tagExist(tags, tag[i]))
                return false;
        }
        return true;
    }

    public LinkedList<Photo> ANDOperation(LinkedList<Photo> album1, LinkedList<Photo> album2) {
        LinkedList<Photo> result = new LinkedList<Photo>();
        if (album1 == null || album2 == null || album1.empty() || album2.empty()) {
            return result;
        }
        album1.findFirst();
        while (true) {
            if (!photoExist(result, album1.retrieve())) {
                album2.findFirst();
                while (true) {
                    if (album2.retrieve().getPath().equals(album1.retrieve().getPath())) {
                        result.insert(album1.retrieve());
                        break;
                    }
                    if (!album2.last())
                        album2.findNext();
                    else
                        break;
                }
            }
            if (!album1.last())
                album1.findNext();
            else
                break;
        }
        return result;
    }

}