package net.macdidi.myandroidtutorial;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.os.BaseBundle;

//import android.content.ClipData.Item;

import android.content.Intent;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    private ListView item_list;
    private TextView show_app_name;

    // 換掉原來的字串陣列
    private ArrayList data = new ArrayList();
    private ArrayAdapter adapter;
    
    private ArrayList content = new ArrayList();
    
    // 儲存所有記事本的List物件
    private List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Toast.makeText(this, "MainActivity onCreate start", Toast.LENGTH_LONG).show();
        
        // 一定要加入呼叫父類別onCreate方法的敘述
        super.onCreate(savedInstanceState);
        // 指定這個元件使用的畫面配置資源
        setContentView(R.layout.activity_main);

        processViews();
        processControllers();
        
        // 加入範例資料
        data.add("吳寶春");
        data.add("天仁茗茶");
        data.add("Starbucks");
        data.add("Smith&Hsu tea");
        
        content.add("麵包");
        content.add("茶");
        content.add("咖啡");
        content.add("司康");
        
        /*
        //items = new ArrayList<Item>();
        items.add(new Item(1, new Date().getTime(), Colors.GREEN, "吳寶春", "麵包", "", 0, 0, 0));
        items.add(new Item(2, new Date().getTime(), Colors.GREEN, "天仁茗茶", "茶", "", 0, 0, 0));
        items.add(new Item(3, new Date().getTime(), Colors.GREEN, "Starbucks", "咖啡", "", 0, 0, 0));
        items.add(new Item(4, new Date().getTime(), Colors.GREEN, "Smith&Hsu tea", "司康", "", 0, 0, 0));
        */
        
        

        int layoutId = android.R.layout.simple_list_item_1;
        adapter = new ArrayAdapter(this, layoutId, data);
        //adapter = new ArrayAdapter(this, layoutId, items);
        item_list.setAdapter(adapter);
        
        //itemAdapter = new ItemAdapter(this, R.layout.single_item, items);
        //item_list.setAdapter(itemAdapter);

        //Toast.makeText(this, "MainActivity onCreate end", Toast.LENGTH_LONG).show();
        //Toast.makeText(this, "items : "+items, Toast.LENGTH_LONG).show();
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	// 如果被啟動的Activity元件傳回確定的結果
    	if (resultCode == Activity.RESULT_OK) {
    		// 讀取標題
    		String titleText = data.getStringExtra("titleText");
    		String contentText = data.getStringExtra("contentText");
 
            // 如果是新增記事
            if (requestCode == 0) {
                // 加入標題項目
                this.data.add(titleText);
                this.content.add(contentText);
                // 通知資料已經改變，ListView元件才會重新顯示
                adapter.notifyDataSetChanged();
            }
            // 如果是修改記事
            else if (requestCode == 1) {
                // 讀取記事編號
                int position = data.getIntExtra("position", -1);
 
                if (position != -1) {
                    // 設定標題項目
                    this.data.set(position, titleText);
                    this.content.set(position, contentText);
                    // 通知資料已經改變，ListView元件才會重新顯示
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }

    private void processViews() {
        item_list = (ListView)findViewById(R.id.item_list);
        show_app_name = (TextView) findViewById(R.id.show_app_name);

        //Toast.makeText(this, "MainActivity processViews end", Toast.LENGTH_LONG).show();
    }

    private void processControllers() {

    	// 建立選單項目點擊監聽物件
        OnItemClickListener itemListener = new OnItemClickListener() {
            // 第一個參數是使用者操作的ListView物件
            // 第二個參數是使用者選擇的項目
            // 第三個參數是使用者選擇的項目編號，第一個是0
            // 第四個參數在這裡沒有用途
        	@Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 使用Action名稱建立啟動另一個Activity元件需要的Intent物件
                Intent intent = new Intent("net.macdidi.myandroidtutorial.EDIT_ITEM");
 
                // 設定記事編號與標題
                intent.putExtra("position", position);
                intent.putExtra("titleText", (String) data.get(position));
                //Toast.makeText(MainActivity.this, "position: " + position, Toast.LENGTH_LONG).show();

                intent.putExtra("contentText", (String) content.get(position));
                //Toast.makeText(MainActivity.this, "contentText: " + (String) content.get(position), Toast.LENGTH_LONG).show();
                
                // 呼叫「startActivityForResult」，第二個參數「1」表示執行修改
                startActivityForResult(intent, 1);
            }
        };

        // 註冊選單項目點擊監聽物件
        item_list.setOnItemClickListener(itemListener);

        
        //=================================================================================

        // 建立選單項目長按監聽物件
        OnItemLongClickListener itemLongListener = new OnItemLongClickListener() {
            // 第一個參數是使用者操作的ListView物件
            // 第二個參數是使用者選擇的項目
            // 第三個參數是使用者選擇的項目編號，第一個是0
            // 第四個參數在這裡沒有用途
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this, "Long: " + data[position], Toast.LENGTH_LONG).show();
                // 換掉「data[position]」
                Toast.makeText(MainActivity.this, "Long: " + data.get(position), Toast.LENGTH_LONG).show();
                
                return false;
            }
        };

        // 註冊選單項目長按監聽物件
        item_list.setOnItemLongClickListener(itemLongListener); 

        // 建立長按監聽物件
        OnLongClickListener listener = new OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder dialog = 
                        new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle(R.string.app_name)
                      .setMessage(R.string.about)
                      .show();
                return false;
            }

        };

        // 註冊長按監聽物件
        show_app_name.setOnLongClickListener(listener);

        //Toast.makeText(this, "MainActivity processControllers end", Toast.LENGTH_LONG).show();
    }

    // 載入選單資源
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        //Toast.makeText(this, "MainActivity onCreateOptionsMenu end", Toast.LENGTH_LONG).show();
        return true;
    }

    public void clickMenuItem(MenuItem item) {
        int itemId = item.getItemId();
        Toast.makeText(this, "MainActivity clickMenuItem ("+itemId+")", Toast.LENGTH_LONG).show();
 /*
        switch (itemId) {
	        case R.id.search_item:
	            break;
	        case R.id.add_item:
	            // 使用Action名稱建立啟動另一個Activity元件需要的Intent物件
	            Intent intent = new Intent("net.macdidi.myandroidtutorial.ADD_ITEM");
	            // 呼叫「startActivityForResult」，，第二個參數「0」表示執行新增
	            startActivityForResult(intent, 0);
	            break;
	        case R.id.revert_item:
	            break;
	        case R.id.delete_item:
	            break;
	        case R.id.googleplus_item:
	            break;
	        case R.id.facebook_item:
	            break;
        }
*/
    }

    // 點擊應用程式名稱元件後呼叫的方法
    public void aboutApp(View view) {
        // 建立啟動另一個Activity元件需要的Intent物件
        // 建構式的第一個參數：「this」
        // 建構式的第二個參數：「Activity元件類別名稱.class」
        Intent intent = new Intent(this, AboutActivity.class);
        // 呼叫「startActivity」，參數為一個建立好的Intent物件
        // 這行敘述執行以後，如果沒有任何錯誤，就會啟動指定的元件
        startActivity(intent);
    }
/*
    @Override
    protected void onStart() {
        //Toast.makeText(MainActivity.this, "MainActivity onStart start", Toast.LENGTH_SHORT).show();
        // TODO Auto-generated method stub
        super.onStart();
        Toast.makeText(MainActivity.this, "MainActivity onStart end", Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onResume() {
        //Toast.makeText(MainActivity.this, "MainActivity onResume start", Toast.LENGTH_SHORT).show();
        // TODO Auto-generated method stub
        super.onResume();
        Toast.makeText(MainActivity.this, "MainActivity onResume end", Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onPause() {
        //Toast.makeText(MainActivity.this, "MainActivity onPause start", Toast.LENGTH_SHORT).show();
        // TODO Auto-generated method stub
        super.onPause();
        Toast.makeText(MainActivity.this, "MainActivity onPause end", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        //Toast.makeText(MainActivity.this, "MainActivity onStop start", Toast.LENGTH_SHORT).show();
        // TODO Auto-generated method stub
        super.onStop();
        Toast.makeText(MainActivity.this, "MainActivity onStop end", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        //Toast.makeText(MainActivity.this, "MainActivity onDestroy start", Toast.LENGTH_SHORT).show();
        // TODO Auto-generated method stub
        super.onDestroy();
        Toast.makeText(MainActivity.this, "MainActivity onDestroy end", Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onRestart() {
        //Toast.makeText(MainActivity.this, "MainActivity onRestart start", Toast.LENGTH_SHORT).show();
        // TODO Auto-generated method stub
        super.onRestart();
        Toast.makeText(MainActivity.this, "MainActivity onRestart end", Toast.LENGTH_LONG).show();
    }
*/
}
