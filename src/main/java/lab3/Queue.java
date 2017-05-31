package lab3;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by allugard on 14.05.17.
 */
public class Queue {
    Deque <Task> queue;

    public Queue() {
        queue = new LinkedList<>();
//        System.out.println(queue.size());
    }

    public void addTask(Task task) {
        queue.addLast(task);
    }


    public Task getTask() {
        return queue.pollFirst();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

}