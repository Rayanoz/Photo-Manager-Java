package PhotoApp;

public class PhotoManager {
    LinkedList<Photo> photos; // List of all photos

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

    // Fixed getPhoto to handle last node case, like deletePhoto
    public void deletePhoto(String path) {
        if (path == null) {
            return;
        }
        photos.findFirst();
        while (true) {
            Photo currentPhoto = photos.retrieve();
            if (currentPhoto == null) {
                break; // No more photos to check
            }
            if (currentPhoto.getPath().equals(path)) {
                photos.remove();
                return;
            }
            if (photos.last()) {
                break;
            }
            photos.findNext();
        }
        // If the photo is not found, do nothing
    }

    // Fixed getPhoto to handle last node case, like deletePhoto
    public Photo getPhoto(String string) {
        if (string == null) {
            throw new IllegalArgumentException("Path cannot be null");
        }
        photos.findFirst();
        while (true) {
            Photo currentPhoto = photos.retrieve();
            if (currentPhoto.getPath().equals(string)) {
                return currentPhoto;
            }
            if (photos.last()) {
                break;
            }
            photos.findNext();
        }
        return null; // If the photo is not found, return null
    }
}
