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
        System.out.println();

        allocator.memoryAlloc(87);
        System.out.println();
        allocator.dump();

        allocator.memoryAlloc(1);
        System.out.println();
        allocator.dump();

    }
}
