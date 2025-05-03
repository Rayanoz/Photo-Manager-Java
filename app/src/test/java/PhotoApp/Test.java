package PhotoApp;

public class Test {

    public static void main(String[] args) {
        PhotoManager manager = new PhotoManager();
        Photo photo1 = new Photo("hedgehog.jpg", toTagsLinkedList("animal, hedgehog, apple, grass, green"));
        manager.addPhoto(photo1);
        Photo photo2 = new Photo("bear.jpg", toTagsLinkedList("animal, bear, cab, grass, wind"));
        manager.addPhoto(photo2);
        Photo photo3 = new Photo("orange-butterfly.jpg", toTagsLinkedList("insect, butterfly, flower, color"));
        manager.addPhoto(photo3);
        Album album1 = new Album("Album1", "bear", manager);
        Album album2 = new Album("Album2", "animal AND grass", manager);
        System.out.println("Get photo1 path and tags:");
        System.out.println("photo1 path: " + photo1.getPath());
        printTags(photo1.getTags());
        System.out.println("Get album1 name, condition, and photos:");
        System.out.println("album1 name: " + album1.getName());
        System.out.println("album1 condition: " + album1.getCondition());
        System.out.println("album1 photos:");
        printPhotos(album1.getPhotos());
        System.out.println("Get album1 number of comparisons: " + album1.getNbComps());
        System.out.println("Get album2 name, condition, and photos:");
        System.out.println("album2 name: " + album2.getName());
        System.out.println("album2 condition: " + album2.getCondition());
        System.out.println("album2 photos:");
        printPhotos(album2.getPhotos());
        System.out.println("Get album2 number of comparisons: " + album2.getNbComps());
        System.out.println("Delete the photo 'bear.jpg':");
        manager.deletePhoto("bear.jpg");
    }

    private static LinkedList<String> toTagsLinkedList(String tags) {
        LinkedList<String> result = new LinkedList<String>();
        String[] tagsArray = tags.split("\\s*,\\s*");

        for (int i = 0; i < tagsArray.length; i++) {
            result.insert(tagsArray[i]);
        }

        return result;
    }

    private static void printTags(LinkedList<String> tags) {
        if (tags.empty()) {
            System.out.println("No tags available.");
            return;
        }
        System.out.print("Tags: ");
        tags.findFirst();
        while (!tags.last()) {
            System.out.print(tags.retrieve() + ", ");
            tags.findNext();
        }
        System.out.println(tags.retrieve());
    }

    private static void printPhotos(LinkedList<Photo> photos) {
        if (photos.empty()) {
            System.out.println("No photos available.");
            return;
        }
        photos.findFirst();
        while (!photos.last()) {
            System.out.print(photos.retrieve().getPath() + ", ");
            photos.findNext();
        }
        System.out.println(photos.retrieve().getPath());
    }
}
