package com.example.myapplication.Models;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;
import java.util.UUID;

public class TitleParent implements ParentObject{

    private List<Object> mChildrenList;
    private UUID _id;
    private String title;



    @Override
    public List<Object> getChildObjectList(){
        return mChildrenList;
    }

    @Override
    public void setChildObjectList(List<Object> list){
        mChildrenList = list;
    }

}
