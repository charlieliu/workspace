package net.macdidi.myandroidtutorial;
 
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;
 
public class ItemActivity extends Activity {
 
    private EditText title_text, content_text;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	//Toast.makeText(this, "ItemActivity onCreate start", Toast.LENGTH_LONG).show();
    	
    	// 一定要加入呼叫父類別onCreate方法的敘述
    	super.onCreate(savedInstanceState);
        // 指定這個元件使用的畫面配置資源
        setContentView(R.layout.activity_item);
 
        processViews();
 
        // 取得Intent物件
        Intent intent = getIntent();
        // 讀取Action名稱
        String action = intent.getAction();
 
        // 如果是修改記事
        if (action.equals("net.macdidi.myandroidtutorial.EDIT_ITEM")) {
            // 接收與設定記事標題
            String titleText = intent.getStringExtra("titleText");
            title_text.setText(titleText);
            String contentText = intent.getStringExtra("contentText");
            content_text.setText(contentText);
        }
        
        //Toast.makeText(this, "ItemActivity onCreate end", Toast.LENGTH_LONG).show();
    }
 
    private void processViews() {
        title_text = (EditText) findViewById(R.id.title_text);
        content_text = (EditText) findViewById(R.id.content_text);
        
    }
 
    // 點擊確定與取消按鈕都會呼叫這個方法
    public void onSubmit(View view) {
        // 確定按鈕
        if (view.getId() == R.id.ok_teim) {
            // 讀取使用者輸入的標題與內容
            String titleText = title_text.getText().toString();
            String contentText = content_text.getText().toString();
 
            // 取得回傳資料用的Intent物件
            Intent result = getIntent();
            // 設定標題與內容
            result.putExtra("titleText", titleText);
            result.putExtra("contentText", contentText);
 
            // 設定回應結果為確定
            setResult(Activity.RESULT_OK, result);
        }
 
        // 結束
        finish();
    }
 
    // 以後需要擴充的功能
    public void clickFunction(View view) {
        int id = view.getId();
        Toast.makeText(this, "ItemActivity clickFunction ("+id+")", Toast.LENGTH_LONG).show();
/*
        switch (id) {
        case R.id.take_picture:
            break;
        case R.id.record_sound:
            break;
        case R.id.set_location:
            break;
        case R.id.set_alarm:
            break;
        case R.id.select_color:
            break;
        }
*/
    }
 /*
    @Override
    protected void onStart() {
        //Toast.makeText(MainActivity.this, "MainActivity onStart start", Toast.LENGTH_SHORT).show();
        // TODO Auto-generated method stub
        super.onStart();
        Toast.makeText(this, "ItemActivity onStart end", Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onResume() {
        //Toast.makeText(MainActivity.this, "MainActivity onResume start", Toast.LENGTH_SHORT).show();
        // TODO Auto-generated method stub
        super.onResume();
        Toast.makeText(this, "ItemActivity onResume end", Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onPause() {
        //Toast.makeText(MainActivity.this, "MainActivity onPause start", Toast.LENGTH_SHORT).show();
        // TODO Auto-generated method stub
        super.onPause();
        Toast.makeText(this, "ItemActivity onPause end", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        //Toast.makeText(MainActivity.this, "MainActivity onStop start", Toast.LENGTH_SHORT).show();
        // TODO Auto-generated method stub
        super.onStop();
        Toast.makeText(this, "ItemActivity onStop end", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        //Toast.makeText(MainActivity.this, "MainActivity onDestroy start", Toast.LENGTH_SHORT).show();
        // TODO Auto-generated method stub
        super.onDestroy();
        Toast.makeText(this, "ItemActivity onDestroy end", Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onRestart() {
        //Toast.makeText(MainActivity.this, "MainActivity onRestart start", Toast.LENGTH_SHORT).show();
        // TODO Auto-generated method stub
        super.onRestart();
        Toast.makeText(this, "ItemActivity onRestart end", Toast.LENGTH_LONG).show();
    }
*/
}