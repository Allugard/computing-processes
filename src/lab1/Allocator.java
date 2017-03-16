package lab1;

import java.util.*;

/**
 * Created by allugard on 26.02.17.
 */
public class Allocator {
    private TreeSet<MemoryBlock> memoryBlocks;
    private TreeSet<MemoryBlock> freeMemoryBlocks;
    private int [] memory;

    public Allocator(int size){
        if (size<10){
            System.out.println("Not enough memory");
        }else {
            memory=new int[size];
            memory[0]=1;
            memory[1]=0;
            memory[2]=0;

            memory[size-3]=1;
            memory[size-2]=size-6;
            memory[size-1]=0;

            memory[3]=0;
            memory[4]=3;
            memory[5]=size-6-3;


            freeMemoryBlocks=new TreeSet<>(new MemBlockSizeComparator());
            freeMemoryBlocks.add(new MemoryBlock(size-6,3));

            memoryBlocks=new TreeSet<>(new MemBlockAdrComparator());
            memoryBlocks.add(new MemoryBlock(3,0));
            memoryBlocks.add(new MemoryBlock(size-6,3));
            memoryBlocks.add(new MemoryBlock(3,size-3));


        }
    }


    public int memoryAlloc(int size){
        size+=3;
        MemoryBlock buf=freeMemoryBlocks.ceiling(new MemoryBlock(size,0));
        if (buf==null) return -1;

        freeMemoryBlocks.remove(buf);

        if(!(buf.getSize()-size<1)){
            int nextAddress=buf.getAddress()+size;
            MemoryBlock newMemoryBlock= new MemoryBlock(buf.getSize()-size, nextAddress);
            memory[nextAddress]=0;
            memory[nextAddress+1]=size;
            memory[nextAddress+2]=buf.getSize()-size-3;

            memory[buf.getAddress()+buf.getSize()+1]=buf.getSize()-size;

            freeMemoryBlocks.add(newMemoryBlock);
            memoryBlocks.add(newMemoryBlock);
//            System.out.println(newMemoryBlock);



            memoryBlocks.remove(buf);
            memory[buf.getAddress()+2]=size-3;
            buf.setSize(size);
            memoryBlocks.add(buf);
        }else {
            memory[buf.getAddress()+2]=size-3;
        }

        memory[buf.getAddress()]=1;

//        System.out.println(buf);
        return buf.getAddress();
    }

    public void memoryFree(int address){
//        memoryBlocks.g
    }




    public void dump(){
        System.out.println("Memory Blocks");
        for (MemoryBlock memoryBlock:memoryBlocks
             ) {
            System.out.println(memoryBlock);
        }
        System.out.println("Free Memory Blocks");
        for (MemoryBlock memoryBlock:freeMemoryBlocks
                ) {
            System.out.println(memoryBlock);
        }
    }


    class MemoryBlock{
        private int size;
        private int address;

        public MemoryBlock(int size, int address) {
            this.size = size;
            this.address = address;
        }

        public int getAddress() {
            return address;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public void setAddress(int address) {
            this.address = address;
        }

        @Override
        public String toString() {
            return "MemoryBlock{" +
                    "sizeBlock=" + size +
                    ", address=" + address +
                    ", State=" + memory[address]+
                    ", prevSize=" + memory[address+1]+
                    ", informationSize="+memory[address+2]+
                    '}';
        }
    }
}
