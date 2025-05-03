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

    // getPhotos now can handle OR condition 
    public LinkedList<Photo> getPhotos() {
        LinkedList<Photo> result = new LinkedList<Photo>();
        LinkedList<Photo> allPhotos = manager.getPhotos();
        //Null safety
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

    //Enhanced tagContain method to handle last node case
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

    // this method is used to check if the tags of a photo are a subset of the tags
    // of an album.
    // it returns true if the tags of the photo are a subset of the tags of the
    // album, false otherwise.
    public boolean subset(LinkedList<String> tags, String[] tag) {
        for (int i = 0; i < tag.length; i++) {
            if (!tagContain(tags, tag[i])) {
                return false;
            }
        }
        return true;
    }

    public LinkedList<Photo> ANDOperation(LinkedList<Photo> album1, LinkedList<Photo> album2) {
        LinkedList<Photo> result = new LinkedList<Photo>();
        album1.findFirst();
        while (!album1.last()) {
            Photo photo = album1.retrieve();
            if (photo != null) {
                // Check if photo exists in album2 by iterating through it
                album2.findFirst();
                boolean found = false;
                while (!album2.last() && !found) {
                    Photo photo2 = album2.retrieve();
                    if (photo2 != null && photo2.getPath().equals(photo.getPath())) {
                        found = true;
                    }
                    album2.findNext();
                }
                // Check the last element if not found yet
                if (!found && album2.retrieve() != null
                        && album2.retrieve().getPath().equals(photo.getPath())) {
                    found = true;
                }

                if (found) {
                    result.insert(photo);
                }
            }
            album1.findNext();
        }

        // Check the last element of album1
        if (album1.retrieve() != null) {
            Photo photo = album1.retrieve();

            // Check if photo exists in album2
            album2.findFirst();
            boolean found = false;
            while (!album2.last() && !found) {
                Photo photo2 = album2.retrieve();
                if (photo2 != null && photo2.getPath().equals(photo.getPath())) {
                    found = true;
                }
                album2.findNext();
            }
            // Check the last element
            if (!found && album2.retrieve() != null
                    && album2.retrieve().getPath().equals(photo.getPath())) {
                found = true;
            }

            if (found) {
                result.insert(photo);
            }
        }

        return result;
    }

}
