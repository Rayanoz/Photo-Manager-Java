package PhotoApp;

public class BST<T> {
    BSTNode<T> root, current;

    public enum Relative {
        Root, Parent, LeftChild, RightChild
    }

    /** Creates a new instance of BST */
    public BST() {
        root = current = null;
    }

    public boolean empty() {
        return root == null;
    }

    public boolean full() {
        return false;
    }

    public T retrieve() {
        return current.data;
    }

    public boolean update(String key, T data) {
        remove_key(current.key);
        return insert(key, data);
    }

    public void deleteSubtree() {
        if (current == root) {
            current = root = null;
        } else {
            BSTNode<T> p = current;
            find(Relative.Parent);
            if (current.left == p)
                current.left = null;
            else
                current.right = null;
            current = root;
        }
    }

    public boolean find(Relative rel) {
        switch (rel) {
            case Root: // Easy case
                current = root;
                return true;
            case Parent:
                if (current == root)
                    return false;
                current = findparent(current, root);
                return true;
            case LeftChild:
                if (current.left == null)
                    return false;
                current = current.left;
                return true;
            case RightChild:
                if (current.right == null)
                    return false;
                current = current.right;
                return true;
            default:
                return false;
        }
    }

    private BSTNode<T> findparent(BSTNode<T> p, BSTNode<T> t) {
        if (t == null)
            return null; // empty tree
        if (t.right == null && t.left == null)
            return null;
        else if (t.right == p || t.left == p)
            return t; // parent is t
        else {
            BSTNode<T> q = findparent(p, t.left);
            if (q != null)
                return q;
            else
                return findparent(p, t.right);
        }
    }

    public boolean findkey(String tkey) {
        BSTNode<T> p = root, q = root;
        if (empty())
            return false;
        while (p != null) {
            q = p;
            if (p.key.equals(tkey)) {
                current = p;
                return true;
            } else if (tkey.compareTo(p.key) < 0)
                p = p.left;
            else
                p = p.right;
        }
        current = q;
        return false;
    }

    public boolean insert(String k, T val) {
        BSTNode<T> p, q = current;
        if (findkey(k)) {
            current = q; // findkey() modified current
            return false; // key already in the BST
        }
        p = new BSTNode<T>(k, val);
        if (empty()) {
            root = current = p;
            return true;
        } else {
            // current is pointing to parent of the new key
            if (k.compareTo(current.key) < 0)
                current.left = p;
            else
                current.right = p;
            current = p;
            return true;
        }
    }

    public boolean remove_key(String tkey) {
        boolean removed = false;
        BSTNode<T> p;
        p = remove_aux(tkey, root, removed);
        current = root = p;
        return removed;
    }

    private BSTNode<T> remove_aux(String key, BSTNode<T> p, boolean flag) {
        BSTNode<T> q, child = null;
        if (p == null)
            return null;
        if (key.compareTo(p.key) < 0)
            p.left = remove_aux(key, p.left, flag);
        else if (key.compareTo(p.key) > 0)
            p.right = remove_aux(key, p.right, flag);
        else {
            flag = true;
            if (p.left != null && p.right != null) {
                q = find_min(p.right);
                p.key = q.key;
                p.data = q.data;
                p.right = remove_aux(q.key, p.right, flag);
            } else {
                if (p.right == null) // one child
                    child = p.left;
                else if (p.left == null) // one child
                    child = p.right;
                return child;
            }
        }
        return p;
    }

    private BSTNode<T> find_min(BSTNode<T> p) {
        if (p == null)
            return null;
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

    public class BSTNode<T> {
        public String key;
        public T data;
        public BSTNode<T> left, right;

        /** Creates a new instance of BSTNode */
        public BSTNode(String k, T val) {
            key = k;
            data = val;
            left = right = null;
        }

        public BSTNode(String k, T val, BSTNode<T> l, BSTNode<T> r) {
            key = k;
            data = val;
            left = l;
            right = r;
        }
    }
}