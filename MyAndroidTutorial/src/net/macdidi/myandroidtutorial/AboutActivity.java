package net.macdidi.myandroidtutorial;
 
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
 
public class AboutActivity extends Activity {
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	//Toast.makeText(this, "AboutActivity onCreate start", Toast.LENGTH_LONG).show();
    	
    	// �@�w�n�[�J�I�s�����OonCreate��k���ԭz
    	super.onCreate(savedInstanceState);
        // �����������ε{�����D
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // ���w�o�Ӥ���ϥΪ��e���t�m�귽
        setContentView(R.layout.activity_about);
        
        //Toast.makeText(this, "AboutActivity onCreate end", Toast.LENGTH_LONG).show();
    }
 
    // �������s
    public void clickOk(View view) {
        // �I�s�o�Ӥ�k����Activity����
        finish();
    }
/*
    @Override
    protected void onStart() {
        //Toast.makeText(MainActivity.this, "MainActivity onStart start", Toast.LENGTH_SHORT).show();
        // TODO Auto-generated method stub
        super.onStart();
        Toast.makeText(this, "AboutActivity onStart end", Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onResume() {
        //Toast.makeText(MainActivity.this, "MainActivity onResume start", Toast.LENGTH_SHORT).show();
        // TODO Auto-generated method stub
        super.onResume();
        Toast.makeText(this, "AboutActivity onResume end", Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onPause() {
        //Toast.makeText(MainActivity.this, "MainActivity onPause start", Toast.LENGTH_SHORT).show();
        // TODO Auto-generated method stub
        super.onPause();
        Toast.makeText(this, "AboutActivity onPause end", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        //Toast.makeText(MainActivity.this, "MainActivity onStop start", Toast.LENGTH_SHORT).show();
        // TODO Auto-generated method stub
        super.onStop();
        Toast.makeText(this, "AboutActivity onStop end", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        //Toast.makeText(MainActivity.this, "MainActivity onDestroy start", Toast.LENGTH_SHORT).show();
        // TODO Auto-generated method stub
        super.onDestroy();
        Toast.makeText(this, "AboutActivity onDestroy end", Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onRestart() {
        //Toast.makeText(MainActivity.this, "MainActivity onRestart start", Toast.LENGTH_SHORT).show();
        // TODO Auto-generated method stub
        super.onRestart();
        Toast.makeText(this, "AboutActivity onRestart end", Toast.LENGTH_LONG).show();
    }
 */
}