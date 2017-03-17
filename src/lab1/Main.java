package lab1;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by allugard on 26.02.17.
 */
public class Main {
    public static void main(String[] args) {
        Allocator allocator=new Allocator(100);
        allocator.dump();
        allocator.memoryAlloc(10);

        allocator.dump();
        allocator.memoryRealloc(3,4);
        System.out.println();
        allocator.dump();
        allocator.print();

    }
}
