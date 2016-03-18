package com.example.tick.inout;

/**
 * Created by tick on 2016/3/17.
 */
public class Tb_inaccount {
    private int _id;
    private float money;
    private String time;
    private String type;
    private String handler;
    private String mark;
    public Tb_inaccount(){
        super();
    }
    public Tb_inaccount(int id,float money,String time,String type,String handler,String mark){
        super();
        this._id=id;
        this.money=money;
        this.time=time;
        this.type=type;
        this.handler=handler;
        this.mark=mark;
    }

    public int getid() {
        return _id;
    }

    public void setid(int id) {
        this._id = id;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
