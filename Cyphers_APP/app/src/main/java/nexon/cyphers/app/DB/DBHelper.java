package nexon.cyphers.app.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=1;
    public DBHelper(Context context)
    {
        super(context,"recentdb",null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="create table recent" +
                "(_id integer primary key autoincrement,"+
                "nickname)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i1==i)
        {
            sqLiteDatabase.execSQL("drop table recent");
            onCreate(sqLiteDatabase);
        }
    }
}
