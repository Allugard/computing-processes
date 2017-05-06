
package lab2;

import java.util.*;

/**
 * Created by allugard on 05.05.17.
 */
public class Allocator {
    private int [] memory;
    private ArrayList <Integer> pages = new ArrayList<>();
    private LinkedList<Integer> freePages = new LinkedList<>();
    private Map<Integer, Integer> blockPages = new HashMap<>();
    /**
     * first pos - free(0)/page in block(1)/page with blocks(2)
     * second - next page(1)/number of free blosk (2)
     * third - position of next free block(2)
     */
    private static final int HEADER = 3;
    private final int pageSize;

    public Allocator(int mem, int pages) {
//        this.pages = new int[pages];
        this.memory = new int[(mem+HEADER)*pages];
        for (int i = 0; i <pages; i++) {
            this.pages.add(i*(mem+HEADER));
            freePages.add(this.pages.get(i));
        }
        pageSize = mem;
    }

    public int alloc(int size){
        if(size<=pageSize/2){
            if(blockPages.containsKey(size) && memory[blockPages.get(size)+1]!=0){
                int position = blockPages.get(size);
                memory[position+1]--;
                int buf = memory[position + memory[position+2]];
                int pos = position + memory[position+2];
                memory[position+2] = buf;
                return pos;
            }else {
                int position = freePages.getFirst();
                freePages.removeFirst();
                blockPages.put(size, position);
                int numBlocks = pageSize/size;
                memory[position] = numBlocks;
                memory[position+1] = numBlocks - 1;
                memory[position+2] = HEADER + size;
                for (int i = 1; i < numBlocks - 1; i++) {
                    memory[position+HEADER + i*size] = HEADER + (i+1)*size;
                }

                return position+HEADER;
            }
        }else {
            int numPages = size/pageSize + 1;
            int position = freePages.getFirst();
            freePages.removeFirst();
            for (int i = 0; i < numPages; i++) {
                int nextPos = freePages.getFirst();
                freePages.removeFirst();
                memory[position] = 1;
                if(i != numPages - 1) {
                    memory[position + 1] = pages.indexOf(nextPos);
                    position = nextPos;
                }

            }
            return position;
        }
    }

    public void free(){
        pages.contains(5);
    }

    public void dump(){
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Allocator{" +
                "memory=" + Arrays.toString(memory) +
                '}';
    }
}