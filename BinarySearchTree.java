/*
 Shelby Kaiser
 Novermber 6, 2015
 Purpose: to provide methods to use when handling binary trees. 
 */

import java.io.*;
import java.util.*;

/**
 *
 *
 * @author ShelbyKake
 * @param <E>
 */
public class BinarySearchTree<E extends Comparable<E>> extends AbstractTree<E> {

    /**
     *
     */
    protected TreeNode<E> root;

    /**
     *
     */
    protected int size = 0;

    /**
     *
     */
    protected ArrayList<E> inorder;

    /**
     * Create a default binary tree
     */
    public BinarySearchTree() {
    }

    /**
     * Create a binary tree from an array of objects
     *
     * @param objects
     */
    public BinarySearchTree(E[] objects) {
        for (int i = 0; i < objects.length; i++) {
            insert(objects[i]);
        }
    }

    /**
     * Returns true if the element is in the tree
     *
     * @param e
     * @return
     */
    public boolean search(E e) {
        TreeNode<E> current = root; // Start from the root
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else // element matches current.element
            {
                return true; // Element is found
            }
        }
        return false;
    }

    /**
     * requires: a search key ensures: number of comparisons between search key
     * and tree elements is returned
     *
     * @param e
     * @return
     */
    public int getComparisons(E e) {
        TreeNode<E> current = root;
        int count = 1;

        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                count++;
                current = current.left;

            } else if (e.compareTo(current.element) > 0) {
                count++;
                current = current.right;

            } else {
                return count;
            }
        }
        return count;
    }

    /**
     * Insert element o into the binary tree Return true if the element is
     * inserted successfully. Uses an iterative algorithm
     *
     * @param e
     * @return
     */
    public boolean insert(E e) {
        if (root == null) {
            root = createNewNode(e); // Create a new root
        } else {
            // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else {
                    return false; // Duplicate node not inserted
                }
            }
            // Create the new node and attach it to the parent node
            if (e.compareTo(parent.element) < 0) {
                parent.left = createNewNode(e);
            } else {
                parent.right = createNewNode(e);
            }
        }
        size++;
        return true; // Element inserted
    }

    /**
     *
     * @param e
     * @return
     */
    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<E>(e);
    }

    /**
     * Inorder traversal from the root
     */
    public void inorder() {
        inorder(root);
    }

    /**
     * Inorder traversal from a subtree
     *
     * @param root
     */
    protected void inorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    /**
     * Postorder traversal from the root
     */
    public void postorder() {
        postorder(root);
    }

    /**
     * Postorder traversal from a subtree
     *
     * @param root
     */
    protected void postorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }

    /**
     * Preorder traversal from the root
     */
    public void preorder() {
        preorder(root);
    }

    /**
     * Preorder traversal from a subtree
     *
     * @param root
     */
    protected void preorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);

    }

    /**
     * Inner class tree node
     *
     * @param <E>
     */
    public static class TreeNode<E extends Comparable<E>> {

        E element;
        TreeNode<E> left;
        TreeNode<E> right;

        /**
         *
         * @param e
         */
        public TreeNode(E e) {
            element = e;
        }
    }

    /**
     * Get the number of nodes in the tree
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the root of the tree
     *
     * @return
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     * Returns an ArrayList containing elements in the path from the root
     * leading to the specified element, returns an empty ArrayList if no such
     * element exists.
     *
     * @param e
     * @return
     */
    public ArrayList<E> path(E e) {
        java.util.ArrayList<E> list = new java.util.ArrayList<>();
        TreeNode<E> current = root; // Start from the root
        //implement the code here as in search method.
        if (search(e)) {
            while (current != null) {
                list.add(current.element);
                if (e.compareTo(current.element) < 0) {
                    current = current.left;

                } else if (e.compareTo(current.element) > 0) {
                    current = current.right;

                }
                if (e.equals(current.element)) {
                    list.add(current.element);
                    return list;
                }
            }

            return list;
        }
        return new ArrayList<>();

    }

    /**
     * Returns the number of leaf nodes in this tree, returns 0 if tree is empty
     *
     * @param current
     * @return
     */
    public int getNumberOfLeaves(TreeNode<E> current) {
        //left for you to implement in Lab 7

        if (current == null) {
            return 0;
        }
        if (current.left == null && current.right == null) {
            return 1;
        } else {
            return getNumberOfLeaves(current.left) + getNumberOfLeaves(current.right);
        }
    }

    /**
     * Returns an ArrayList containing all elements in preorder of the specified
     * element’s left sub-tree, returns an empty ArrayList if no such element
     * exists.
     *
     * @param e
     * @return
     */
    public ArrayList<E> leftSubTree(E e) {
        ArrayList<E> leftSubTree = new ArrayList<>();
        TreeNode<E> current = root;
        if (search(e)) {

            while (current != null) {
                if (e.compareTo(current.element) < 0) {

                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {

                    current = current.right;
                } else if (current.element.equals(e)) {
                    current = current.left;
                    preorder(current);
                } else {
                    return leftSubTree;
                }

            }

        }
        return leftSubTree;
    }

    /**
     * Returns an ArrayList containing all elements in preorder of the specified
     * element’s right sub-tree, returns an empty ArrayList if no such element
     * exists.
     *
     * @param e
     * @return
     */
    public ArrayList<E> rightSubTree(E e) {
        ArrayList<E> rightSubTree = new ArrayList<>();
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {

                current = current.left;
            } else if (e.compareTo(current.element) > 0) {

                current = current.right;
            } else if (current.element.equals(e)) {
                current = current.right;
                preorder(current);
            } else {
                return rightSubTree;
            }

        }

        return rightSubTree;
    }

    /**
     * Returns the inorder predecessor of the specified element, returns null if
     * tree is empty or element 'e' is not in the tree.
     *
     * @param e
     * @return
     */
    public E inorderPredecessor(E e) {

        TreeNode<E> current = find(e);
        if (current.left != null) {
            current = current.left;
            while (current.right != null) {
                current = current.right;
            }
            return current.element;

        } else {
            TreeNode<E> parent = root;
            TreeNode<E> predecessor = null;
            while (parent != null) {
                if (current.element.compareTo(parent.element) > 0) {
                    predecessor = parent;
                    parent = parent.right;
                } else {
                    predecessor = parent.left;

                }
            }
            if (predecessor == null) {
                return null;
            }
            return predecessor.element;
        }

    }

    /**
     * Delete an element from the binary tree. Return true if the element is
     * deleted successfully Return false if the element is not in the tree
     *
     * @param e
     * @return
     */
    public boolean delete(E e) {
        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else {
                break; // Element is in the tree pointed by current
            }
        }
        if (current == null) {
            return false; // Element is not in the tree
        }    // Case 1: current has no left children
        if (current.left == null) {
            // Connect the parent with the right child of the current node
            if (parent == null) {
                root = current.right;
            } else {
                if (e.compareTo(parent.element) < 0) {
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }
            }
        } else {
            // Case 2 & 3: The current node has a left child
            // Locate the rightmost node in the left subtree of
            // the current node and also its parent
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }
            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
            } else // Special case: parentOfRightMost == current
            {
                parentOfRightMost.left = rightMost.left;
            }
        }
        size--;
        return true; // Element inserted
    }

    /**
     * Obtain an iterator. Use inorder.
     *
     * @return
     */
    public java.util.Iterator iterator() {
        return inorderIterator();
    }

    /**
     * Obtain an inorder iterator
     *
     * @return
     */
    public java.util.Iterator inorderIterator() {
        return new InorderIterator();

    }

    /**
     *
     * @param e
     * @return
     */
    public TreeNode<E> find(E e) {
        TreeNode<E> current = root; // Start from the root
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else // element matches current.element
            {
                return current; // Element is found
            }
        }
        return null;

    }

    // Inner class InorderIterator
    class InorderIterator implements java.util.Iterator {

        // Store the elements in a list
        private java.util.ArrayList<E> list = new java.util.ArrayList<E>();
        private int current = 0; // Point to the current element in list

        public InorderIterator() {
            inorder(); // Traverse binary tree and store elements in list
        }

        /**
         * Inorder traversal from the root
         */
        private void inorder() {
            inorder(root);
        }

        /**
         * Inorder traversal from a subtree
         */
        private void inorder(TreeNode<E> root) {
            if (root == null) {
                return;
            }
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        /**
         * Next element for traversing?
         */
        public boolean hasNext() {
            if (current < list.size()) {
                return true;
            }
            return false;
        }

        /**
         * Get the current element and move cursor to the next
         */
        public Object next() {
            return list.get(current++);
        }

        /**
         * Remove the current element and refresh the list
         */
        public void remove() {
            delete(list.get(current)); // Delete the current element
            list.clear(); // Clear the list
            inorder(); // Rebuild the list
        }
    }

    /**
     * Remove all elements from the tree
     */
    public void clear() {
        root = null;
        size = 0;
    }

}
