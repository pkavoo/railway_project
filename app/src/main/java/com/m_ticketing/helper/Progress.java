package com.m_ticketing.helper;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by chris on 10/21/2016.
 */
public class Progress {private Context context;
    private String Title;
    private String Message;
    private ProgressDialog progressDialog;
    public Progress(Context context, String Title, String Message)
    {
        this.context=context;
        this.Title=Title;
        this.Message=Message;
        progressDialog=new ProgressDialog(context);
        progressDialog.setTitle(Title);
        progressDialog.setMessage(Message);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCanceledOnTouchOutside(false);
    }
    public void ShowDialogue(){
        if (!progressDialog.isShowing())
        progressDialog.show();
    }
    public void DismisDialogue(){
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }
    public void setMessage(String message){
        progressDialog.setMessage(message);
    }
}
