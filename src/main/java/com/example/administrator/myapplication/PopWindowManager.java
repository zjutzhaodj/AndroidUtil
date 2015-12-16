package com.example.administrator.myapplication;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

/**
 * Created by Zhaodj on 2015/12/14.
 */
public class PopWindowManager {
    private PopupWindow mPopupWindow;

    private int mLayoutId;
    private LayoutInflater mLayoutInflater;
    private int mGrivity;
    private int offsetX;
    private int offsetY;

    public PopWindowManager(Context context){
        this(context, Gravity.CENTER);
    }


    public PopWindowManager(Context context,int LayoutId){
        this(context,LayoutId, Gravity.CENTER);
    }



    public PopWindowManager(Context context,int LayoutId,int mGrivity){
       this(context, LayoutId, mGrivity, 0, 0);
    }

    public PopWindowManager(Context context,int LayoutId,int mGrivity, int offsetX, int offsetY){
        mLayoutId=LayoutId;
        mLayoutInflater=LayoutInflater.from(context);

        this.mGrivity=mGrivity;
        this.offsetX=offsetX;
        this.offsetY=offsetY;
        initPopWindow(mLayoutInflater, mLayoutId);
    }

    private void initPopWindow(LayoutInflater mLayoutInflater, int mLayoutId) {
        View view=mLayoutInflater.inflate(mLayoutId,null);
        mPopupWindow=new PopupWindow(view);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setAnimationStyle(R.style.MyPopwindow);
        mPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public void showPopWindow(View parent){

        if(mPopupWindow==null){
            if(mLayoutInflater!=null&& mLayoutId!=0){
                initPopWindow(mLayoutInflater, mLayoutId);
            }else {
                return;
            }
        }
        mPopupWindow.showAtLocation(parent,mGrivity,offsetX,offsetY);
    }

    public void dismisPopWindow(){
        if(mPopupWindow!=null){
            mPopupWindow.dismiss();
            mPopupWindow=null;
        }

    }


}

