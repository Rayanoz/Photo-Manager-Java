package PhotoApp;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AppTest {

    private PhotoManager manager;
    private Photo photo1;
    private Photo photo2;
    private Photo photo3;

    @BeforeEach
    void setUp() {
        manager = new PhotoManager();

        photo1 = new Photo("hedgehog.jpg", App.toTagsLinkedList("animal, hedgehog, apple, grass, green"));
        photo2 = new Photo("bear.jpg", App.toTagsLinkedList("animal, bear, cab, grass, wind"));
        photo3 = new Photo("orange-butterfly.jpg", App.toTagsLinkedList("insect, butterfly, flower, color"));

        manager.addPhoto(photo1);
        manager.addPhoto(photo2);
        manager.addPhoto(photo3);
    }

    @AfterEach
    void tearDown() {
        manager = null;
        photo1 = null;
        photo2 = null;
        photo3 = null;
    }

    @Test
    @DisplayName("Photo creation and properties test")
    void testPhotoCreation() {
        assertEquals("hedgehog.jpg", photo1.getPath());

        LinkedList<String> tags = photo1.getTags();
        assertTrue(tags.contains("animal"));
        assertTrue(tags.contains("hedgehog"));
        assertTrue(tags.contains("grass"));
        assertTrue(tags.contains("green"));
        assertTrue(tags.contains("apple"));
        assertEquals(5, tags.size());
    }

    @Test
    @DisplayName("PhotoManager add and retrieve photos test")
    void testPhotoManagerAddRetrieve() {
        Photo retrievedPhoto = manager.getPhoto("bear.jpg");
        assertNotNull(retrievedPhoto);
        assertEquals("bear.jpg", retrievedPhoto.getPath());

        LinkedList<Photo> allPhotos = manager.getPhotos();
        assertEquals(3, allPhotos.size());
        assertTrue(allPhotos.contains(photo1));
        assertTrue(allPhotos.contains(photo2));
        assertTrue(allPhotos.contains(photo3));
    }

    @Test
    @DisplayName("PhotoManager delete photo test")
    void testPhotoManagerDelete() {
        manager.deletePhoto("bear.jpg");
        assertNull(manager.getPhoto("bear.jpg"));
        assertEquals(2, manager.getPhotos().size());

        manager.deletePhoto("nonexistent.jpg");
        assertEquals(2, manager.getPhotos().size());
    }

    @Test
    @DisplayName("Album creation and query test")
    void testAlbumCreation() {
        Album album1 = new Album("Album1", "bear", manager);
        assertEquals("Album1", album1.getName());
        assertEquals("bear", album1.getCondition());

        LinkedList<Photo> album1Photos = album1.getPhotos();
        assertEquals(1, album1Photos.size());
        assertEquals("bear.jpg", album1Photos.getFirst().getPath());
    }

    @Test
    @DisplayName("Album update after photo deletion test")
    void testAlbumUpdateAfterDeletion() {
        Album album = new Album("AnimalAlbum", "animal", manager);
        assertEquals(2, album.getPhotos().size());

        manager.deletePhoto("bear.jpg");

        assertEquals(1, album.getPhotos().size());
        assertEquals("hedgehog.jpg", album.getPhotos().getFirst().getPath());
    }

    @Test
    @DisplayName("Tag parsing and LinkedList creation test")
    void testTagsLinkedList() {
        LinkedList<String> tags = App.toTagsLinkedList("tag1, tag2, tag3 , tag4");

        assertEquals(4, tags.size());
        assertTrue(tags.contains("tag1"));
        assertTrue(tags.contains("tag2"));
        assertTrue(tags.contains("tag3"));
        assertTrue(tags.contains("tag4"));

        LinkedList<String> emptyTags = App.toTagsLinkedList("");
        assertEquals(1, emptyTags.size());

        LinkedList<String> singleTag = App.toTagsLinkedList("onlytag");
        assertEquals(1, singleTag.size());
        assertTrue(singleTag.contains("onlytag"));
    }

    // This test covers both AND && OR operations
    @Test
    @DisplayName("Complex album query test")
    void testComplexAlbumQuery() {
        Album complexAlbum = new Album("ComplexAlbum", "animal AND green OR flower", manager);

        LinkedList<Photo> albumPhotos = complexAlbum.getPhotos();
        assertNotNull(albumPhotos);
        assertTrue(albumPhotos.contains(photo1));
        assertTrue(albumPhotos.contains(photo3));
        assertFalse(albumPhotos.contains(photo2));
    }

    @Test
    @DisplayName("Album should return empty for unknown condition")
    void testEmptyAlbumOnUnknownCondition() {
        Album album = new Album("EmptyAlbum", "unknownTag", manager);
        LinkedList<Photo> photos = album.getPhotos();
        assertNotNull(photos);
        assertEquals(0, photos.size());
    }

    @Test
    @DisplayName("Manager should ignore deletion of nonexistent photo")
    void testManagerDeleteNonexistent() {
        manager.deletePhoto("does-not-exist.jpg");
        LinkedList<Photo> photos = manager.getPhotos();
        assertEquals(3, photos.size());
    }

    @Test
    @DisplayName("Duplicate insertion test")
    void testDuplicateInsertion() {
        manager.addPhoto(photo2);
        assertEquals(4, manager.getPhotos().size());
    }

    @Test
    @DisplayName("Album comparison counter increments correctly")
    void testNbCompsCount() {
        Album album = new Album("CountAlbum", "animal AND grass", manager);
        album.getPhotos();
        assertTrue(album.getNbComps() > 0);
    }

    @Test
    @DisplayName("Inverted index album single tag query")
    void testInvIndexAlbumSingleTag() {
        InvIndexPhotoManager invManager = new InvIndexPhotoManager();
        invManager.addPhoto(photo1);
        invManager.addPhoto(photo2);
        invManager.addPhoto(photo3);

        Album album = new Album("InvAlbum1", "bear", invManager);
        LinkedList<Photo> photos = album.getPhotosBST(); // or getPhotos() if implemented for invManager
        assertEquals(1, photos.size());
        assertEquals("bear.jpg", photos.getFirst().getPath());
    }

    @Test
    @DisplayName("Inverted index album AND query")
    void testInvIndexAlbumAndQuery() {
        InvIndexPhotoManager invManager = new InvIndexPhotoManager();
        invManager.addPhoto(photo1);
        invManager.addPhoto(photo2);
        invManager.addPhoto(photo3);

        Album album = new Album("InvAlbum2", "animal AND grass", invManager);
        LinkedList<Photo> photos = album.getPhotosBST();
        assertEquals(2, photos.size());
        assertTrue(photos.contains(photo1));
        assertTrue(photos.contains(photo2));
    }

    @Test
    @DisplayName("Inverted index album OR query")
    void testInvIndexAlbumOrQuery() {
        InvIndexPhotoManager invManager = new InvIndexPhotoManager();
        invManager.addPhoto(photo1);
        invManager.addPhoto(photo2);
        invManager.addPhoto(photo3);

        Album album = new Album("InvAlbum3", "green OR flower", invManager);
        LinkedList<Photo> photos = album.getPhotosBST();
        assertTrue(photos.contains(photo1));
        assertTrue(photos.contains(photo3));
        assertFalse(photos.contains(photo2));
    }

    @Test
    @DisplayName("Inverted index album returns empty for unknown tag")
    void testInvIndexAlbumUnknownTag() {
        InvIndexPhotoManager invManager = new InvIndexPhotoManager();
        invManager.addPhoto(photo1);
        invManager.addPhoto(photo2);

        Album album = new Album("InvAlbumEmpty", "notatag", invManager);
        LinkedList<Photo> photos = album.getPhotosBST();
        assertNotNull(photos);
        assertEquals(0, photos.size());
    }

}
