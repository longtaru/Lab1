package mob.longnd.lab1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "DBTask", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sSQlTask = "CREATE TABLE TASKS(ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT," +
                " CONTENT TEXT, DATE TEXT, TYPE TEXT)";
        db.execSQL(sSQlTask);
        String sSQLInsert ="INSERT INTO TASKS (ID,TITLE,CONTENT,DATE,TYPE) VALUES " +
                 "('1','android','Hoc lap trinh android','11/06/2024','De')," +
                 "('2','php','Hoc lap trinh php','01/12/2024','Kho')," +
                 "('3','python','Hoc lap trinh python','05/08/2024','De');";
                     db.execSQL(sSQLInsert);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion!=oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS TASKS");
            onCreate(db);
        }
    }
}
