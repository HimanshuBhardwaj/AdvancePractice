package com.himanshu.practice.july.july21;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by himanshubhardwaj on 21/07/19.
 */
public class Implementation {
    public static void main(String[] args) {
        SnapShotMap<String, String> stringStringSnapShotMap = new SnapShotMapImpl<>();

        stringStringSnapShotMap.put("Himanshu", "1");
        stringStringSnapShotMap.put("Himanshu1", "1");
        stringStringSnapShotMap.put("Himanshu2", "1");
        stringStringSnapShotMap.put("Himanshu3", "1");
        stringStringSnapShotMap.put("Himanshu4", "1");
        stringStringSnapShotMap.snapshot();
        stringStringSnapShotMap.put("Himanshu4", "2");
        stringStringSnapShotMap.snapshot();
        stringStringSnapShotMap.snapshot();
        stringStringSnapShotMap.snapshot();
        stringStringSnapShotMap.put("Himanshu4", "3");

        System.out.println(stringStringSnapShotMap.get("Himanshu4"));
        System.out.println(stringStringSnapShotMap.snapshotGet("Himanshu4", 1));


    }
}


//keep staring question unless you understand it correctly
interface SnapShotMap<K, V> {
    V get(K key);

    void put(K key, V value);

    int snapshot();

    V snapshotGet(K key, int snapshot);

}

class SnapShotMapImpl<K, V> implements SnapShotMap<K, V> {
    ArrayList<TreeMap<K, V>> snapshotVersions;
    int currentVersion;

    public SnapShotMapImpl() {
        this.currentVersion = 0;
        snapshotVersions = new ArrayList();
        snapshotVersions.add(new TreeMap<K, V>());
    }

    @Override
    public V get(K key) {
        for (int i = currentVersion; i >= 0; i--) {
            if (snapshotVersions.get(i).containsKey(key)) {
                return snapshotVersions.get(i).get(key);
            }
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        snapshotVersions.get(currentVersion).put(key, value);
    }

    @Override
    public int snapshot() {
        snapshotVersions.add(new TreeMap<K, V>());
        currentVersion++;
        return currentVersion - 1;
    }

    @Override
    public V snapshotGet(K key, int snapshot) {
        if (snapshot <= currentVersion) {
            for (int i = snapshot; i >= 0; i--) {
                if (snapshotVersions.get(i).containsKey(key)) {
                    return snapshotVersions.get(i).get(key);
                }
            }
        }
        return null;
    }
}