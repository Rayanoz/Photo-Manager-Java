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
        Album album2 = new Album("Album2", "animal AND grass", manager);

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
} 
