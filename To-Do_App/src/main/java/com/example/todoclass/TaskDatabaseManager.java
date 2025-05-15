package com.example.todoclass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Shriyash Ghimire
 * since :  May 14th 2025
 * Description : Class used to create a new ArrayList that acts as an database, also stores all the emthod used to manipulate the database.
 */

public class TaskDatabaseManager {

    private final List<String> todoList;


    /**
     * Creates an arraylist that acts as an database to store the tasks
     */

    public TaskDatabaseManager() {
        this.todoList = new ArrayList<>();
    }

    /**
     *
     * @param task:  String value given the method usually from the text field to add it to the todoList arrayList.
     * Adds the given String to the arrayList if the conditions are met.
     */

    public void addTask(String task) {
        try{
            if (task != null && !task.trim().isEmpty()) {
                todoList.add(task.trim());
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    /**
     *
     * @param givenTask :  String value of the task need to be cross-checked in the arrayList.
     * @param givenIndex : Index value of the item.
     * @return :  Boolean value stating if the task exists or not
     */
    //used when you know both string and the index position of the task!
    public boolean  checkIfExists( String givenTask, int givenIndex){
       return (Objects.equals(givenTask, todoList.get(givenIndex)));
    }

    /**
     *
     * @param givenTask :  String value of the task need to be cross-checked in the arrayList/db.
     * @return :  Boolean value stating if the task exists in the arrayList/ db.
     */
    // used when you only know the task you are adding!
    public boolean  checkIfExists( String givenTask){
        return (todoList.contains(givenTask));
    }

    /**
     *
     * @param index: index value of the task that needs to be removed.
     *             Removes rge task in that index of the database/arraylist, as long as it's valid.
     */

    public void removeTask(int index) {
        if (index >= 0 && index < todoList.size()) {
            todoList.remove(index);
        }
    }

    /**
     *
     * @return: Integer value stating how many items are left in the arrayList/ how many tasks are in the todoList arrayList.
     */

    public int num_Tasks_Left() {
        return todoList.size();
    }

    /**
     *
     * @param givenIndex: index value of the task that needs to be updated with the new String value.
     * @param givenTask :  updated new String value that is to be replaced with.
     */

    public void updateTask(int givenIndex, String givenTask){
        todoList.set(givenIndex, givenTask);

    }

    /**
     *
     * @param givenIndex: Index value of the task that needs to be retrieved
     * @return:  the staring value in the index position in the todoList arrayList.
     */

    public String getTaskFromIndex(int givenIndex){
        return todoList.get(givenIndex);
    }

    /**\
     *
     * @return : ArrayList, returns the copy of todoList, to show on the ListView on the home Stage!
     */
    public List<String> getTasks() {
        return new ArrayList<>(todoList); // Returns a copy to prevent external modification,
        // i.e makes sure modifications are only allowed through object calls
    }
}
