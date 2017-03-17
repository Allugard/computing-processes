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

        if(!(buf.getSize()-size<4)){
            int nextAddress=buf.getAddress()+size;
            MemoryBlock newMemoryBlock= new MemoryBlock(buf.getSize()-size, nextAddress);
            memory[nextAddress]=0;
            memory[nextAddress+1]=size;
            memory[nextAddress+2]=buf.getSize()-size-3;

            memory[buf.getAddress()+buf.getSize()+1]=buf.getSize()-size;

            freeMemoryBlocks.add(newMemoryBlock);
            memoryBlocks.add(newMemoryBlock);

            memoryBlocks.remove(buf);
            memory[buf.getAddress()+2]=size-3;
            buf.setSize(size);
            memoryBlocks.add(buf);
        }

        memory[buf.getAddress()]=1;

        return buf.getAddress();
    }

    public void memoryFree(int address){
        MemoryBlock current = memoryBlocks.ceiling(new MemoryBlock(0,address));
        memory[address]=0;

        if (memory[address+current.getSize()]==0 && memory[address-memory[address+1]]==0){
            MemoryBlock nextFree = memoryBlocks.ceiling(new MemoryBlock(0,address+current.getSize()));
            MemoryBlock prevFree = memoryBlocks.ceiling(new MemoryBlock(0,address-memory[address+1]));

            freeMemoryBlocks.remove(nextFree);
            freeMemoryBlocks.remove(prevFree);
            memoryBlocks.remove(nextFree);
            memoryBlocks.remove(prevFree);
            memoryBlocks.remove(current);

            memory[address-prevFree.getSize()+2]+=current.getSize()+nextFree.getSize();
            prevFree.setSize(memory[address-prevFree.getSize()+2]+3);

            memory[nextFree.getAddress()+nextFree.getSize()+1]=prevFree.getSize();
            memory[nextFree.getAddress()+2]=0;
            memory[nextFree.getAddress()+1]=0;
            memory[current.getAddress()+2]=0;
            memory[current.getAddress()+1]=0;


            memoryBlocks.add(prevFree);
            freeMemoryBlocks.add(prevFree);
            return;
        }

            if (memory[address+current.getSize()]==0){
            MemoryBlock nextFree = memoryBlocks.ceiling(new MemoryBlock(0,address+current.getSize()));

            memory[address+2]+=nextFree.getSize();

            memory[nextFree.getAddress()+2]=0;
            memory[nextFree.getAddress()+1]=0;


            memoryBlocks.remove(nextFree);
            freeMemoryBlocks.remove(nextFree);

            memoryBlocks.remove(current);
            current.setSize(memory[address+2]+3);
            memory[nextFree.getAddress()+nextFree.getSize()+1]=current.getSize();
            memoryBlocks.add(current);
            freeMemoryBlocks.add(current);
            return;
        }

        if (memory[address-memory[address+1]]==0){
            MemoryBlock prevFree = memoryBlocks.ceiling(new MemoryBlock(0,address-memory[address+1]));

            memory[address-prevFree.getSize()+2]+=current.getSize();
            memory[address+current.getSize()+1]+=prevFree.getSize();

            memoryBlocks.remove(current);
            memoryBlocks.remove(prevFree);
            freeMemoryBlocks.remove(prevFree);
            prevFree.setSize(memory[address-prevFree.getSize()+2]+3);
            memoryBlocks.add(prevFree);
            freeMemoryBlocks.add(prevFree);
            return;
        }

        freeMemoryBlocks.add(current);
    }

    public int memoryRealloc(int address, int size){
        MemoryBlock curMemoryBlock=memoryBlocks.ceiling(new MemoryBlock(0,address));
        if (size>memory[address+2]){
            int newAddress=memoryAlloc(size);
            if (newAddress>0){
//                memoryBlocks.remove(curMemoryBlock);
//                memory[address]=0;
//                memory[address+1]=0;
//                memory[address+2]=0;

                memoryFree(address);
                return newAddress;
            }
        }else {
            if(memory[address+2]-size<4){
                return address;
            }else {
                memoryBlocks.remove(curMemoryBlock);
                memory[address+5]=memory[address+2]-size;
                curMemoryBlock.setSize(size+3);
                memory[address+2]=size;
                memory[address+3]=0;
                memory[address+4]=size+3;
                MemoryBlock newMemoryBlock=new MemoryBlock(memory[address+5],address+curMemoryBlock.getSize());
                memoryBlocks.add(newMemoryBlock);
                memoryBlocks.add(curMemoryBlock);
                freeMemoryBlocks.add(newMemoryBlock);

                memory[newMemoryBlock.getAddress()+newMemoryBlock.getSize()+1]=newMemoryBlock.getSize();

            }


        }


        return 1;
    }

    void print(){
        for (int i = 0; i <memory.length ; i++) {
            System.out.print(memory[i]+" ");
        }
    }

    public void dump(){
        System.out.println("Memory Blocks:");
        for (MemoryBlock memoryBlock:memoryBlocks
             ) {
            System.out.println(memoryBlock);
        }
        System.out.println("Free Memory Blocks:");
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
