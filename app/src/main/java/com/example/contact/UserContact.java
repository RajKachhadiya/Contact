package com.example.contact;

public class UserContact {

    int id;
    String nam;
    String num;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public UserContact(int id, String nam, String num) {
        this.id = id;
        this.nam = nam;
        this.num = num;
    }

    @Override
    public String toString() {
        return "UserContact{" +
                "id=" + id +
                ", nam='" + nam + '\'' +
                ", num='" + num + '\'' +
                '}';
    }
}
