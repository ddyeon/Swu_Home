package com.example.swu_home.activity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블 생성
        /* 이름은 MONEYBOOK이고, 자동으로 값이 증가하는 _id 정수형 기본키 컬럼과
        item 문자열 컬럼, price 정수형 컬럼, create_at 문자열 컬럼으로 구성된 테이블을 생성. */
        db.execSQL("CREATE TABLE PHONEBOOK (_id INTEGER PRIMARY KEY AUTOINCREMENT, number TEXT, name TEXT, mobile TEXT);");
    }

    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){

    }


    public void insert(String number, String name, String mobile) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO PHONEBOOK VALUES(null,'" + number + "', '"+ name + "', '" + mobile + "');");
        db.close();
    }


    public void delete(String name) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
        db.execSQL("DELETE FROM PHONEBOOK WHERE name='" + name + "';");
        db.close();
    }

    public String getResult() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM PHONEBOOK", null);
        while (cursor.moveToNext()) {
            result += " 사용자 지정번호 : "
                    + cursor.getString(1)
                    + " | 이름 : "
                    + cursor.getString(2)
                    + " | 전화번호 : "
                    + cursor.getString(3)
                    + "\n ";

        }

        return result;
    }
}


