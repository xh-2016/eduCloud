package com.activity.educloud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FileActivity extends Activity {

	private ListView mListView;  
    private List<Map<String, Object>> listItems;
    private FileListAdapter filelistadapter;
    private Integer[] viewimageIDs = {R.drawable.documnet};
    private Integer[] btnimageIDs = {R.drawable.not_selected};
    private String[] filesNames = {"文件名"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file);
		
		mListView = (ListView) findViewById(R.id.ltv_file); 
		listItems = new ArrayList<Map<String, Object>>();   
        for(int i = 0; i < filesNames.length; i++) {   
            Map<String, Object> map = new HashMap<String, Object>();    
            map.put("viewimage", viewimageIDs[i]);               
            map.put("filename", filesNames[i]);              
            map.put("btnimage", btnimageIDs[i]);         
            listItems.add(map);   
        }    
        filelistadapter = new FileListAdapter(this, listItems); //创建适配器   
        mListView.setAdapter(filelistadapter);
	}

	class FileListAdapter extends BaseAdapter {  
        private LayoutInflater mInflater;  
        private Context mContext = null;
        private android.widget.Spinner fileselect = null;
        private List<Map<String, Object>> mlistItems;
        public FileListAdapter(Context context,List<Map<String, Object>> lt) {  
            mContext = context;  
            mlistItems = lt;
            mInflater = LayoutInflater.from(mContext);  
        }  
        public Object getItem(int arg0) {  
            // TODO Auto-generated method stub  
            return null;  
        }  
        public long getItemId(int position) {  
            // TODO Auto-generated method stub  
            return 0;  
        }  
        public int getCount() {  
            // TODO Auto-generated method stub  
            return mlistItems.size();  
        }  
        public View getView(int position, View convertView,  
                android.view.ViewGroup parent) {  
            final ImageView indexImage;  
            final TextView indexText;
            final ImageButton indexButton;
            if (convertView == null) {  
                // 和item_custom.xml脚本关联  
                convertView = mInflater.inflate(R.layout.listview_fileitem, null);  
            }  
            indexImage = (ImageView) convertView.findViewById(R.id.imv_filetype);  
            indexText = (TextView) convertView.findViewById(R.id.tv_filename); 
            indexButton = (ImageButton)convertView.findViewById(R.id.imbtn_select); 
            
            indexText.setText(mlistItems.get(position).get("filename").toString());  
            //indexText.setTextColor(Color.RED);    
            indexImage.setBackgroundResource((Integer)mlistItems.get(position).get("viewimage"));  
            indexButton.setBackgroundResource((Integer)mlistItems.get(position).get("btnimage"));
            
            indexButton.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					new AlertDialog.Builder(FileActivity.this)
					.setTitle("文件选项")
	                .setMultiChoiceItems(new String[] {"删除","共享","重命名"}, null, null)
	                .setPositiveButton("确定", null)
	                .setNegativeButton("取消", null)
	                .show();
				}
            	
            });
            
            indexButton.setOnTouchListener(new View.OnTouchListener(){

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					if(event.getAction() == MotionEvent.ACTION_DOWN){
						indexButton.setBackgroundResource(R.drawable.selected);
					}
					else if(event.getAction() == MotionEvent.ACTION_UP){
						indexButton.setBackgroundResource(R.drawable.not_selected);
					}
					return false;
				}
            	
            });
            return convertView;  
        }  
    }  
	
}
