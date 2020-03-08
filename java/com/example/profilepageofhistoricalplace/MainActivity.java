package com.example.profilepageofhistoricalplace;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewFlipper V_Flipper;
    DatabaseReference mDatabaseRef;
    FirebaseAuth mAuth;
    Button btn,ShareOnFacebookButton;
    EditText Comment;
    FirebaseUser user;
    List<MyData> mUploads=new ArrayList<>();;
    RecyclerView recyclerView;
    MyRecyclerViewAdapter mAdapter;
    Query query;
    TextView Description;
    String NameOfPlace,DesOfPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Intent i=getIntent();
        NameOfPlace=i.getStringExtra("PlaceName");
        DesOfPlace=i.getStringExtra("AboutImage");

        query=FirebaseDatabase.getInstance().getReference("Comments").orderByChild("place_Name").equalTo(NameOfPlace);
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("Comments");
        btn=(Button)findViewById(R.id.Post_comment_button);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        Comment = findViewById(R.id.Comment_Section);
        recyclerView= (RecyclerView) findViewById(R.id.RecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Description=findViewById(R.id.Description);
        Description.setText(DesOfPlace);
        ShareOnFacebookButton=findViewById(R.id.ShareOnFacebookButton);




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Comment_Of_User=Comment.getText().toString();
                String UserName=user.getDisplayName();
                MyData temp1=new MyData();
                temp1.setPersonName(UserName);
                temp1.setComment(Comment_Of_User);
                temp1.setPlace_Name(NameOfPlace);
                String uploadID = mDatabaseRef.push().getKey();
                mDatabaseRef.child(uploadID).setValue(temp1);

            }
        });


        ShareOnFacebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,ShareLink.class);
                startActivity(i);

            }
        });


//
//        List<MyData> arr=new ArrayList<>();
//        MyData temp=new MyData();
//        temp.setPersonName("Badshahi");
//        temp.setComment("Achi ha yar");
//        arr.add(temp);
////        String uploadID = mDatabaseRef.push().getKey();
////        mDatabaseRef.child(uploadID).setValue(temp);
//        temp.setPersonName("Badshahi");
//        temp.setComment("Achi ha yar");
//        arr.add(temp);
////        String uploadID1 = mDatabaseRef.push().getKey();
////        mDatabaseRef.child(uploadID1).setValue(temp);
//        temp.setPersonName("Badshahi");
//        temp.setComment("Achi ha yar");
//        arr.add(temp);
//        temp.setPersonName("Badshahi");
//        temp.setComment("Achi ha yar");
//        arr.add(temp);
//
//
//        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(arr);
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);




        query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUploads.clear();
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren())
                {
                    MyData myData=postSnapshot.getValue(MyData.class);
                    mUploads.add(myData);
                }
                mAdapter=new MyRecyclerViewAdapter(mUploads);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        int images[]={R.drawable.lahore,R.drawable.tomb_of_jahangir_pakistan_3123687659};

        V_Flipper=findViewById(R.id.V_Flipper);
        for(int image:images)
        {
            ImageFlipper(image);
        }


    }

    public void ImageFlipper(int Image)
    {
        ImageView i=new ImageView(this);
        i.setBackgroundResource(Image);
        V_Flipper.addView(i);
        V_Flipper.setFlipInterval(4000);
        V_Flipper.setAutoStart(true);
        V_Flipper.setInAnimation(this,android.R.anim.slide_in_left);
        V_Flipper.setOutAnimation(this,android.R.anim.slide_out_right);
    }


}
