package com.example.myapplication;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class DocUpload extends AppCompatActivity {

    Button selectFile, upload;
    TextView notification;
    EditText email;
    Uri pdfUri;

    FirebaseStorage storage;
    FirebaseDatabase database;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_upload);

        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();

        email = (EditText) findViewById(R.id.emailinput);

        selectFile=findViewById(R.id.selectFile);
        upload=findViewById(R.id.upload);
        notification = findViewById(R.id.notification);

        selectFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ContextCompat.checkSelfPermission(DocUpload.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
                {
                    String fmail = email.getText().toString();
                    if(fmail!=null)
                        selectPdf();
                    else
                        Toast.makeText(DocUpload.this, "Enter an eMail!", Toast.LENGTH_LONG).show();
                }
                else
                    ActivityCompat.requestPermissions(DocUpload.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String em = email.getText().toString();

                if(em==null){
                    email.setError("Please enter an email");
                }

                else if(pdfUri!=null)
                    uploadFile(pdfUri, em);
                else
                    Toast.makeText(DocUpload.this,"Select a File", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void uploadFile(Uri pdfUri, String fmail) {

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading File...");
        progressDialog.setProgressStyle(0);
        progressDialog.show();

        final String fileName=System.currentTimeMillis()+"";
        final StorageReference storageReference=storage.getReference();

        storageReference.child("Uploads").child(fmail).putFile(pdfUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        String url = taskSnapshot.getStorage().getDownloadUrl().toString();
                        DatabaseReference reference = database.getReference();

                        reference.child(fileName).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful())
                                    Toast.makeText(DocUpload.this,"File Successfully Uploaded", Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(DocUpload.this, "File Not Successfully Uploaded", Toast.LENGTH_LONG).show();

                                progressDialog.dismiss();

                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(DocUpload.this, "File Not Successfully Uploaded", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                int currentProgress=(int) (100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(currentProgress);

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode==9 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            selectPdf();
        }
        else
            Toast.makeText(DocUpload.this, "please provide permission...",Toast.LENGTH_LONG).show();
    }

    private void selectPdf() {

        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 86);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==86 && resultCode==RESULT_OK && data!=null)
        {
            pdfUri=data.getData();
            notification.setText("File Selected!");
        }
        else{
            Toast.makeText(DocUpload.this, "please select a file...", Toast.LENGTH_SHORT).show();
        }

    }
}


