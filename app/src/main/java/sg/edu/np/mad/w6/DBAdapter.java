package sg.edu.np.mad.w6;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBAdapter extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DB_NAME = "wk6.db";

    public DBAdapter(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE WK6 ( NAME TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS WK6");
        onCreate(db);
    }

    public void insertText(String text)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT INTO WK6 VALUES ('" + text + "')");

        db.close();
    }

    public ArrayList<String> readText()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM WK6", null);
        //db.query("table_name",null,"name=?", new String[]{"test"},"name", null, null);
        //select * from table_name where name=test group by name

        ArrayList<String> result = new ArrayList<>();

        while(cursor.moveToNext())
        {
            result.add(cursor.getString(0));
        }

        cursor.close();
        db.close();

        return result;
    }
}
