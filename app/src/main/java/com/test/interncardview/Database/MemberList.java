package com.test.interncardview.Database;

public class MemberList {
    String fullname;
    String origin;
    String current;
    int number;
    private boolean expandable;

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isExpandable() {
        return expandable;
    }
    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    public MemberList() {
        this.fullname = fullname;
        this.origin = origin;
        this.current = current;
        this.number = number;
    }

    public String getOrigin() {
        return origin;
    }

    public String getCurrent() {
        return current;
    }

    public int getNumber() {
        return number;
    }

    public String getFullname() {
        return fullname;
    }

}
