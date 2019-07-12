package com.example.student.DataStorage;

/**
 * Created by student on 2017/10/18.
 */
//注意不要把public寫成static，會把屬性值改掉然後指記憶最後一筆資料
public class customer {
   public int id;
    public String name;
    public  String tel;
    public String addr;

    //為了不影響前面程式，要多寫幾個建構式(因為之前程式new物件的時候不用放參數)
    public customer() {
    }

    //為了方便處理資料，加寫建構式，三個參數是用來新增物件用
    public customer(String name, String tel, String addr) {
        this.name = name;
        this.tel = tel;
        this.addr = addr;
    }

    //四個參數是用來讀取資料用
    public customer(int id, String name, String tel, String addr) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.addr = addr;
    }

}
