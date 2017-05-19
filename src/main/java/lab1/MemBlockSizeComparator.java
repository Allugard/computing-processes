package lab1;

import java.util.Comparator;

/**
 * Created by allugard on 16.03.17.
 */
public class MemBlockSizeComparator implements Comparator <Allocator.MemoryBlock> {
    @Override
    public int compare(Allocator.MemoryBlock o1, Allocator.MemoryBlock o2) {
        if (o1.getSize()>o2.getSize()){
            return 1;
        }
        if (o1.getSize()<o2.getSize()){
            return -1;
        }
        return o1.getAddress()-o2.getAddress();
    }
}
