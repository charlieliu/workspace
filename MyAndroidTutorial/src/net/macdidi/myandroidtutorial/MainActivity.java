package net.macdidi.myandroidtutorial;

import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.BaseBundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
//import android.view.View.OnLongClickListener.onLongClick();
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
    //private ArrayList data = new ArrayList();
    //private ArrayAdapter adapter;
    
    // ListView使用的自定Adapter物件
    private ItemAdapter itemAdapter;
    // 儲存所有記事本的List物件
    private List<Item> items;
    
    // 選單項目物件
    private MenuItem add_item, search_item, revert_item, share_item, delete_item;
     
    // 已選擇項目數量
    private int selectedCount = 0;

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
        //data.add("吳寶春");
        //data.add("天仁茗茶");
        //data.add("Starbucks");
        //data.add("Smith&Hsu tea");

        //int layoutId = android.R.layout.simple_list_item_1;
        //adapter = new ArrayAdapter(this, layoutId, data);
        //item_list.setAdapter(adapter);

        // 加入範例資料       
        items = new ArrayList<Item>();
     
        //items.add(new Item(1, new Date().getTime(), Colors.RED, "吳寶春.", "Hello content", "", 0, 0, 0));
        items.add(new Item(1, new Date(selectedCount).getTime(), Colors.RED, "吳寶春.", "Hello content", "", 0, 0, 0));
        items.add(new Item(2, new Date(selectedCount).getTime(), Colors.BLUE, "天仁茗茶", "她的名字叫「大熱狗」，又叫\n作「奶嘴」，是一隻非常可愛\n的小狗。", "", 0, 0, 0));
        items.add(new Item(3, new Date(selectedCount).getTime(), Colors.GREEN, "Starbucks", "Hello content", "", 0, 0, 0));
        items.add(new Item(3, new Date(selectedCount).getTime(), Colors.GREEN, "Smith&Hsu tea", "", "", 0, 0, 0));
     
        // 建立自定Adapter物件
        itemAdapter = new ItemAdapter(this, R.layout.single_item, items);
        item_list.setAdapter(itemAdapter);       

        //Toast.makeText(this, "MainActivity onCreate end", Toast.LENGTH_LONG).show();
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	// 如果被啟動的Activity元件傳回確定的結果
    	if (resultCode == Activity.RESULT_OK) {
    		// 讀取標題
    		//String titleText = data.getStringExtra("titleText");
    		Item item = (Item) data.getExtras().getSerializable("net.macdidi.myandroidtutorial.Item");
 
            // 如果是新增記事
            if (requestCode == 0) {
            	/*
                // 加入標題項目
                this.data.add(titleText);
                // 通知資料已經改變，ListView元件才會重新顯示
                adapter.notifyDataSetChanged();
                */
            	// 設定記事物件的編號與日期時間
                item.setId(items.size() + 1);
                item.setDatetime(new Date(resultCode).getTime());
     
                // 加入新增的記事物件
                items.add(item);
     
                // 通知資料改變
                itemAdapter.notifyDataSetChanged();
            }
            // 如果是修改記事
            else if (requestCode == 1) {
            	/*
                // 讀取記事編號
                int position = data.getIntExtra("position", -1);
 
                if (position != -1) {
                    // 設定標題項目
                    this.data.set(position, titleText);
                    // 通知資料已經改變，ListView元件才會重新顯示
                    adapter.notifyDataSetChanged();
                }
                */
            	// 讀取記事編號
                int position = data.getIntExtra("position", -1);
     
                if (position != -1) {
                    // 設定修改的記事物件
                    items.set(position, item);
                    itemAdapter.notifyDataSetChanged();
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
        	/*
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
                //intent.putExtra("titleText", (String) data.get(position));
 
                // 呼叫「startActivityForResult」，第二個參數「1」表示執行修改
                //startActivityForResult(intent, 1);
                
                // 讀取選擇的記事物件
                Item item = itemAdapter.getItem(position);
     
                // 如果已經有勾選的項目
                if (selectedCount > 0) {
                    // 處理是否顯示已選擇項目 
                    processMenu(item);
                    // 重新設定記事項目
                    itemAdapter.set(position, item);
                }
                else {                  
                    //Intent intent_179 = new Intent( "net.macdidi.myandroidtutorial.EDIT_ITEM");
     
                    // 設定記事編號與記事物件
                    intent.putExtra("position", position);
                    intent.putExtra("net.macdidi.myandroidtutorial.Item", item);
     
                    startActivityForResult(intent, 1);
                }
            }
            */
        	@Override
            public void onItemClick(AdapterView<?> parent, View view, 
                    int position, long id) {
                // 讀取選擇的記事物件
                Item item = itemAdapter.getItem(position);
         
                Intent intent = new Intent("net.macdidi.myandroidtutorial.EDIT_ITEM");
         
                // 設定記事編號與記事物件
                intent.putExtra("position", position);
                intent.putExtra("net.macdidi.myandroidtutorial.Item", item);
         
                startActivityForResult(intent, 1);              
            }
        };

        // 註冊選單項目點擊監聽物件
        item_list.setOnItemClickListener(itemListener);

        
        //=================================================================================
        /*
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
		*/
        /*
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
        	//@Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 讀取選擇的記事物件
                Item item = itemAdapter.getItem(position);
         
                Intent intent = new Intent("net.macdidi.myandroidtutorial.EDIT_ITEM");
         
                // 設定記事編號與記事物件
                intent.putExtra("position", position);
                intent.putExtra("net.macdidi.myandroidtutorial.Item", item);
         
                startActivityForResult(intent, 1);              
            }
        };

        // 註冊長按監聽物件
        show_app_name.setOnLongClickListener(listener);
        */
        
        // 註冊選單項目點擊監聽物件
        item_list.setOnItemClickListener(itemListener);
     
        // 建立記事項目長按監聽物件
        OnItemLongClickListener itemLongListener = new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, 
                    int position, long id) {
                // 讀取選擇的記事物件
                Item item = itemAdapter.getItem(position);
                // 處理是否顯示已選擇項目
                processMenu(item);
                // 重新設定記事項目
                itemAdapter.set(position, item);
                return true;
            }
        };
     
        // 註冊記事項目長按監聽物件
        item_list.setOnItemLongClickListener(itemLongListener);

        //Toast.makeText(this, "MainActivity processControllers end", Toast.LENGTH_LONG).show();
    }
    
    // 處理是否顯示已選擇項目
    private void processMenu(Item item) {
        // 如果需要設定記事項目
        if (item != null) {
            // 設定已勾選的狀態
            item.setSelected(!item.isSelected());
     
            // 計算已勾選數量
            if (item.isSelected()) {
                selectedCount++;
            }
            else {
                selectedCount--;
            }
        }
     
        // 根據選擇的狀況，設定是否顯示選單項目
        add_item.setVisible(selectedCount == 0);
        search_item.setVisible(selectedCount == 0);
        revert_item.setVisible(selectedCount > 0);
        share_item.setVisible(selectedCount > 0);
        delete_item.setVisible(selectedCount > 0);
    }

    // 載入選單資源
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        
        
        /*
        // 取得選單項目物件
        add_item = menu.findItem(R.id.add_item);
        search_item = menu.findItem(R.id.search_item);
        revert_item = menu.findItem(R.id.revert_item);
        share_item = menu.findItem(R.id.share_item);
        delete_item = menu.findItem(R.id.delete_item);
        */
     
        // 設定選單項目
        processMenu(null);

        //Toast.makeText(this, "MainActivity onCreateOptionsMenu end", Toast.LENGTH_LONG).show();
        return true;
    }

    public void clickMenuItem(MenuItem item) {
    	// 使用參數取得使用者選擇的選單項目元件編號
    	int itemId = item.getItemId();
    	/*
    	switch (itemId) {
	        case R.id.search_item:
	            break;
	        case R.id.add_item:
	            Intent intent = new Intent("net.macdidi.myandroidtutorial.ADD_ITEM");
	            startActivityForResult(intent, 0);
	            break;
	        // 取消所有已勾選的項目
	        case R.id.revert_item:
	            for (int i = 0; i < itemAdapter.getCount(); i++) {
	                Item ri = itemAdapter.getItem(i);
	     
	                if (ri.isSelected()) {
	                    ri.setSelected(false);
	                    itemAdapter.set(i, ri);
	                }
	            }
	     
	            selectedCount = 0;
	            processMenu(null);          
	     
	            break;
	        // 刪除
	        case R.id.delete_item:
	            // 沒有選擇
	            if (selectedCount == 0) {
	                break;
	            }
	     
	            // 建立與顯示詢問是否刪除的對話框
	            AlertDialog.Builder d = new AlertDialog.Builder(this);
	            String message = getString(R.string.delete_item);
	            d.setTitle(R.string.delete)
	             .setMessage(String.format(message, selectedCount));
	            d.setPositiveButton(android.R.string.yes, 
	                    new DialogInterface.OnClickListener() {
	                        @Override
	                        public void onClick(DialogInterface dialog, int which) {
	                            // 刪除所有已勾選的項目
	                            int index = itemAdapter.getCount() - 1;
	                                 
	                            while (index > -1) {
	                                Item item = itemAdapter.get(index);
	                                     
	                                if (item.isSelected()) {
	                                    itemAdapter.remove(item);
	                                }
	                                 
	                                index--;
	                            }
	                                 
	                            // 通知資料改變
	                            itemAdapter.notifyDataSetChanged();
	                        }
	                    });
	            d.setNegativeButton(android.R.string.no, null);
	            d.show();
	     
	            break;
	        case R.id.googleplus_item:
	            break;
	        case R.id.facebook_item:
	            break;
        }
        //Toast.makeText(this, "MainActivity clickMenuItem ("+itemId+")", Toast.LENGTH_LONG).show();
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
    
    public void onSubmit(View view) {
        // 確定按鈕
        if (view.getId() == R.id.ok_teim) {
        	
        	TextView title_text = null;
        	TextView content_text = null;
        	
            // 讀取使用者輸入的標題與內容
            String titleText = title_text.getText().toString();
            String contentText = content_text.getText().toString();
            
            Item item = null;
     
            // 設定記事物件的標題與內容
            item.setTitle(titleText);
            item.setContent(contentText);
     
            // 如果是修改記事
            if (getIntent().getAction().equals("net.macdidi.myandroidtutorial.EDIT_ITEM")) {
                item.setLastModify(new Date(0).getTime());
            }
            // 新增記事
            else {
                item.setDatetime(new Date(0).getTime());
            }           
     
            Intent result = getIntent();
            // 設定回傳的記事物件
            result.putExtra("net.macdidi.myandroidtutorial.Item", item);
            setResult(Activity.RESULT_OK, result);
        }
     
        // 結束
        finish();
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
