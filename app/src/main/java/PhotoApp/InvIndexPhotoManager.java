package PhotoApp;

public class InvIndexPhotoManager extends PhotoManager {
    BST<LinkedList<Photo>> invertedIndex;

    public InvIndexPhotoManager() {
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

    public void deletePhoto(String path) {
        if (path == null || invertedIndex.empty()) {
            return;
        }

        // Store tags to be removed (tags with no photos after deletion)
        LinkedList<String> tagsToRemove = new LinkedList<>();

        // Traverse the BST using a recursive helper method
        deletePhotoHelper(path, invertedIndex.root, tagsToRemove);

        // Remove tags that have no photos
        tagsToRemove.findFirst();
        while (!tagsToRemove.empty()) {
            String tagToRemove = tagsToRemove.retrieve();
            invertedIndex.remove_key(tagToRemove);
            if (tagsToRemove.last()) {
                break;
            }
            tagsToRemove.findNext();
        }
    }

    private void deletePhotoHelper(String path, BST<LinkedList<Photo>>.BSTNode<LinkedList<Photo>> node,
            LinkedList<String> tagsToRemove) {
        if (node == null) {
            return;
        }

        // Process current node
        LinkedList<Photo> photos = node.data;
        photos.findFirst();
        boolean photoFound = false;

        while (true) {
            Photo currentPhoto = photos.retrieve();
            if (currentPhoto.getPath().equals(path)) {
                photos.remove();
                photoFound = true;
                break;
            }
            if (photos.last()) {
                break;
            }
            photos.findNext();
        }

        // If the photos list is empty after removal, add tag to removal list
        if (photoFound && photos.empty()) {
            tagsToRemove.insert(node.key);
        }

        // Recursively process left and right subtrees
        deletePhotoHelper(path, node.left, tagsToRemove);
        deletePhotoHelper(path, node.right, tagsToRemove);
    }

    public BST<LinkedList<Photo>> getInvIndexPhotos() {
        return invertedIndex;
    }
}