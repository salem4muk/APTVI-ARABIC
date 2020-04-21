package com.salem4muk.aptvi.arabic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import dmax.dialog.SpotsDialog;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.salem4muk.aptvi.arabic.Common.Common;
import com.salem4muk.aptvi.arabic.model.Channel_Category;
import com.salem4muk.aptvi.arabic.model.Channel_URL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.sql.DataSource;

public class Add_ChannelActivity extends AppCompatActivity {

    Button but_uoload;
    ImageButton but_choose_image;
    MaterialSpinner spinner;
    EditText ed_title,ed_link;
    MaterialSpinner spinner_url;
    String channel_uri_selected;
    Map<String,String> spinnerData = new HashMap<>();
    Map<String, String> spinnerData1 = new HashMap<>();


    Uri filePath;
    String channel_category_selected;
    List<String> listcategory = new ArrayList<>();

    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    private DatabaseReference mDatabase;

    static  final int PERMISSION_REQUEST = 1000;
    String category_id_selected = "";
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add__channel);




        if (Config.ENABLE_RTL_MODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            }
        }




        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();


        //views
        but_choose_image = findViewById(R.id.image);
        but_uoload = findViewById(R.id.btn);
        spinner = findViewById(R.id.spinner_category);
        ed_title = findViewById(R.id.ed_title);
        ed_link = findViewById(R.id.ed_link);
        spinner_url = findViewById(R.id.spinner_url);



        loadChannel_UrlToSpinner();



       // loadChannelCategoryToSpinner();
        loadChannelCategoryToSpinner1();
        but_choose_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseFile();
            }
        });


        but_uoload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (spinner.getSelectedIndex() == 0)
                    Toast.makeText(Add_ChannelActivity.this,"الرجاء أختيار قسم",Toast.LENGTH_LONG).show();
                else if (spinner_url.getSelectedIndex() == 0)
                    Toast.makeText(Add_ChannelActivity.this, "الرجاء أختيار نوع الرابط", Toast.LENGTH_LONG).show();
                else
                    upload();
            }
        });



    }

    private void loadChannel_UrlToSpinner() {

        //add hint to spinner
        //  listcategory.add("أختيار نوع الرابط");


        FirebaseDatabase.getInstance()
                .getReference("Channel_URL")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            Channel_URL item1 = snapshot.getValue(Channel_URL.class);
                            String Key = snapshot.getKey();


                            spinnerData1.put(Key, item1.getName_url());


                        }

                        Object[] valueArray = spinnerData1.values().toArray();
                        List<Object> valueList = new ArrayList<>();
                        valueList.add(0, "أختيار نوع الرابط");
                        valueList.addAll(Arrays.asList(valueArray));
                        spinner_url.setItems(valueList);
                        spinner_url.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

                                Object[] KeyArray = spinnerData1.keySet().toArray();
                                List<Object> KeyList = new ArrayList<>();
                                KeyList.add(0, "URL_Key");
                                KeyList.addAll(Arrays.asList(KeyArray));
                                channel_uri_selected = KeyList.get(position).toString();

                            }
                        });

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


    }

    private void loadChannelCategoryToSpinner1() {

        FirebaseDatabase.getInstance()
                .getReference(Common.STR_CHANNER_CATEGORY)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot:dataSnapshot.getChildren())
                        {

                            Channel_Category item = snapshot.getValue(Channel_Category.class);
                            String Key = snapshot.getKey();


                            spinnerData.put(Key,item.getName());


                        }

                        Object []  valueArray = spinnerData.values().toArray();
                        List<Object>  valueList = new ArrayList<>();
                        valueList.add(0,"الأقسام");
                        valueList.addAll(Arrays.asList(valueArray));
                        spinner.setItems(valueList);
                        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

                                Object []  KeyArray = spinnerData.keySet().toArray();
                                List<Object>  KeyList = new ArrayList<>();
                                KeyList.add(0,"Category_Key");
                                KeyList.addAll(Arrays.asList(KeyArray));
                                category_id_selected = KeyList.get(position).toString();

                            }
                        });

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });








    }

    private void upload() {

        final AlertDialog alertDialog = new SpotsDialog(Add_ChannelActivity.this);
        alertDialog.show();
        alertDialog.setMessage("جاري الرفع");


        final StorageReference reference = storageReference.child(new StringBuilder("Channel_Category/"+channel_category_selected+"/")
                .append(UUID.randomUUID()).toString());

        reference.putFile(filePath)
                .continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {

                        if (!task.isSuccessful())

                            throw task.getException();


                        return reference.getDownloadUrl();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                alertDialog.dismiss();
                Toast.makeText(Add_ChannelActivity.this,""+e.getMessage(),Toast.LENGTH_LONG).show();

            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {

                alertDialog.dismiss();
                if (task.isSuccessful()){

                    Uri uri = task.getResult();
                    saveUrlToDatabase(category_id_selected,channel_uri_selected,uri.toString());

                }else {

                    Toast.makeText(Add_ChannelActivity.this,"failed to upload !",Toast.LENGTH_LONG).show();

                }
            }
        });







    }

    private void saveUrlToDatabase(final String category_id_selected,final String channel_uri_selected, final String link_image) {


        final String title_val = ed_title.getText().toString().trim();
        final String link_val = ed_link.getText().toString().trim();

        final AlertDialog alertDialog = new SpotsDialog(Add_ChannelActivity.this);
        alertDialog.show();
        alertDialog.setMessage("جاري الرفع");

/*
        FirebaseDatabase.getInstance()
                .getReference("Channel_List")
                .push()
                .setValue(new Channel_Category(link_image,channel_category_selected))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        alertDialog.dismiss();
                        Toast.makeText(Add_ChannelActivity.this, "Posted Successfully!!!!!",
                                Toast.LENGTH_SHORT).show();

                    }
                });*/



        mDatabase = FirebaseDatabase.getInstance().getReference().child("Channel_List");

        final DatabaseReference newPost = mDatabase.push();

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                newPost.child("channelname").setValue(title_val);
                newPost.child("channelUrl").setValue(link_val);
                newPost.child("imageUrl").setValue(link_image);
                newPost.child("categoryId").setValue(category_id_selected);
                newPost.child("channelUrltrue").setValue(channel_uri_selected)

                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(Add_ChannelActivity.this, HomeActivity.class));

                        }
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        alertDialog.dismiss();
        Toast.makeText(Add_ChannelActivity.this, "Posted Successfully!!!!!",
                Toast.LENGTH_SHORT).show();


    }





    private void chooseFile() {



        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Image :"),PERMISSION_REQUEST);
    }
/*
    private void loadChannelCategoryToSpinner() {

        //add hint to spinner
        listcategory.add("أختيار قسم القناة");



        FirebaseDatabase.getInstance()
                .getReference("Channel_Category")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){


                            Channel_Category channel = snapshot.getValue(Channel_Category.class);
                            listcategory.add(channel.getName());


                        }

                        spinner.setItems(listcategory);

                        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

                                channel_category_selected = listcategory.get(position);


                            }
                        });

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


    }
    */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == PERMISSION_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){

            filePath = data.getData();

            but_choose_image.setImageURI(filePath);



        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;

            default:
                return super.onOptionsItemSelected(menuItem);
        }
        return true;
    }
}
