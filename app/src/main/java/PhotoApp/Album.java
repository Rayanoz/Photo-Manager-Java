package PhotoApp;

public class Album {
    private String name;
    private String condition;
    private PhotoManager manager;
    private int nbComps;
    private LinkedList<Photo> photos;
    private LinkedList<Photo> allPhotos;
    private LinkedList<Photo> result;

    public Album(String name, String condition, PhotoManager manager) {
        this.name = name;
        this.condition = condition;
        this.manager = manager;
        this.allPhotos = manager.getPhotos();
        this.result = new LinkedList<Photo>();
        this.nbComps = 0;
        this.photos = new LinkedList<Photo>();
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

    public LinkedList<Photo> getPhotos() {
        if (!photos.empty()) {
            return result;
        }
        allPhotos.findFirst();
        while (!allPhotos.last()) {
            Photo currentPhoto = allPhotos.retrieve();
            if (currentPhoto.getTags().contains(condition)) {
                result.insert(currentPhoto);
            }
            allPhotos.findNext();
        }
        return result;
    }

    public int getNbComps() {
        if (!photos.empty()) {
            return nbComps;
        }
        allPhotos.findFirst();
        while (!allPhotos.last()) {
            Photo currentPhoto = allPhotos.retrieve();
            if (currentPhoto.getTags().contains(condition)) {
                result.insert(currentPhoto);
            }
            nbComps++;
            allPhotos.findNext();
        }
        return nbComps;
    }
}
