package by.buglak.fitflow.ui.training.common

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

companion object {
    public var DATABASE_VERSION = 1
    public var DATABASE_NAME = "stateDB"
    public var TABLE_STATE = "stateDB"
    public var KEY_ID = "_id"
    public var KEY_EXACT = "exact"
    public var KEY_FINISHED = "finished"
    public var KEY_DATE = "date"
    public var USER_INFO = "userDB"
    public var KEY_GENDER = "gender"
    public var KEY_WEIGHT_KG = "weightKG"
    public var KEY_WEIGHT_PD = "weightPD"
    public var KEY_HEIGHT_SM = "heightSM"
    public var KEY_HEIGHT_FT = "heightFT"
    public var KEY_WEIGHT_SYSTEM = "weightSystem"
    public var PROGRESS_NAME = "progressDB"
    public var KEY_FULL_FIN = "fullFinished"
    public var KEY_FULL_LAST = "fullLast"
    public var KEY_SIT_FIN = "sitFinished"
    public var KEY_SIT_LAST = "sitLast"
    public var KEY_PUSH_FIN = "pushFinished"
    public var KEY_PUSH_LAST = "pushLast"
    public var KEY_SQT_FIN = "sqtFinished"
    public var KEY_SQT_LAST = "sqtLast"
    public var KEY_PULL_FIN = "pullFinished"
    public var KEY_PULL_LAST = "pullLast"
}



    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "create table " + TABLE_STATE + "(" + KEY_ID + " integer primary key,"
                    + KEY_EXACT + " text," + KEY_FINISHED + " integer," + KEY_DATE + " text" + ");"
        )
        db?.execSQL(
            "create table " + USER_INFO + "(" + KEY_ID + " integer primary key,"
                    + KEY_GENDER + " text," + KEY_HEIGHT_SM + " text," + KEY_HEIGHT_FT
                    + " text," + KEY_WEIGHT_SYSTEM + " text," + KEY_WEIGHT_KG + " text," + KEY_WEIGHT_PD + " text" + ");"
        )
        db?.execSQL(
            "create table " + PROGRESS_NAME + "(" + KEY_ID + " integer primary key,"
                    + KEY_FULL_FIN + " text," + KEY_FULL_LAST + " text," + KEY_SIT_FIN + " text," +
                    KEY_SIT_LAST + " text," + KEY_PUSH_FIN + " text," + KEY_PUSH_LAST
                    + " text," + KEY_SQT_FIN + " text," + KEY_SQT_LAST + " text," + KEY_PULL_FIN + " text," +
                    KEY_PULL_LAST + " text" +");"
        )
    }

    override fun onUpgrade(db1: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
db1.execSQL("drop table if exists " + TABLE_STATE)
db1.execSQL("drop table if exists " + USER_INFO)
db1.execSQL("drop table if exists " + PROGRESS_NAME)
        onCreate(db1)
    }
}