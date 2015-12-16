package com.example.administrator.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

/**
 * Created by Zhaodj on 2015/12/14.
 */
public class DialogManager {
  private Context mContext;
    private Dialog mDialog;
    private int mLayoutid;
    private LayoutInflater mLayoutInflater;
    private boolean Custom;
    private Button positive;
    private Button Negtive;
    private onDialogClickListener clickListener;

    public DialogManager(Context context)
    {
        this(context,0);
    }

    public void setOnDialogClickListener(onDialogClickListener clickListener){
        this.clickListener=clickListener;
    }
    public DialogManager(Context context,int LayoutId)
    {
        mContext=context;
        if(LayoutId==0){
            mLayoutid=R.layout.dialog;
            Custom=true;
        }else {
            mLayoutid=LayoutId;
        }
        mLayoutInflater=LayoutInflater.from(context);
    }

    public  void showDialog(){
        mDialog=new Dialog(mContext);
        View view=mLayoutInflater.inflate(mLayoutid, null);
        mDialog.setContentView(view);
        if(Custom){
            positive= (Button) view.findViewById(R.id.sure);
            positive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onPositiveClick();
                }
            });
            Negtive= (Button) view.findViewById(R.id.no);
            Negtive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onNegtiveClick();
                }
            });
        }
        mDialog.show();

    }

    public void cancleDialog(){
        if(mDialog.isShowing()&&mDialog!=null){
            mDialog.dismiss();
            mDialog=null;
        }
        mDialog.setCanceledOnTouchOutside(true);

    }
    interface onDialogClickListener{
        void onPositiveClick();
        void onNegtiveClick();
    }


}
