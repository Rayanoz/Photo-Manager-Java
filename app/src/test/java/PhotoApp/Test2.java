package PhotoApp;

public class Test2 {
    public static void displayListOfPhoto(LinkedList<Photo> L) {
        if (L == null) {
            System.out.println("null List");
        } else if (L.empty()) {
            System.out.println("empty list");
        } else {

            L.findFirst();
            while (!L.last()) {
                System.out.print("\n" + L.retrieve().path + " ");
                // L.retrieve().display();
                L.findNext();
            }
            System.out.println("\n" + L.retrieve().path + " ");
            // L.retrieve().display();
            System.out.println("------------------------------");
        }
    }

    public static void test_1_all_classes_with_out_album_BST() {
        BST<Integer> b1 = new BST<Integer>();
        b1.inOrder();
        b1.insert("d", 4);
        b1.insert("b", 2);
        // b1.inOrder();
        b1.insert("a", 1);
        b1.insert("c", 3);
        b1.insert("e", 5);
        b1.insert("g", 7);
        b1.insert("f", 6);
        b1.remove_key("a");
        b1.inOrder();
        System.out.println("\n---------------------");

        Photo p1 = new Photo("hedgehog.jpg",
                toTagsLinkedList("animal, hedgehog, apple,grass, green"));

        LinkedList<String> tags2 = new LinkedList<String>();
        tags2.insert("animal");
        tags2.insert("bear");
        tags2.insert("cab");
        tags2.insert("grass");
        tags2.insert("wind");
        Photo p2 = new Photo("bear.jpg", tags2);

        LinkedList<String> tags3 = new LinkedList<String>();
        tags3.insert("insect");
        tags3.insert("butterfly");
        tags3.insert("flower");
        tags3.insert("color");
        Photo p3 = new Photo("butterfly1.jpg", tags3);
        LinkedList<String> tags4 = new LinkedList<String>();
        tags4.insert("insect");
        tags4.insert("butterfly");
        tags4.insert("black");
        tags4.insert("flower");
        Photo p4 = new Photo("butterfly2.jpg", tags4);

        LinkedList<String> tags5 = new LinkedList<String>();
        tags5.insert("animal");
        tags5.insert("fox");
        tags5.insert("tree");
        tags5.insert("forest");
        tags5.insert("grass");
        Photo p5 = new Photo("fox.jpg", tags5);

        LinkedList<String> tags6 = new LinkedList<String>();
        tags6.insert("animal");
        tags6.insert("bear");
        tags6.insert("panda");
        tags6.insert("grass");
        Photo p6 = new Photo("panda.jpg", tags6);

        LinkedList<String> tags7 = new LinkedList<String>();
        tags7.insert("animal");
        tags7.insert("wolf");
        tags7.insert("mountain");
        tags7.insert("sky");
        tags7.insert("snow");
        tags7.insert("cloud");
        Photo p7 = new Photo("wolf.jpg", tags7);

        LinkedList<String> tags8 = new LinkedList<String>();
        tags8.insert("animal");
        tags8.insert("raccoon");
        tags8.insert("log");
        tags8.insert("snow");
        Photo p8 = new Photo("raccoon.jpg", tags8);
        //////////////////
        InvIndexPhotoManager manager1 = new InvIndexPhotoManager();
        BST<LinkedList<Photo>> all_photos = manager1.getInvIndexPhotos();
        all_photos.inOrder();
        System.out.print("all photos before adding any photo now are: ");

        all_photos.inOrder();
        System.out.println("-----------------------");

        System.out.println("deleting hedgehog.jpg");
        manager1.deletePhoto("hedgehog.jpg");
        all_photos = manager1.getInvIndexPhotos();
        System.out.print("all photos after trying to delete from empty photos are: ");

        all_photos.inOrder();
        System.out.println("-----------------------");
        manager1.addPhoto(p1);
        manager1.addPhoto(p2);
        manager1.addPhoto(p3);
        manager1.addPhoto(p4);
        manager1.addPhoto(p5);
        manager1.addPhoto(p6);
        manager1.addPhoto(p7);
        manager1.addPhoto(p8);
        // trying to add an existing photo not affect all photos
        manager1.addPhoto(p8);
        System.out.print("all photos afetr adding 8 photos are:");

        displayListOfPhoto(manager1.getPhotos());
        System.out.println("all tags with thier photos in BST as follwing:");
        all_photos = manager1.getInvIndexPhotos();// **
        all_photos.inOrder(); // **
        System.out.println("-------------------------------");

        System.out.println("deleting not existing photo dog.jpg");
        manager1.deletePhoto("dog.jpg");
        displayListOfPhoto(manager1.getPhotos());
        System.out.println("all tags with thier photos in BST as follwing:");
        all_photos = manager1.getInvIndexPhotos();// **
        all_photos.inOrder(); // **
        System.out.println("-------------------------------");

        System.out.println("deleting hedgehog.jpg");
        manager1.deletePhoto("hedgehog.jpg");
        displayListOfPhoto(manager1.getPhotos());
        System.out.println("all tags with thier photos in BST as follwing:");
        all_photos = manager1.getInvIndexPhotos();// **
        all_photos.inOrder(); // **
        System.out.println("-------------------------------");
        System.out.println("deleting  bear.jpg");
        manager1.deletePhoto("bear.jpg");
        displayListOfPhoto(manager1.getPhotos());
        System.out.println("all tags with thier photos in BST as follwing:");
        all_photos = manager1.getInvIndexPhotos();// **
        all_photos.inOrder(); // **
        System.out.println("-------------------------------");
        System.out.println("deleting butterfly1.jpg");
        manager1.deletePhoto("butterfly1.jpg");
        displayListOfPhoto(manager1.getPhotos());
        System.out.println("all tags with thier photos in BST as follwing:");
        all_photos = manager1.getInvIndexPhotos();// **
        all_photos.inOrder(); // **
        System.out.println("-------------------------------");
        System.out.println("deleting butterfly2.jpg");
        manager1.deletePhoto("butterfly2.jpg");
        displayListOfPhoto(manager1.getPhotos());
        System.out.println("all tags with thier photos in BST as follwing:");
        all_photos = manager1.getInvIndexPhotos();// **
        all_photos.inOrder(); // **
        System.out.println("-------------------------------");
        System.out.println("deleting fox.jpg");
        manager1.deletePhoto("fox.jpg");
        displayListOfPhoto(manager1.getPhotos());
        System.out.println("all tags with thier photos in BST as follwing:");
        all_photos = manager1.getInvIndexPhotos();// **
        all_photos.inOrder(); // **
        System.out.println("-------------------------------");
        System.out.println("deleting panda.jpg");
        manager1.deletePhoto("panda.jpg");
        displayListOfPhoto(manager1.getPhotos());
        System.out.println("all tags with thier photos in BST as follwing:");
        all_photos = manager1.getInvIndexPhotos();// **
        all_photos.inOrder(); // **
        System.out.println("-------------------------------");
        System.out.println("deleting wolf.jpg");
        manager1.deletePhoto("wolf.jpg");
        displayListOfPhoto(manager1.getPhotos());
        System.out.println("all tags with thier photos in BST as follwing:");
        all_photos = manager1.getInvIndexPhotos();// **
        all_photos.inOrder(); // **
        System.out.println("-------------------------------");
        System.out.println("deleting raccoon.jpg");
        manager1.deletePhoto("raccoon.jpg");
        displayListOfPhoto(manager1.getPhotos());
        System.out.println("all tags with thier photos in BST as follwing:");
        all_photos = manager1.getInvIndexPhotos();// **
        all_photos.inOrder(); // **
        System.out.println("-------------------------------");
        System.out.println("test album");
        //
    }

    public static void test_3() {
        PhotoManager manager = new PhotoManager();
        Photo photo1 = new Photo("hedgehog.jpg",
                toTagsLinkedList("animal, hedgehog, apple,grass, green"));
        manager.addPhoto(photo1);
        Photo photo2 = new Photo("bear.jpg",
                toTagsLinkedList("animal, bear, cab, grass,wind"));
        manager.addPhoto(photo2);
        Photo photo3 = new Photo("orange-butterfly.jpg",
                toTagsLinkedList("insect,butterfly, flower, color"));
        manager.addPhoto(photo3);

        System.out.println("photo1 path: " + photo1.getPath());

        // You can get the list of tags of photo1 by calling photo1.getTags().

        // You can write a method that prints the list of tags of photo1.);
        LinkedList<String> tags = photo1.getTags();
        if (!tags.empty()) {
            tags.findFirst();
            while (!tags.last()) {
                System.out.print(tags.retrieve() + " ");
                tags.findNext();
            }
            System.out.print(tags.retrieve() + " ");
        }

        LinkedList<Photo> all_photos = manager.getPhotos();
        displayListOfPhoto(all_photos);
        System.out.println("\nDelete the photo ’bear.jpg’:");

        manager.deletePhoto("bear.jpg");
        all_photos = manager.getPhotos();
        displayListOfPhoto(all_photos);
        ///////////// check album
        Album album1 = new Album("Album1", "bear", manager);
        Album album2 = new Album("Album2", "animal AND grass", manager);
        System.out.println("Get photo1 path and tags:");

        System.out.println("Get album2 name, condition, and photos:");
        System.out.println("album2 name: " + album2.getName());
        System.out.println("album2 condition: " + album2.getCondition());
        // You can get the list of photos in album2 by calling album2.getPhotos().
        // You can write a method that prints the list of photos in album2.
    }

    private static LinkedList<String> toTagsLinkedList(String tags) {
        LinkedList<String> result = new LinkedList<String>();
        String[] tagsArray = tags.split("\\s*,\\s*");
        for (int i = 0; i < tagsArray.length; i++) {
            result.insert(tagsArray[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        test_1_all_classes_with_out_album_BST();
        System.out.println("===============================");
        test_3();
    }

}
