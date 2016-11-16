package book.chapter10.expandablelistview;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/*在MainActivity定义拓展下拉列表所需要的资源。包括组的名称、组的图标、项的名称、项的图标，其中每项的资源是通过二维数组来设定的，二维数组的每一行代表一组资源，
 * 每一列代表该组下的一项。*/
public class MainActivity extends Activity {

	String[] type=new String[]{"我的好友","大学同学","亲戚朋友"};//定义组显示的文字
	String[][] info=new String[][]{{"张三","张四","张五"},{"李四","李斯"},{"王五","王六","王二","王三"}};//定义每一组的内容，每一组项的个数可以不一致
	int[] groupImgs=new int[]{R.drawable.g1,R.drawable.g2,R.drawable.g3};//组的图标
	//每一项的图标
	int[][]imgIds=new int[][]{
			{R.drawable.a1,R.drawable.a2,R.drawable.a3},
			{R.drawable.a4,R.drawable.a5,R.drawable.a6},
			{R.drawable.a7,R.drawable.a8,R.drawable.a9,R.drawable.a10}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);//设置界面布局文件
		
		//设置资源如何显示，通过实现BaseExpandableList-Adapter抽象类来完成。
		//获取扩展下拉列表
		ExpandableListView myExpandable = (ExpandableListView) findViewById(R.id.myExpandable);
		//创建一个自定的下拉列表适配器，用于设置内容与显示之间的关系
		ExpandableListAdapter myAdapter=new BaseExpandableListAdapter(){

			@Override
			public boolean isChildSelectable(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				return true;//子项是否可以选择
			}
			
			@Override
			public boolean hasStableIds() {
				// TODO Auto-generated method stub
				return false;
			}
			
			//自己定义一个获取TextView的方法
			private TextView getTextView(){
				AbsListView.LayoutParams lp=new AbsListView.LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT,
						ViewGroup.LayoutParams.WRAP_CONTENT);//设置宽度和高度
				
				TextView textview=new TextView(MainActivity.this);
				textview.setLayoutParams(lp);
				textview.setGravity(Gravity.CENTER_VERTICAL);//文字水平居中
				textview.setTextSize(20);//设置文字大小为20sp
				textview.setPadding(30, 0, 0, 0);//设置左边距为30pt
				textview.setTextColor(Color.BLACK);//设置文本颜色
				return textview;//获取自定义文本控件
			}
			
			@Override
			public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
					ViewGroup parent) {
				// TODO Auto-generated method stub
				LinearLayout layout=new LinearLayout(MainActivity.this);//线性布局
				layout.setOrientation(LinearLayout.HORIZONTAL);//设置线性布局方向
				layout.setGravity(Gravity.CENTER_VERTICAL);//设置垂直居中
				
				ImageView groupImg=new ImageView(MainActivity.this);//创建一个ImageView
				groupImg.setImageResource(groupImgs[groupPosition]);//设置ImageView的图片
				layout.addView(groupImg);//在线性布局中添加图片
				
				TextView textView=getTextView();//得到一个textView
				textView.setText(getGroup(groupPosition).toString());//设置TextView显示内容
				layout.addView(textView);//在布局中添加textView
				return layout;//返回整个线性布局控件
			}

			@Override
			public long getGroupId(int groupPosition) {
				// TODO Auto-generated method stub
				return groupPosition;//获取组的ID
			}
			
			@Override
			public int getGroupCount() {
				// TODO Auto-generated method stub
				return type.length;//获取组的个数
			}
			
			
			@Override
			public Object getGroup(int groupPosition) {
				// TODO Auto-generated method stub
				return type[groupPosition];//获取自定组对象
			}
			
			@Override
			public int getChildrenCount(int groupPosition) {
				// TODO Auto-generated method stub
				return info[groupPosition].length;//获取指定组项数
			}
			
			@Override
			public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
					View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				LinearLayout layout=new LinearLayout(MainActivity.this);//线性布局
				layout.setOrientation(LinearLayout.HORIZONTAL);//设置线性布局方向
				layout.setPadding(20, 0, 0, 0);//设置线性布局的左边距
				
				ImageView itemImage=new ImageView(MainActivity.this);//创建图片视图
				itemImage.setPadding(20, 0, 0, 0);//设置图片的左边距
				itemImage.setImageResource(imgIds[groupPosition][childPosition]);
				layout.addView(itemImage);//在线性布局中添加图片
				
				TextView textView=getTextView();//获取文本显示框
				textView.setText(getChild(groupPosition,childPosition).toString());
				layout.addView(textView);//在布局中添加文本控件
				return layout;//返回线性布局
			}
			
			@Override
			public long getChildId(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				return childPosition;//获取子项的ID
			}
			
			@Override
			public Object getChild(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				return info[groupPosition][childPosition];//获取指定组中指定序号的项
			}
			
		};
		
		//将拓展下拉列表与适配器关联
		myExpandable.setAdapter(myAdapter);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
