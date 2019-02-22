package com.example.intent.item;

public class RecyclerItemForm {

    private String msg = "";
    private int img ;

    //생성자랑 getter&setter 생성
    public RecyclerItemForm(String msg, int img) {
        this.msg = msg;
        this.img = img;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
