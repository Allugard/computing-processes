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
        allocator.memoryAlloc(1);
        allocator.memoryAlloc(83);
        allocator.memoryAlloc(1);
//        allocator.dump();

        System.out.println();
        allocator.memoryFree(3);

        System.out.println();
//        allocator.dump();

        allocator.memoryFree(93);
        System.out.println();
//        allocator.dump();
        System.out.println();
        allocator.memoryFree(7);

        allocator.dump();
        allocator.print();

    }
}
