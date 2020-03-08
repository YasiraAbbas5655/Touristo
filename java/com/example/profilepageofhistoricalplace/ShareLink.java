package com.example.profilepageofhistoricalplace;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.facebook.share.widget.ShareDialog;

public class ShareLink extends AppCompatActivity {

    CallbackManager callbackManager;
    ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_share_link);

        callbackManager= CallbackManager.Factory.create();
        shareDialog=new ShareDialog(this);

        ShareLinkContent linkContent= new ShareLinkContent.Builder().setQuote("This is useful link")
                .setContentUrl(Uri.parse("http://youtube.com")).build();
        if (shareDialog.canShow(ShareLinkContent.class))
        {
            shareDialog.show(linkContent);
        }
    }
}
