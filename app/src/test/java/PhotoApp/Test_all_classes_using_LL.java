package PhotoApp;

public class Test_all_classes_using_LL {
    public static void displayListOfPhoto(LinkedList<Photo> L) {
        if (L == null) {
            System.out.println("null List");
        } else if (L.empty()) {
            System.out.println("empty list");
        } else {
            // System.out.println("\nall photos are ");
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

    public static void test_1_all_classes_with_out_album_LL() {
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
        PhotoManager manager1 = new PhotoManager();
        LinkedList<Photo> all_photos = manager1.getPhotos();
        System.out.print("all photos before adding any photo now are: ");
        displayListOfPhoto(all_photos);
        System.out.println("-----------------------");

        System.out.println("deleting hedgehog.jpg");
        manager1.deletePhoto("hedgehog.jpg");
        all_photos = manager1.getPhotos();
        System.out.print("all photos after trying to delete from empty photos are: ");
        displayListOfPhoto(all_photos);
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
        all_photos = manager1.getPhotos();
        displayListOfPhoto(all_photos);
        System.out.println("-------------------------------");
        System.out.println("deleting not existing photo dog.jpg");
        manager1.deletePhoto("dog.jpg");
        all_photos = manager1.getPhotos();
        displayListOfPhoto(all_photos);
        System.out.println("--------------------------------");
        System.out.println("deleting hedgehog.jpg");
        manager1.deletePhoto("hedgehog.jpg");
        all_photos = manager1.getPhotos();
        displayListOfPhoto(all_photos);
        System.out.println("deleting  bear.jpg");
        manager1.deletePhoto("bear.jpg");
        all_photos = manager1.getPhotos();
        displayListOfPhoto(all_photos);
        System.out.println("deleting butterfly1.jpg");
        manager1.deletePhoto("butterfly1.jpg");
        all_photos = manager1.getPhotos();
        displayListOfPhoto(all_photos);
        System.out.println("deleting butterfly2.jpg");
        manager1.deletePhoto("butterfly2.jpg");
        all_photos = manager1.getPhotos();
        displayListOfPhoto(all_photos);
        System.out.println("deleting fox.jpg");
        manager1.deletePhoto("fox.jpg");
        all_photos = manager1.getPhotos();
        displayListOfPhoto(all_photos);
        System.out.println("deleting panda.jpg");
        manager1.deletePhoto("panda.jpg");
        all_photos = manager1.getPhotos();
        displayListOfPhoto(all_photos);
        System.out.println("deleting wolf.jpg");
        manager1.deletePhoto("wolf.jpg");
        all_photos = manager1.getPhotos();
        displayListOfPhoto(all_photos);
        System.out.println("deleting raccoon.jpg");
        manager1.deletePhoto("raccoon.jpg");
        all_photos = manager1.getPhotos();
        displayListOfPhoto(all_photos);
        System.out.println("test album");
        Album A = new Album("aa", "bear", manager1);
        LinkedList<Photo> res = A.getPhotos();
        displayListOfPhoto(res);
        // System.out.println(A.getNbComps());
        // BST<LinkedList<Photo>>index=manager1.getPhotos();
        // index.displayListOfPhoto(manager1.allPhotos);
        // index.inOrder();

    }

    public static void test_2_album_LL() {

        System.out.println("\n---------------------");
        // String cod="a AND Tag2 AND Tag3 AND Tag4";
        // String cod=" ";
        // String a[]=cod.split(" AND ");
        // System.out.println("tags=");
        // System.out.println("length="+a.length);
        // for(int i=0;i<a.length;i++)
        // System.out.print(a[i]+" ");

        System.out.println("\n---------------------");
        // LinkedList<String>tags1=new LinkedList<String>();
        // tags1.insert("animal");
        // tags1.insert("hedgehog");
        // tags1.insert("apple");
        // tags1.insert("grass");
        // tags1.insert("green");
        // Photo p1=new Photo("hedgehog.jpg", tags1);
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
        PhotoManager manager1 = new PhotoManager();
        System.out.println("album0 befor adding any photo");
        Album album0 = new Album("aa", "animal  AND grass AND fox", manager1);
        LinkedList<Photo> res = album0.getPhotos();
        displayListOfPhoto(res);
        System.out.println("total number of tag comparision=" + album0.getNbComps());
        System.out.println("-----------------");
        manager1.addPhoto(p1);
        manager1.addPhoto(p2);
        manager1.addPhoto(p3);
        manager1.addPhoto(p4);
        manager1.addPhoto(p5);
        manager1.addPhoto(p6);
        manager1.addPhoto(p7);
        manager1.addPhoto(p8);

        System.out.println("test album");
        System.out.println("-----------------");
        Album album1 = new Album("Album1", "bear", manager1);
        res = album1.getPhotos();
        System.out.println("all photos with condition bear are");
        displayListOfPhoto(res);
        System.out.println("total number of tag comparision=" + album1.getNbComps());
        System.out.println("-----------------");
        Album album2 = new Album("Album2", "animal AND grass", manager1);
        res = album2.getPhotos();
        System.out.println("all photos with condition animal AND grass are");
        displayListOfPhoto(res);
        System.out.println("total number of tag comparision=" + album2.getNbComps());
        System.out.println("-----------------");
        Album album3 = new Album("aa", "animal  AND grass AND fox", manager1);
        res = album3.getPhotos();
        System.out.println("all photos with condition animal  AND grass AND fox are");
        displayListOfPhoto(res);
        System.out.println("total number of tag comparision=" + album3.getNbComps());
        System.out.println("-----------------");
        Album album4 = new Album("aa", "dog  AND grass AND fox", manager1);
        res = album3.getPhotos();
        System.out.println("all photos with condition dog  AND grass AND fox are");
        displayListOfPhoto(res);
        System.out.println("total number of tag comparision=" + album3.getNbComps());
        System.out.println("-----------------");

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

        LinkedList<Photo> all_photos = manager.getPhotos();
        displayListOfPhoto(all_photos);

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
        test_1_all_classes_with_out_album_LL();
        System.out.println("===============================");
        test_3();
        test_2_album_LL();
    }

}
