package com.himanshu.practice.july.july18;

import lombok.ToString;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by himanshubhardwaj on 18/07/19.
 */
public class IterativePostOrderTraversal {
    public static void main(String[] args) {
        Node root = Node.insert(null, 5);
        root = Node.insert(root, 7);
        root = Node.insert(root, 6);
        root = Node.insert(root, 8);
        root = Node.insert(root, 3);
        root = Node.insert(root, 4);
        root = Node.insert(root, 2);

        ArrayList<Node> recursiveArrayList = new ArrayList<>();
        Node.postOrderTraversal(root, recursiveArrayList);
        ArrayList<Node> iterativeArrayList = new ArrayList<>();
        Node.iterativePostOrderTraversal(root, iterativeArrayList);

        for (int i = 0; i < iterativeArrayList.size(); i++) {
            System.out.println(iterativeArrayList.get(i) + "\t" + recursiveArrayList.get(i));
        }

    }
}

@ToString
class Node {
    int value;
    @ToString.Exclude Node left;
    @ToString.Exclude Node right;


    public Node(int value) {
        this.value = value;
    }

    static Node insert(Node root, int value) {
        if (root == null) {
            return new Node(value);
        }

        if (root.value > value) {
            root.left = insert(root.left, value);
        } else {
            root.right = insert(root.right, value);
        }

        return root;
    }


    static void postOrderTraversal(Node root, ArrayList<Node> traversal) {
        if (root == null) {
            return;
        }

        postOrderTraversal(root.right, traversal);
        postOrderTraversal(root.left, traversal);

        traversal.add(root);
    }


    static void iterativePostOrderTraversal(Node root, ArrayList<Node> arrayList) {
        if (root == null) {
            return;
        }


        Stack<Node> mannualStack = new Stack<>();

        if (root.right != null) {
            mannualStack.add(root.right);
        }

        if (root.left != null) {
            mannualStack.add(root.left);
        }


        Stack<Node> returnStack = new Stack<>();
        returnStack.add(root);


        while (!mannualStack.isEmpty()) {
            Node top = mannualStack.pop();
            returnStack.add(top);

            if (top.right != null) {
                mannualStack.add(top.right);
            }


            if (top.left != null) {
                mannualStack.add(top.left);
            }


        }


        while (!returnStack.isEmpty()) {
            arrayList.add(returnStack.pop());

        }


    }


}
