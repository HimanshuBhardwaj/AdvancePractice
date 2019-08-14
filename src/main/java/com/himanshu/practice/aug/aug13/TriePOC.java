package com.himanshu.practice.aug.aug13;

import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TriePOC {
    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("Himanshu", "First Name");
        trie.insert("Himanshu Bhardwaj", "Full Name");
        trie.insert("Sai", "Dev");
        trie.insert("Rajneesh", "Lead");
        trie.insert("Aditya", "Senior dev");
        trie.insert("Aditxa", "Timepass");
        trie.insert("Aditya Kumar", "Senior dev full Name");


        trie.print();

        System.out.println();
        System.out.println(trie);
        System.out.println();
        System.out.println(trie.containsKey("Aditya"));
        System.out.println(trie.containsKey("Adityaaaa"));
        System.out.println(trie.containsKey("Aditxa"));

    }
}

@ToString
class Trie {
    Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(String key, String value) {
        if (!root.children.containsKey(key.charAt(0))) {
            root.children.put(key.charAt(0), new Node());
        }


        insertHelper(key, 0, value, root.children.get(key.charAt(0)));
    }

    private void insertHelper(String key, int index, String value, Node root) {
        if ((index + 1) == key.length()) {
            root.values.add(value);
            return;
        }

        if (!root.children.containsKey(key.charAt(index + 1))) {
            root.children.put(key.charAt(index + 1), new Node());
        }
        insertHelper(key, index + 1, value, root.children.get(key.charAt(index + 1)));
    }


    public void print() {
        for (Map.Entry<Character, Node> entry : root.children.entrySet()) {
            printHelper(entry.getKey().toString(), entry.getValue());
        }
    }

    private void printHelper(String prefix, Node value) {
        if (!value.values.isEmpty()) {
            System.out.println(prefix + "\t" + value.values);
        }

        for (Map.Entry<Character, Node> entry : value.children.entrySet()) {
            printHelper(prefix + entry.getKey().toString(), entry.getValue());
        }
    }

    public boolean containsKey(String key) {
        if (key == null || key.length() == 0) {
            return false;
        }

        return containsKeyHelper(key, 0, root.children.get(key.charAt(0)));
    }

    private boolean containsKeyHelper(String key, int index, Node node) {
        if (node == null) {
            return false;
        }

        if (key.length() - 1 == index) {
            return !node.values.isEmpty();
        }

        return containsKeyHelper(key, index + 1, node.children.get(key.charAt(index + 1)));
    }


//    public String getValue(String key) {
//        if (!containsKey(key)) {
//            return null;
//        }
//
//
//
//
//    }
}


@ToString
class Node {
    ArrayList<String> values;
    HashMap<Character, Node> children;

    public Node() {
        this.values = new ArrayList<>();
        children = new HashMap<>();
    }


}
