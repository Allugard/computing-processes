package lab1;

import java.util.*;

/**
 * Created by allugard on 26.02.17.
 */
public class Allocator {
    private TreeSet<MemoryBlock> memoryBlocks;
    private TreeSet<MemoryBlock> freeMemoryBlocks;
    private int [] memory;
    private Random r=new Random();
    private int sum;

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


    public int memoryAlloc(int size, boolean fill){
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

        if(fill){
            for (int i = buf.getAddress()+3; i <buf.getAddress()+buf.getSize(); i++) {
                memory[i]=r.nextInt(10);
                sum+=memory[i];
            }
        }

        return buf.getAddress();
    }

    public int memoryFree(int address){
        MemoryBlock current = memoryBlocks.ceiling(new MemoryBlock(0,address));
        memory[address]=0;
        int deletedData=0;

        for (int i = address+3; i <address+memory[address+2]+3; i++) {
            sum-=memory[i];
            deletedData+=memory[i];
            memory[i]=0;
        }

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
            return deletedData;
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
            return deletedData;
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
            return deletedData;
        }

        freeMemoryBlocks.add(current);
        return deletedData;
    }

    public int memoryRealloc(int address, int size){
        MemoryBlock curMemoryBlock=memoryBlocks.ceiling(new MemoryBlock(0,address));
        if (size>memory[address+2]){
            int newAddress=memoryAlloc(size, false);
            if (newAddress>0){
                for (int i = address+3,j=1; i < address+memory[address+2]+3; i++,j++) {
                    memory[newAddress+2+j]=memory[i];
                    sum+=memory[i];
                }
                memoryFree(address);
                return newAddress;
            }
        }else {
            if(memory[address+2]-size<4){
                return address;
            }else {
                int deletedData=0;
               for (int i = address+size+3; i <address+size+6; i++) {
                   sum-=memory[i];
                   deletedData+=memory[i];
                }


                memoryBlocks.remove(curMemoryBlock);
                memory[address+size+3+2]=memory[address+2]-size-3;
                curMemoryBlock.setSize(size+3);
                memory[address+2]=size;
                memory[address+size+3+1]=curMemoryBlock.getSize();
                MemoryBlock newMemoryBlock=new MemoryBlock(memory[address+size+3+2]+3,address+curMemoryBlock.getSize());
                memoryBlocks.add(newMemoryBlock);
                memoryBlocks.add(curMemoryBlock);
                freeMemoryBlocks.add(newMemoryBlock);

                memory[newMemoryBlock.getAddress()+newMemoryBlock.getSize()+1]=newMemoryBlock.getSize();

                if(memory[newMemoryBlock.getAddress()+newMemoryBlock.getSize()]==0){
                    deletedData+=memoryFree(newMemoryBlock.getAddress());
                }
                System.out.println("Wasted data="+deletedData);
            }
        }
        return address;
    }

    void print(){
        for (int i = 0; i <memory.length ; i++) {
            System.out.print(memory[i]+" ");
        }
        System.out.println();
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

    public void checkSum(){
        System.out.println("Checksum="+sum);
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
