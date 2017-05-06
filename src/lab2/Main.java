package lab2;

/**
 * Created by allugard on 05.05.17.
 */
public class Main {
    public static void main(String[] args) {
        Allocator allocator = new Allocator(20, 5);
        allocator.alloc(2);
        allocator.alloc(5);
        allocator.dump();
    }
}
