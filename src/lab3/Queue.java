package lab3;

import java.util.*;

/**
 * Created by allugard on 14.05.17.
 */
public class Queue {
    List <Deque> queue = new ArrayList<>();

    public Queue() {
        for (int i = 0; i <queue.size(); i++) {
            queue.add(new LinkedList());
        }
    }

    public void addTask(Task task) {
//        if(queue.isEmpty()){
//            queue.add(new ArrayList());
//        }
        queue.get((int)(task.getLength()/Math.log(2))).add(task);
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

}