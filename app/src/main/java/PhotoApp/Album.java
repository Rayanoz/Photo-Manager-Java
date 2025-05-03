package PhotoApp;

public class Album {

    private String name;
    private String condition;
    private PhotoManager manager;
    private InvIndexPhotoManager invManager;
    private int nbComps;

    public Album(String name, String condition, PhotoManager manager) {
        this.name = name;
        this.condition = condition;
        this.manager = manager;
        this.nbComps = 0;
    }

    public Album(String name, String condition, InvIndexPhotoManager invManager) {
        this.name = name;
        this.condition = condition;
        this.invManager = invManager;
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

    public InvIndexPhotoManager getInvManager() {
        return invManager;
    }

    public int getNbComps() {
        return nbComps;
    }

    // getPhotos now can handle OR condition
    public LinkedList<Photo> getPhotos() {
        nbComps = 0;
        LinkedList<Photo> result = new LinkedList<Photo>();
        if (manager == null) {
            throw new IllegalArgumentException("PhotoManager cannot be null");
        }
        LinkedList<Photo> allPhotos = manager.getPhotos();
        // Null safety
        if (allPhotos == null || allPhotos.empty()) {
            return result;
        }
        // Split condition into OR groups
        String[] orGroups = condition.split("\\s+OR\\s+");

        allPhotos.findFirst();
        while (true) {
            Photo photo = allPhotos.retrieve();
            if (photo == null) {
                break;
            }

            LinkedList<String> tags = photo.getTags();
            boolean matches = false;

            for (String group : orGroups) {
                String[] andTags = group.trim().split("\\s+AND\\s+");
                boolean groupMatch = true;

                for (String tag : andTags) {
                    tag = tag.trim();
                    nbComps++;
                    if (!tagContain(tags, tag)) {
                        groupMatch = false;
                        break;
                    }
                }
                if (groupMatch) {
                    matches = true;
                    break;
                }
            }
            if (matches) {
                result.insert(photo);
            }

            if (allPhotos.last()) {
                break;
            }
            allPhotos.findNext();
        }
        return result;
    }

    public boolean tagContain(LinkedList<String> tags, String tag) {
        tags.findFirst();
        while (!tags.last()) {
            nbComps++; // Increase comparison counter
            if (tags.retrieve().equals(tag)) {
                return true;
            }
            tags.findNext();
        }
        // Final comparison for the last tag
        nbComps++;
        if (tags.retrieve().equals(tag)) {
            return true;
        }
        return false;
    }

    public boolean tagContain(BST<LinkedList<String>> tags, String tag) {
        if (tags == null || tag == null) {
            return false;
        }

        nbComps = 0; // Reset comparison counter
        BST<LinkedList<String>>.BSTNode<LinkedList<String>> current = tags.root;

        while (current != null) {
            nbComps++; // Increment for each comparison in BST traversal
            int comparison = tag.compareTo(current.key);

            if (comparison == 0) {
                return true;
            } else if (comparison < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return false;
    }

    public boolean BSTtagContain(BST<String> tags, String tag) {
        BST<String>.BSTNode<String> current = tags.root;
        while (current != null) {
            nbComps++; // Increment for each comparison
            int cmp = tag.compareTo(current.key);
            if (cmp == 0) {
                return true;
            } else if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    // Overload subset to use BST<String>
    public boolean subset(BST<String> tags, String[] tagArr) {
        for (int i = 0; i < tagArr.length; i++) {
            if (!BSTtagContain(tags, tagArr[i])) {
                return false;
            }
        }
        return true;
    }

    public LinkedList<Photo> getTagPhoto(String tag) {
        LinkedList<Photo> result = new LinkedList<Photo>();
        if (invManager == null) {
            return null;
        }
        BST<LinkedList<Photo>> allTags = invManager.getInvIndexPhotos();
        if (allTags == null || allTags.empty()) {
            return result;
        }

        // Find the tag in the BST
        if (allTags.findkey(tag)) {
            LinkedList<Photo> photos = allTags.retrieve();
            photos.findFirst();
            while (true) {
                result.insert(photos.retrieve());
                if (photos.last()) {
                    break;
                }
                photos.findNext();
            }
        }
        return result;

    }

    public LinkedList<Photo> getPhotosBST() {
        nbComps = 0;
        LinkedList<Photo> res = new LinkedList<Photo>();
        if (condition == null)
            return res;
        if (invManager == null) {
            throw new IllegalArgumentException("InvIndexPhotoManager cannot be null");
        }
        if (condition.equals("")) // no condition case
            return manager.photos;

        // Split by OR first
        String[] orGroups = condition.split("\\s+OR\\s+");
        for (String group : orGroups) {
            // For each OR group, split by AND
            String[] andTags = group.trim().split("\\s+AND\\s+");
            LinkedList<Photo> candidatePhotos = new LinkedList<Photo>();

            // Start with all photos for the first tag in the AND group
            if (andTags.length > 0) {
                candidatePhotos = getTagPhoto(andTags[0].trim());
            }

            // For each photo in candidatePhotos, check if it contains all AND tags using
            // BSTtagContain
            if (!candidatePhotos.empty()) {
                candidatePhotos.findFirst();
                while (true) {
                    Photo photo = candidatePhotos.retrieve();
                    BST<String> tagsBST = photo.getTagsBST();
                    boolean matches = true;
                    for (String tag : andTags) {
                        tag = tag.trim();
                        if (!BSTtagContain(tagsBST, tag)) {
                            matches = false;
                            break;
                        }
                    }
                    if (matches && !photoExist(res, photo)) {
                        res.insert(photo);
                    }
                    if (candidatePhotos.last())
                        break;
                    candidatePhotos.findNext();
                }
            }
        }
        return res;
    }

    // Helper to check if a photo is already in the result list
    private boolean photoExist(LinkedList<Photo> result, Photo photo) {
        if (result.empty())
            return false;
        result.findFirst();
        while (true) {
            if (result.retrieve().path.equals(photo.path)) {
                return true;
            }
            if (!result.last())
                result.findNext();
            else
                break;
        }
        return false;
    }
}
