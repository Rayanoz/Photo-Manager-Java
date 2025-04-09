/*
 * This source file was generated by the Gradle 'init' task
 */
package PhotoApp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    private PhotoManager manager;
    private Photo photo1;
    private Photo photo2;
    private Photo photo3;

    @BeforeEach
    void setUp() {
        manager = new PhotoManager();

        // Create test photos with tags
        photo1 = new Photo("hedgehog.jpg", App.toTagsLinkedList("animal, hedgehog, apple, grass, green"));
        photo2 = new Photo("bear.jpg", App.toTagsLinkedList("animal, bear, cab, grass, wind"));
        photo3 = new Photo("orange-butterfly.jpg", App.toTagsLinkedList("insect, butterfly, flower, color"));

        // Add photos to manager
        manager.addPhoto(photo1);
        manager.addPhoto(photo2);
        manager.addPhoto(photo3);
    }

    @AfterEach
    void tearDown() {
        // Clean up resources
        manager = null;
        photo1 = null;
        photo2 = null;
        photo3 = null;
    }

    @Test
    @DisplayName("Photo creation and properties test")
    void testPhotoCreation() {
        assertEquals("hedgehog.jpg", photo1.getPath(), "Photo path should match");

        // Test tags
        LinkedList<String> tags = photo1.getTags();
        assertTrue(tags.contains("animal"), "Photo should have 'animal' tag");
        assertTrue(tags.contains("hedgehog"), "Photo should have 'hedgehog' tag");
        assertTrue(tags.contains("grass"), "Photo should have 'grass' tag");
        assertTrue(tags.contains("green"), "Photo should have 'green' tag");
        assertTrue(tags.contains("apple"), "Photo should have 'apple' tag");
        assertEquals(5, tags.size(), "Photo should have 5 tags");
    }

    @Test
    @DisplayName("PhotoManager add and retrieve photos test")
    void testPhotoManagerAddRetrieve() {
        // Test photo retrieval by path
        Photo retrievedPhoto = manager.getPhoto("bear.jpg");
        assertNotNull(retrievedPhoto, "Retrieved photo should not be null");
        assertEquals("bear.jpg", retrievedPhoto.getPath(), "Retrieved photo path should match");

        // Test all photos retrieval
        LinkedList<Photo> allPhotos = manager.getPhotos();
        assertEquals(3, allPhotos.size(), "Manager should have 3 photos");
        assertTrue(allPhotos.contains(photo1), "All photos should contain photo1");
        assertTrue(allPhotos.contains(photo2), "All photos should contain photo2");
        assertTrue(allPhotos.contains(photo3), "All photos should contain photo3");
    }

    @Test
    @DisplayName("PhotoManager delete photo test")
    void testPhotoManagerDelete() {
        // Delete a photo
        manager.deletePhoto("bear.jpg");

        // Verify deletion
        assertNull(manager.getPhoto("bear.jpg"), "Deleted photo should not be retrievable");
        assertEquals(2, manager.getPhotos().size(), "Manager should have 2 photos after deletion");

        // Try deleting non-existent photo
        manager.deletePhoto("nonexistent.jpg");
        assertEquals(2, manager.getPhotos().size(), "Manager should still have 2 photos");
    }

    @Test
    @DisplayName("Album creation and query test")
    void testAlbumCreation() {
        // Create albums with conditions
        Album album1 = new Album("Album1", "bear", manager);
        Album album2 = new Album("Album2", "animal AND grass", manager);

        // Test album properties
        assertEquals("Album1", album1.getName(), "Album name should match");
        assertEquals("bear", album1.getCondition(), "Album condition should match");

        // Test album photos based on condition
        LinkedList<Photo> album1Photos = album1.getPhotos();
        assertEquals(1, album1Photos.size(), "Album1 should have 1 photo");
        assertEquals("bear.jpg", album1Photos.getFirst().getPath(), "Album1 should contain bear.jpg");

        // Test AND condition
        LinkedList<Photo> album2Photos = album2.getPhotos();
        assertEquals(2, album2Photos.size(), "Album2 should have 2 photos");
        assertTrue(album2Photos.contains(photo1), "Album2 should contain hedgehog.jpg");
        assertTrue(album2Photos.contains(photo2), "Album2 should contain bear.jpg");
        assertFalse(album2Photos.contains(photo3), "Album2 should not contain orange-butterfly.jpg");
    }

    @Test
    @DisplayName("Album update after photo deletion test")
    void testAlbumUpdateAfterDeletion() {
        // Create album that includes a photo we'll delete
        Album album = new Album("AnimalAlbum", "animal", manager);
        assertEquals(2, album.getPhotos().size(), "Album should have 2 photos initially");

        // Delete a photo that's in the album
        manager.deletePhoto("bear.jpg");

        // Album should update automatically
        assertEquals(1, album.getPhotos().size(), "Album should have 1 photo after deletion");
        assertEquals("hedgehog.jpg", album.getPhotos().getFirst().getPath(),
                "Album should still contain hedgehog.jpg");
    }

    @Test
    @DisplayName("Tag parsing and LinkedList creation test")
    void testTagsLinkedList() {
        // Test the helper method
        LinkedList<String> tags = App.toTagsLinkedList("tag1, tag2,  tag3 , tag4");

        assertEquals(4, tags.size(), "Should have 4 tags");
        assertTrue(tags.contains("tag1"), "Should contain tag1");
        assertTrue(tags.contains("tag2"), "Should contain tag2");
        assertTrue(tags.contains("tag3"), "Should contain tag3");
        assertTrue(tags.contains("tag4"), "Should contain tag4");

        // Test with empty string
        LinkedList<String> emptyTags = App.toTagsLinkedList("");
        assertEquals(1, emptyTags.size(), "Should have 1 tag (empty string)");

        // Test with single tag
        LinkedList<String> singleTag = App.toTagsLinkedList("onlytag");
        assertEquals(1, singleTag.size(), "Should have 1 tag");
        assertTrue(singleTag.contains("onlytag"), "Should contain the single tag");
    }

    @Test
    @DisplayName("Complex album query test")
    void testComplexAlbumQuery() {
        // Create album with complex condition
        Album complexAlbum = new Album("ComplexAlbum", "animal AND (green OR flower)", manager);

        // Test album photos based on complex condition
        LinkedList<Photo> albumPhotos = complexAlbum.getPhotos();
        assertEquals(2, albumPhotos.size(), "Complex album should have 2 photos");
        assertTrue(albumPhotos.contains(photo1), "Album should contain hedgehog.jpg with 'animal' and 'green'");
        assertFalse(albumPhotos.contains(photo2), "Album should not contain bear.jpg");
        assertTrue(albumPhotos.contains(photo3), "Album should contain butterfly.jpg with 'flower'");
    }
}
