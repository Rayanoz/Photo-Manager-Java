package PhotoApp;
public class InvIndexPhotoManager{
    BST<LinkedList<Photo>> invertedIndex; 
    public InvIndexPhotoManager(){
        invertedIndex = new BST<LinkedList<Photo>>();
    }

public void addPhoto(Photo photo) {
    if (photo == null || photo.getTags().empty()) {
        return;
    }

    LinkedList<String> tags = photo.getTags();
    tags.findFirst();

    while (true) {
        String tag = tags.retrieve();
        LinkedList<Photo> photos;
        
        if (invertedIndex.findkey(tag)) { // Tag already exists
            photos = invertedIndex.retrieve();
            if (!photos.contains(photo)) {
                photos.insert(photo);
            }
        } else { // Tag does not exist
            photos = new LinkedList<Photo>();
            photos.insert(photo);
            invertedIndex.insert(tag, photos);
        }

        if (tags.last()) { // Stop loop
            break;
        }
        tags.findNext();
    }
}


    public void deletePhoto(String path){
        
    }
    public BST<LinkedList<Photo>> getPhotos(){
        return invertedIndex;
    }
}