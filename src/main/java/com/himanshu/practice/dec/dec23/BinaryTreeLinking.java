package com.himanshu.practice.dec.dec23;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Created by himanshubhardwaj on 28/12/19.
 */
public class BinaryTreeLinking {
    public static void main(String[] args) {
        Node root = new Node(10, null, null);
        root = root.insert(root, 7);
        root = root.insert(root, 8);
        root = root.insert(root, 12);
        root = root.insert(root, 11);
        root = root.insert(root, 13);
        System.out.println();
        root.inorderTraversal(root);
        Node head = new DLL().convert(root);
        System.out.println();
//        System.out.println(head.value);

        head.inorderTraversal(head);

    }
}


class DLL {
    Node head;

    Node convert(Node root) {
        if (root == null) {
            return head;
        }
        head = root;
        while (head.left != null) {
            head = head.left;
        }

        link(root, root);
        return head;

    }

    private Node link(Node root, Node parent) {
        if (root == null) {
            return null;
        }


        if (root.left == null && root.right == null) {
            if (parent != null) {
                parent.left = root;
            }
            return root;
        }

        if (root.left != null) {
            Node rightMax = link(root.left, parent);
            rightMax.right = root;
        } else {
            parent.left = root;
        }
        return link(root.right, root);
    }

}

@AllArgsConstructor
class Node {
    int value;
    Node left;
    Node right;

    public Node insert(Node root, int value) {
        if (root == null) {
            return new Node(value, null, null);
        }

        if (root.value > value) {
            root.left = insert(root.left, value);
        } else {
            root.right = insert(root.right, value);
        }

        return root;
    }

    public void inorderTraversal(Node root) {
        if (root == null) {
            return;
        }

        inorderTraversal(root.left);
        System.out.print(root.value + " ");
        inorderTraversal(root.right);
    }
}
