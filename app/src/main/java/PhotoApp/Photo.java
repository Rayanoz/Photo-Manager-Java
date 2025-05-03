package PhotoApp;

public class Photo {
    String path;
    private LinkedList<String> tags;

    public Photo(String path, LinkedList<String> tags) {
        this.path = path;
        this.tags = tags;
    }

    public String getPath() {
        return path;
    }

    public LinkedList<String> getTags() {
        return tags;
    }

    public BST<String> getTagsBST() {
        BST<String> bst = new BST<>();
        tags.findFirst();
        while (true) {
            String tag = tags.retrieve();
            bst.insert(tag, tag);
            if (tags.last()) {
                break;
            }
            tags.findNext();
        }
        return bst;
    }
}
