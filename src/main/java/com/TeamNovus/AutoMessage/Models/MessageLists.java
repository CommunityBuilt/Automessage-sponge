package com.TeamNovus.AutoMessage.Models;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.scheduler.Task;

import com.TeamNovus.AutoMessage.AutoMessage;
import com.TeamNovus.AutoMessage.Tasks.BroadcastTask;

public class MessageLists {

    private static HashMap<String, MessageList> lists = new HashMap<String, MessageList>();
    private static Task task ;
    public static HashMap<String, MessageList> getMessageLists() {
        return lists;
    }

    public static void setMessageLists(HashMap<String, MessageList> messageLists) {
        lists = messageLists;
    }

    public static MessageList getExactList(String name) {
        return lists.get(name);
    }

    public static MessageList getBestList(String name) {
        for (String key : lists.keySet()) {
            if (key.startsWith(name)) {
                return lists.get(key);
            }
        }
        return null;
    }

    public static String getBestKey(String name) {
        for (String key : lists.keySet()) {
            if (key.startsWith(name)) {
                return key;
            }
        }

        return null;
    }

    public static void setList(String key, MessageList value) {
        if (value == null) {
            lists.remove(key);
        } else {
            lists.put(key, value);
        }

        schedule();
    }

    public static void clear() {
        lists.clear();
    }

    public static void schedule() {
        unschedule();
        
        for (Entry<String, MessageList> entry : lists.entrySet()) {
            MessageList list = lists.get(entry.getKey());
            if(list.isEnabled()){
            	task = Sponge.getScheduler().createTaskBuilder().delay(list.getInterval(), TimeUnit.SECONDS).interval(list.getInterval(), TimeUnit.SECONDS).execute(new BroadcastTask(list)).submit(AutoMessage.plugin);
            }
        }
    }

    public static void unschedule() {
    	if(task != null){
    		task.cancel();
    	}
    }
}
