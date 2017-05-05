package lab2;

import java.util.*;

/**
 * Created by allugard on 05.05.17.
 */
public class Allocator {
    private int [] memory;
    private int [] pages;
    private LinkedList<Integer> freePages = new LinkedList<>();
    private Map<Integer, Integer> blockPages = new HashMap<>();
    private static final int HEADER = 3;
    private final int pageSize;

    public Allocator(int mem, int pages) {
        this.pages = new int[pages];
        this.memory = new int[(mem+HEADER)*pages];
        for (int i = 1; i <pages; i++) {
            this.pages[i] = i*(mem+HEADER);
            freePages.add(this.pages[i]);
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
                memory[pos] = 1;
                memory[position+2] = buf;
                return pos;
            }else {
                int position = freePages.getFirst();
                freePages.removeFirst();
                blockPages.put(size, position);
                int numBlocks = pageSize/size;
                for (int i = 0; i < numBlocks; i++) {

                }

                return position;
            }
        }else {
            return 0;
        }
    }

}