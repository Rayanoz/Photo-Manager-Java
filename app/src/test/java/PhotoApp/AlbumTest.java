package PhotoApp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class AlbumTest {
    private PhotoManager manager;
    private Album album;
    private LinkedList<Photo> photoList1;
    private LinkedList<Photo> photoList2;

    @Before
    public void setUp() {
        // Set up a mock photo manager with some test photos
        manager = new PhotoManager();
        LinkedList<String> photo1Tags = new LinkedList<>();
        photo1Tags.insert("nature");
        photo1Tags.insert("mountain");
        Photo photo1 = new Photo("path1", photo1Tags);

        LinkedList<String> photo2Tags = new LinkedList<>();
        photo2Tags.insert("nature");
        photo2Tags.insert("river");
        Photo photo2 = new Photo("path2", photo2Tags);

        LinkedList<String> photo3Tags = new LinkedList<>();
        photo3Tags.insert("city");
        photo3Tags.insert("night");
        Photo photo3 = new Photo("path3", photo3Tags);

        // Add photos to the manager
        LinkedList<Photo> managerPhotos = new LinkedList<>();
        managerPhotos.insert(photo1);
        managerPhotos.insert(photo2);
        managerPhotos.insert(photo3);

        while (!managerPhotos.last()) {
            manager.addPhoto(managerPhotos.retrieve());
            managerPhotos.findNext();
        }
        manager.addPhoto(managerPhotos.retrieve());

        // Set up the album with a mock condition
        String condition = "nature AND mountain";
        album = new Album("Test Album", condition, manager);

        // Set up test photo lists for AND operation testing
        photoList1 = new LinkedList<>();
        photoList1.insert(photo1);
        photoList1.insert(photo2);

        photoList2 = new LinkedList<>();
        photoList2.insert(photo1);
        photoList2.insert(photo3);
    }

    // Create an album with a condition
    // album=new Album("Nature Album","nature",manager);

    // // Set up test photo lists for AND operation testing
    // photoList1=new LinkedList<>();photoList1.insert(photo1);photoList1.insert(photo2);

    // photoList2=new LinkedList<>();photoList2.insert(photo1);photoList2.insert(photo3);

    @Test
    public void testConstructor() {
        assertEquals("Nature Album", album.getName());
        assertEquals("nature", album.getCondition());
        assertSame(manager, album.getManager());
        assertEquals(0, album.getNbComps());
    }

    @Test
    public void testGetPhotos() {
        LinkedList<Photo> photos = album.getPhotos();
        assertNotNull(photos);

        // Count photos in the list that match the condition
        int count = 0;
        photos.findFirst();
        while (!photos.last()) {
            count++;
            photos.findNext();
        }
        if (photos.retrieve() != null) {
            count++;
        }

        // Should have 2 photos with the "nature" tag
        assertEquals(2, count);
    }

    @Test
    public void testTagContain() {
        LinkedList<String> tags = new LinkedList<>();
        tags.insert("nature");
        tags.insert("mountain");

        assertTrue(album.tagContain(tags, "nature"));
        assertTrue(album.tagContain(tags, "mountain"));
        assertFalse(album.tagContain(tags, "beach"));
    }

    @Test
    public void testSubset() {
        LinkedList<String> tags = new LinkedList<>();
        tags.insert("nature");
        tags.insert("mountain");
        tags.insert("sunset");

        String[] albumTags1 = { "nature", "mountain" };
        String[] albumTags2 = { "nature", "beach" };
        String[] albumTags3 = { "city" };

        assertTrue(album.subset(tags, albumTags1));
        assertFalse(album.subset(tags, albumTags2));
        assertFalse(album.subset(tags, albumTags3));
    }

    @Test
    public void testANDOperation() {
        LinkedList<Photo> result = album.ANDOperation(photoList1, photoList2);
        assertNotNull(result);

        // Count photos in result
        int count = 0;
        result.findFirst();
        while (!result.last()) {
            count++;
            result.findNext();
        }
        if (result.retrieve() != null) {
            count++;
        }

        // Should have 1 photo in common (photo1)
        assertEquals(1, count);

        // Verify it's the correct photo (path1)
        result.findFirst();
        assertEquals("path1", result.retrieve().getPath());
    }
}
