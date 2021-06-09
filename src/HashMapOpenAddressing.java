import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HashMapOpenAddressing<K extends Comparable<K>, V> {

    private class Node {
        public K key;
        public V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private List<Node> arr;
    private int numKeys;
    private int size;

    public HashMapOpenAddressing() {
        size = 16;
        arr = new ArrayList<>(Collections.nCopies(size, null));
        numKeys = 0;
    }

    public V get(K key) {
        int index = Math.abs(key.hashCode()) % size;
        Node n;
        for (int i = index; ; i = (i + 1) % size) {
            n = arr.get(i);
            if (n == null)
                return null;
            else if (key.compareTo(n.key) == 0)
                return n.value;
        }
    }

    public void put(K key, V value) {
        int index = Math.abs(key.hashCode()) % size;
        Node n;
        for (int i = index; ; i = (i + 1) % size) {
            n = arr.get(i);
            if (n == null) {
                Node temp = new Node(key, value);
                arr.set(i, temp);
                numKeys++;
                break;
            }
        }
        double loadFactor = 0.5;
        if ((double) numKeys / size > loadFactor) {
            this.reinitialize();
        }
    }

    private void reinitialize() {
        int nsize = size * 2;
        List<Node> nArr = new ArrayList<>(Collections.nCopies(nsize, null));
        Node temp;

        for (int i = 0; i < size; i++) {
            temp = arr.get(i);
            if (temp != null) {
                K key = temp.key;
                int index = Math.abs(key.hashCode()) % nsize;
                for (int j = index; ; j = (j + 1) % nsize) {
                    Node n = nArr.get(j);
                    if (n == null) {
                        nArr.set(j, temp);
                        break;
                    }
                }
            }
        }
        this.size = nsize;
        this.arr = nArr;
    }

    public int size() {
        return numKeys;
    }

    public List<K> keys() {
        List<K> keylist = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Node n = arr.get(i);
            if (n != null)
                keylist.add(n.key);
        }
        return keylist;
    }

    public List<V> values() {
        List<V> valuelist = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Node n = arr.get(i);
            if (n != null)
                valuelist.add(n.value);
        }
        return valuelist;
    }

}
