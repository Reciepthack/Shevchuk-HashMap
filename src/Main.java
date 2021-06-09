public class Main {
    public static void main(String[] args) {

        HashMapOpenAddressing<Integer, Long> hashMap = new HashMapOpenAddressing<>();

        hashMap.put(1, 2L);
        hashMap.put(2, 1L);
        hashMap.put(3, 3L);
        hashMap.put(4, 6L);
        hashMap.put(5, 3L);
        hashMap.put(6, 5L);
        hashMap.put(7, 4L);
        hashMap.put(8, 5L);
        System.out.println("Keys " + hashMap.keys() + "\n" + "Values " + hashMap.values());
        System.out.println("Get element :" + hashMap.get(5));
        System.out.println("Get size :" + hashMap.size());
    }
}
