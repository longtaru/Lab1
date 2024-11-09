package mob.longnd.lab1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mob.longnd.lab1.database.DBHelper;
import mob.longnd.lab1.model.Task;

public class TaskDAO {
    private final DBHelper dbHelper;
    private SQLiteDatabase database;

    public TaskDAO(Context context) {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public long addTask(Task task) {
        ContentValues values = new ContentValues();
        values.put("TITLE",task.getTitle());
        values.put("CONTENT",task.getContent());
        values.put("DATE",task.getDate());
        values.put("TYPE",task.getType());
        long check = database.insert("TASKS", null, values);
        if ((check <=0)) {
            return -1;
        }
        return 1;
    }

    public long updateTask(Task task) {
        ContentValues values = new ContentValues();
        values.put("ID",task.getId());
        values.put("TITLE",task.getTitle());
        values.put("CONTENT",task.getContent());
        values.put("DATE",task.getDate());
        values.put("TYPE",task.getType());
        long check = database.update("TASKS", values, "ID = ?", new String[]{String.valueOf(task.getId())});
        if ((check <=0)) {
            return -1;
        }
        return 1;
    }

    public long deleteTask(int id) {
        long check = database.delete("TASKS", "ID = ?", new String[]{String.valueOf(id)});
        if ((check <= 0)) {
            return -1;
        }
        return 1;
    }

    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> list = new ArrayList<>();
        database = dbHelper.getReadableDatabase();
        try{
            Cursor cursor = database.rawQuery("select * from TASKS",null);
            if(cursor.getCount() >0){
                cursor.moveToFirst();
                do {
                    list.add(new Task(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)));
                }while (cursor.moveToNext());
            }
        }catch (Exception ex){
            Log.e("Error DB",ex.getMessage());
        }
        return list;
    }
}
