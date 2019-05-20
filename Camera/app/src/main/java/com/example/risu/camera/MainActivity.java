package com.example.risu.camera;
        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.net.Uri;
        import android.os.Bundle;
        import android.provider.MediaStore;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Base64;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
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


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private Button select_file,upload_file;
    private EditText file_name;
    private ImageView imageView;
    private final  int IMG_REQ=1;
    private Bitmap bitmap;
    private String UploadUrl="http:192.168.61.2/upload.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        select_file=(Button)findViewById(R.id.select);
        upload_file=(Button)findViewById(R.id.upload);
        file_name=(EditText)findViewById(R.id.et_name);
        imageView=(ImageView)findViewById(R.id.iv_image);

        select_file.setOnClickListener(this);
        upload_file.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){

            case R.id.select:
                selectImage();
                break;

            case R.id.upload:
                uploadImage();
                break;
        }
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
                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                imageView.setImageBitmap(bitmap);
                imageView.setVisibility(View.VISIBLE);
                file_name.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    private void uploadImage(){
        StringRequest stringrequest=new StringRequest(Request.Method.POST, UploadUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String Response=jsonObject.getString("response");
                            Toast.makeText(MainActivity.this,Response,Toast.LENGTH_SHORT).show();
                            imageView.setImageResource(0);
                            imageView.setVisibility(View.GONE);
                            file_name.setText("");
                            file_name.setVisibility(View.GONE);
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
                params.put("name",file_name.getText().toString().trim());
                params.put("image",imageToString(bitmap));
                return params;
            }
        };
        MySingleton.getInstance(MainActivity.this).addToRequestQue(stringrequest);

    }
    private String imageToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream =new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgBytes=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes,Base64.DEFAULT);
    }
}