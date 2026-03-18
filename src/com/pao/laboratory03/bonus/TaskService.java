package com.pao.laboratory03.bonus;
import com.pao.laboratory03.bonus.Priority;
import com.pao.laboratory03.bonus.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskService {
    private Map<String, Task> tasksById = new HashMap<>();
    private Map<Priority, List<Task>> tasksByPriority = new HashMap<>();
    private List<String> audiLog = new ArrayList<>();
    private static TaskService instanta = null;
    private int cnt = 1;

    private TaskService(){
    }

    public static TaskService getInstanta(){
        if(instanta == null) instanta = new TaskService();
        return instanta;
    }

    public Task addTask(String title, Priority priority){
        String id = String.format("T%03d", cnt++);
        if(this.tasksById.containsKey(id)) throw new DuplicateTaskException();
        Task t = new Task(id, title, priority);
        tasksById.put(id, t);
        List<Task> listaPrioritati = tasksByPriority.get(priority);
    }
}
