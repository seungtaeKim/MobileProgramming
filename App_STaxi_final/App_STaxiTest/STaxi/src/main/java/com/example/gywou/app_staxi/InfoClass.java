package com.example.gywou.app_staxi;

public class InfoClass {

    public int _id;
    public String name;
    public String contact;
    public String location;

    //생성자
    public InfoClass(){}

    /**
     * 실질적으로 값을 입력할 때 사용되는 생성자(getter and setter)
     * @param _id        테이블 아이디
     * @param name       이름
     * @param contact     전화번호
     * @param location   지역
     */
    public InfoClass(int _id, String name, String contact, String location) {
        this._id = _id;
        this.name = name;
        this.contact = contact;
        this.location = location;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}