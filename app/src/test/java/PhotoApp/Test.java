package PhotoApp;

public class Test {

    public static void main(String[] args) {
        PhotoManager manager = new PhotoManager();
        InvIndexPhotoManager invManager = new InvIndexPhotoManager();
        Photo photo1 = new Photo("hedgehog.jpg", toTagsLinkedList("animal, hedgehog, apple, grass, green"));
        manager.addPhoto(photo1);
        Photo photo2 = new Photo("bear.jpg", toTagsLinkedList("animal, bear, cab, grass, wind"));
        manager.addPhoto(photo2);
        Photo photo3 = new Photo("orange-butterfly.jpg", toTagsLinkedList("insect, butterfly, flower, color"));
        manager.addPhoto(photo3);
        invManager.addPhoto(photo1);
        invManager.addPhoto(photo2);
        invManager.addPhoto(photo3);
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
        System.out.println("Get album1 photos after deletion:");
        printPhotos(album1.getPhotos());
        System.out.println("Get album2 photos after deletion:");
        printPhotos(album2.getPhotos());
        System.out.println("Get photo2 path and tags after deletion:");
        System.out.println("photo2 path: " + photo2.getPath());
        printTags(photo2.getTags());
        System.out.println("Get photo3 path and tags after deletion:");
        System.out.println("photo3 path: " + photo3.getPath());
        printTags(photo3.getTags());
        System.out.println("Get photo1 path and tags after deletion:");
        System.out.println("photo1 path: " + photo1.getPath());
        printTags(photo1.getTags());
        System.out.println("Get album1 number of comparisons after deletion: " + album1.getNbComps());
        System.out.println("Get album2 number of comparisons after deletion: " + album2.getNbComps());
        // Tests for Album Inverted Index
        System.out.println("\n--- Testing Album Inverted Index ---");

        // Create new photos with various tags
        Photo photo4 = new Photo("mountain.jpg", toTagsLinkedList("nature, mountain, snow, sky, blue"));
        manager.addPhoto(photo4);
        Photo photo5 = new Photo("forest.jpg", toTagsLinkedList("nature, trees, green, path"));
        manager.addPhoto(photo5);
        invManager.addPhoto(photo4);
        invManager.addPhoto(photo5);

        // Create albums with different conditions to test inverted index
        Album albumInverted1 = new Album("Nature Album", "nature", invManager);
        Album albumInverted2 = new Album("Blue Album", "blue OR sky", invManager);
        Album albumInverted3 = new Album("Complex Album", "(nature AND green) OR snow", invManager);

        System.out.println("Nature Album photos:");
        printPhotos(albumInverted1.getPhotosBST());
        System.out.println("Nature Album comparisons: " + albumInverted1.getNbComps());

        System.out.println("Blue Album photos:");
        printPhotos(albumInverted2.getPhotosBST());
        System.out.println("Blue Album comparisons: " + albumInverted2.getNbComps());

        System.out.println("Complex Album photos:");
        printPhotos(albumInverted3.getPhotosBST());
        System.out.println("Complex Album comparisons: " + albumInverted3.getNbComps());

        // Modify a photo's tags and test if albums update correctly
        System.out.println("\nModifying photo tags and testing album updates:");
        photo4.getTags().findFirst();
        photo4.getTags().remove(); // Remove "nature" tag
        photo4.getTags().insert("sunset"); // Add new tag

        System.out.println("Nature Album photos after modification:");
        printPhotos(albumInverted1.getPhotosBST());
        System.out.println("Complex Album photos after modification:");
        printPhotos(albumInverted3.getPhotosBST());

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
