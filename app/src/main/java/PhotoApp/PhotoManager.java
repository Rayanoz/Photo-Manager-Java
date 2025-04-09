package PhotoApp;

public class PhotoManager {
    private LinkedList<Photo> photos; // List of all photos

    public PhotoManager() {
        photos = new LinkedList<Photo>();
    }

    public LinkedList<Photo> getPhotos() {
        if (photos.empty()) {
            return null;
        } else {
            return photos;
        }
    }

    public void addPhoto(Photo p) {
        if (p == null) {
            throw new IllegalArgumentException("Photo cannot be null");
        }
        photos.insert(p);
    }

    public void deletePhoto(String path) {
        if (path == null) {
            throw new IllegalArgumentException("Path cannot be null");
        }
        photos.findFirst();
        while (!photos.last()) {
            Photo currentPhoto = photos.retrieve();
            if (currentPhoto.getPath().equals(path)) {
                photos.remove();
                return;
            }
            photos.findNext();
        }
        // If the photo is not found, do nothing
    }

    public Photo getPhoto(String string) {
        if (string == null) {
            throw new IllegalArgumentException("Path cannot be null");
        }
        photos.findFirst();
        while (!photos.last()) {
            Photo currentPhoto = photos.retrieve();
            if (currentPhoto.getPath().equals(string)) {
                return currentPhoto;
            }
            photos.findNext();
        }
        return null; // If the photo is not found, return null
    }
}
