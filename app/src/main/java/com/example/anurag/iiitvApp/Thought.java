package com.example.anurag.iiitvApp;

public class Thought {

    private String mName;
    private String mDescription;
    private String mTime;


    public Thought(String name, String desc,String time)
    {
        mName = name;
        mDescription = desc;
        mTime = time;
    }



    public String getmDescription() {

        return mDescription;
    }



    public String getmName() {

        return mName;
    }

    public String getmTime()
    {
        return mTime;
    }


}
