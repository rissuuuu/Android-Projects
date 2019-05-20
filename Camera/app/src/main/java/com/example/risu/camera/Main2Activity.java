package com.example.risu.camera;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private Button select_file,upload_file,capture_image;
    private ImageView imageView;
    private final  int IMG_REQ=1;
    Spinner spinner_category;
    private Bitmap bitmap,bitmap_camera;
    private String UploadUrl="http://localhost/upload.php";
    TextView textview,textview2;
    String str;
    EditText et_des;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        select_file=(Button)findViewById(R.id.select);
        upload_file=(Button)findViewById(R.id.upload);
        capture_image=(Button)findViewById(R.id.camera);
        et_des=(EditText)findViewById(R.id.editText2);
        imageView=(ImageView)findViewById(R.id.iv_image);
        textview=(TextView)findViewById(R.id.textView) ;
        textview2=(TextView)findViewById(R.id.textView2) ;


        select_file.setOnClickListener(this);
        upload_file.setOnClickListener(this);
        capture_image.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.complaints){
          Intent intent= new Intent(getApplicationContext(),Basket.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.logout){
            Toast.makeText(getApplicationContext(),"Logout",Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){

            case R.id.select:
                selectImage();

                break;

            case R.id.upload:
                pass();
                uploadImage();
                break;
            case R.id.camera:
                uploadCamera();
        }
    }
    private void uploadCamera() {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,0);
    }


    private void selectImage(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMG_REQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IMG_REQ && resultCode==RESULT_OK && data!=null){
            Uri path=data.getData();
            try {

                bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                imageView.setImageBitmap(bitmap);
                imageView.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if(requestCode==0&& resultCode==RESULT_OK && data!=null){
            try {
                bitmap_camera = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(bitmap_camera);
                imageView.setVisibility(View.VISIBLE);
            } catch (Exception e){
                e.printStackTrace();
            }
            }
    }
    private void pass() {


    }
    private void uploadImage(){
        Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_SHORT).show();
        StringRequest stringrequest=new StringRequest(Request.Method.POST, UploadUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String Response=jsonObject.getString("response");
                            Toast.makeText(Main2Activity.this,Response,Toast.LENGTH_SHORT).show();
                            imageView.setImageResource(0);
                            imageView.setVisibility(View.GONE);
                            et_des.setText("");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params =new HashMap<>();
                //params.put("name",textview.getText().toString()+textview2.getText().toString());
                params.put("image",imageToString(bitmap_camera));
                params.put("image1",imageToString(bitmap));
                params.put("Block",textview.getText().toString());
                params.put("Room_no",textview2.getText().toString());
                params.put("Description",et_des.getText().toString());
                return params;
            }
        };
        MySingleton.getInstance(Main2Activity.this).addToRequestQue(stringrequest);

    }
    private String imageToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream =new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgBytes=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes,Base64.DEFAULT);
    }
}