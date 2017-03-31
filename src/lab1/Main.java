package lab1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by allugard on 26.02.17.
 */
public class Main {
    public static void main(String[] args) {
        List <Integer> address=new ArrayList<>();
        Allocator allocator=new Allocator(100);
        allocator.dump();

        address.add(allocator.memoryAlloc(10,true));
        allocator.checkSum();
//        allocator.print();
        allocator.memoryRealloc(address.get(0),5);
//        allocator.print();
        allocator.checkSum();



    }
}
