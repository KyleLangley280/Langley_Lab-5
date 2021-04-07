package com.example.langley_lab5a;

public class Contact {
    private int id;
    //private String name,address;
    private String memo;

    public Contact(int id, String memo){
        this.id = id;
        //this.name = name;
        //this.address = address;
        this.memo = memo;
    }

    public Contact(String memo){
        //this.name = name;
        //this.address = address;
        this.memo = memo;
    }

    public int getId(){return id;}
    public void setId(int id){this.id = id;}

    //public String getName(){return name;}
    //public void setName(String name){this.name = name;}

    //public String getAddress(){return address;}
    //public void setAddress(String address){this.address = address;}

    public String getMemo(){return memo;}
    public void setMemo(String memo){this.memo = memo;}

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(id).append(": ").append(memo);
        //s.append("Name: ").append(name).append("\n");
        //s.append("Address: ").append(address).append("\n");
        return s.toString();
    }
}
