package com.example.tick.inout;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tick on 2016/3/17.
 */
public class InaccountDAO {
    private DBOpenHelper helper;
    private SQLiteDatabase db;
    public InaccountDAO(Context context){
        helper = new DBOpenHelper(context);
    }

    public void add(Tb_inaccount tb_inaccount){
        db=helper.getWritableDatabase();
        db.execSQL("insert into tb_inaccount(_id,money,time,type,handler,mark) values (?,?,?,?,?,?)",new Object[]{
                tb_inaccount.getid(),tb_inaccount.getMoney(),tb_inaccount.getTime(),tb_inaccount.getType(),tb_inaccount.getHandler(),tb_inaccount.getMark()
        });
    }
    public void  update(Tb_inaccount tb_inaccount){
        db=helper.getWritableDatabase();
        db.execSQL("update tb_inaccount set money=?,time=?,type=?,handler=?,mark=?,where _id=?",new Object[]{
                tb_inaccount.getMoney(),tb_inaccount.getTime(),tb_inaccount.getType(),tb_inaccount.getHandler(),tb_inaccount.getMark(),tb_inaccount.getid()
        });
    }
    public Tb_inaccount find(int id){
        db=helper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select _id,money,time,type,handler,mark,from tb_inaccount where _id=?",new String[]{
                String.valueOf(id)});
        if(cursor.moveToNext()){
            return new Tb_inaccount(cursor.getInt(cursor.getColumnIndex("_id")),cursor.getFloat(cursor.getColumnIndex("money")),cursor.getString(cursor.getColumnIndex("time")),
                    cursor.getString(cursor.getColumnIndex("type")),cursor.getString(cursor.getColumnIndex("handler")),cursor.getString(cursor.getColumnIndex("mark")));
        }
        return  null;
    }
    public void detele(Integer... ids) {
        if (ids.length > 0) {// 判断是否存在要删除的id

            StringBuffer sb = new StringBuffer();// 创建StringBuffer对象
            for (int i = 0; i < ids.length; i++) {// 遍历要删除的id集合

                sb.append('?').append(',');// 将删除条件添加到StringBuffer对象中
            }
            sb.deleteCharAt(sb.length() - 1);// 去掉最后一个“,“字符
            db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
            // 执行删除收入信息操作
            db.execSQL("delete from tb_inaccount where _id in (" + sb + ")", (Object[]) ids);
        }
    }

    public List<Tb_inaccount> getScrollData(int start,int count){
        List<Tb_inaccount> tb_inaccount=new ArrayList<Tb_inaccount>();
        db=helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_inaccount limit ?,?", new String[] { String.valueOf(start), String.valueOf(count) });
        while(cursor.moveToNext()){
            tb_inaccount.add(new Tb_inaccount(cursor.getInt(cursor.getColumnIndex("_id")),cursor.getFloat(cursor.getColumnIndex("money")),
                    cursor.getString(cursor.getColumnIndex("time")), cursor.getString(cursor.getColumnIndex("type")),cursor.getString(cursor.getColumnIndex("handler")),cursor.getString(cursor.getColumnIndex("mark"))));
        }
        return tb_inaccount;
    }
    public long getCount(){
        db=helper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select count(_id) from tb_inaccount",null);

        if(cursor.moveToNext()){
            return cursor.getLong(0);
        }
        return  0;
    }
    public int getMaxId(){
        db=helper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select max(_id) from tb_inaccount",null);

        while (cursor.moveToLast()){
            return cursor.getInt(0);
        }
        return 0;
    }
}
