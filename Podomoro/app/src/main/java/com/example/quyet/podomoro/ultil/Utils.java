package com.example.quyet.podomoro.ultil;

import com.example.quyet.podomoro.databases.models.Task;

import java.util.List;
import java.util.Random;

/**
 * Created by quyetdv on 2/15/2017.
 */

public class Utils {
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    public String randomId(List<Task> taskList){
        Random random = new Random();
        int len  = 12;
        StringBuilder sb = new StringBuilder( len );
        boolean check = true;
        while(check){
            for( int i = 0; i < len; i++ )
                sb.append( AB.charAt( random.nextInt(AB.length()) ) );
            check = false;
            for (Task t :
                    taskList) {
                if (t.getId().equals(sb.toString())){
                    check = true;
                    break;
                }
            }
        }

        return sb.toString();

    }

}
