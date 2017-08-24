package com.learn.design;

class MyEntry<K, V> {
    private K key;
    private V val;

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getVal() {
        return val;
    }

    public void setVal(V val) {
        this.val = val;
    }

}

public class MyHashMap<K, V> {
    private float loadFactor = 0.75f;
    private int capacity = 100;
    private int size = 0;
    private MyEntry<K, V>[] table = new MyEntry[capacity];

    private int hashing(Object key) {
        if (key == null) {
            return 0;
        } else {
            return key.hashCode() % capacity;
        }
    }

    public boolean containsKey(Object key) {
        if (size == 0) return false;
        if (key == null) {
            if (table[0].getKey() == null) {
                return true;
            }
        }
        int index = hashing(key);
        MyEntry entry = table[index];
        if (entry != null && entry.getKey().equals((K) key)) {
            return true;
        }
        return false;
    }

    public boolean containsValue(Object val) {
        for (int i = 0; i < size; i++) {
            if (table[i] != null && table[i].getVal().equals((V) val)) {
                return true;
            }
        }
        return false;
    }

    public V put(K key, V val) {
        if (key == null) {
            return putNullKey(val);
        }
        int index = hashing(key);
        MyEntry entry = table[index];
        if (entry != null && entry.getKey().equals(key)) {
            entry.setVal(val);
        } else {
            MyEntry<K, V> newEntry = new MyEntry<K, V>();
            newEntry.setKey(key);
            newEntry.setVal(val);
            table[index] = newEntry;
            size++;
        }
        return val;
    }

    private V putNullKey(V val) {
        MyEntry entry = table[0];
        if (entry != null && entry.getKey() == null) {
            entry.setVal(val);
        } else {
            MyEntry<K, V> newEntry = new MyEntry<>();
            newEntry.setKey(null);
            newEntry.setVal(val);
            table[0] = newEntry;
            size++;
        }
        return val;
    }

    public V get(K key) {
        V res = null;
        if (key == null) {
            if (table[0] != null && table[0].getKey() == null) {
                res =  table[0].getVal();
            }
        } else {
            int index = hashing(key);
            MyEntry<K, V> entry = table[index];
            if (entry != null) {
                res = entry.getVal();
            }
        }
        return res;
    }

    public V remove(K key) {
        V res = null;
        int index = hashing(key);
        if (table[index].getKey().equals((K) key)) {
            res = table[index].getVal();
            for (int i = index; i < size; i++) {
                table[i] = table[i + 1];
            }
        }
        size--;
        return res;
    }
}
