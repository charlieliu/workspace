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
    	
    	// �@�w�n�[�J�I�s�����OonCreate��k���ԭz
    	super.onCreate(savedInstanceState);
        // ���w�o�Ӥ���ϥΪ��e���t�m�귽
        setContentView(R.layout.activity_item);
 
        processViews();
 
        // ���oIntent����
        Intent intent = getIntent();
        // Ū��Action�W��
        String action = intent.getAction();
 
        // �p�G�O�ק�O��
        if (action.equals("net.macdidi.myandroidtutorial.EDIT_ITEM")) {
            // �����P�]�w�O�Ƽ��D
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
 
    // �I���T�w�P�������s���|�I�s�o�Ӥ�k
    public void onSubmit(View view) {
        // �T�w���s
        if (view.getId() == R.id.ok_teim) {
            // Ū���ϥΪ̿�J�����D�P���e
            String titleText = title_text.getText().toString();
            String contentText = content_text.getText().toString();
 
            // ���o�^�Ǹ�ƥΪ�Intent����
            Intent result = getIntent();
            // �]�w���D�P���e
            result.putExtra("titleText", titleText);
            result.putExtra("contentText", contentText);
 
            // �]�w�^�����G���T�w
            setResult(Activity.RESULT_OK, result);
        }
 
        // ����
        finish();
    }
 
    // �H��ݭn�X�R���\��
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