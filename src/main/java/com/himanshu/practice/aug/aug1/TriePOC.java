package com.himanshu.practice.aug.aug1;

import java.util.ArrayList;
import java.util.HashMap;
import java.lang.String;


public class TriePOC {


}


class Trie {
    TrieNode root;


    public Trie() {
        root = new TrieNode();
    }


    public void insert(String key, String value) {
        insertHelper(key, 0, root, value);
    }

    private TrieNode insertHelper(String key, int index, TrieNode root, String value) {
        if (index == (key.length() - 1)) {
            if (root == null) {
                root = new TrieNode();
                root.value.add(value);
            } else {
                root.value.add(value);
            }
            return root;
        }

        if (!root.children.containsKey(key.charAt(index))) {
            root.children.put(key.charAt(index), new TrieNode());
        }
        //insertHelper(key,index+1,)
        return null;
    }


    private static class TrieNode {
        ArrayList<String> value;
        HashMap<Character, TrieNode> children;

        public TrieNode() {
            children = new HashMap<>();
        }
    }
}
