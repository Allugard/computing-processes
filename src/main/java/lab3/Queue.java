package lab3;

import java.util.*;

/**
 * Created by allugard on 14.05.17.
 */
public class Queue {
    List <Deque> queue;

    public Queue() {
        queue = new ArrayList<>();
        for (int i = 0; i <31; i++) {
            queue.add(new LinkedList());
        }
//        System.out.println(queue.size());
    }

    public void addTask(Task task) {
        queue.get((int)(Math.log(task.getLength()/8)/Math.log(2))).addLast(task);
    }


    public Task getTask() {
        if(queue.isEmpty()){
            return null;
        }
        for (Deque<Task>list:queue) {
            if(list.isEmpty()){
                continue;
            }else {
                return list.getFirst();
            }
        }
        return null;
    }
    public Task getTask(double time) {
        if(queue.isEmpty()){
            return null;
        }
        for (Deque<Task>list:queue) {
            if(list.isEmpty()){
                continue;
            }else {
                Task task=list.getFirst();
//                task.addTimeInQueue(time);
                return task;
            }
        }
        return null;
    }

    public boolean isEmpty() {
        /*if(lists.isEmpty()){
            return true;
        }*/
        for (Deque<Task>list:queue) {
            if(list.isEmpty()){
                continue;
            }else {
                return false;
            }
        }
        return true;
    }


    public void next(Task task){
        int i=0;
        for (Deque<Task> list:queue) {
            if(list.remove(task)){
                i=queue.indexOf(list);
                break;
            }
        }
        queue.get(i+1).add(task);
    }


    public void remove(Task task){
        int i=0;
        for (Deque<Task> list:queue) {
            if(list.remove(task)){
                break;
            }
        }
    }


    public int getIndex(Task task) {
        int i=0;
        for (Deque<Task> list:queue) {
            if(list.contains(task)){
                i=queue.indexOf(list);
                break;
            }
        }
        return i;
    }
}