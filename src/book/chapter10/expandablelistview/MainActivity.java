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


/*��MainActivity������չ�����б�����Ҫ����Դ������������ơ����ͼ�ꡢ������ơ����ͼ�꣬����ÿ�����Դ��ͨ����ά�������趨�ģ���ά�����ÿһ�д���һ����Դ��
 * ÿһ�д�������µ�һ�*/
public class MainActivity extends Activity {

	String[] type=new String[]{"�ҵĺ���","��ѧͬѧ","��������"};//��������ʾ������
	String[][] info=new String[][]{{"����","����","����"},{"����","��˹"},{"����","����","����","����"}};//����ÿһ������ݣ�ÿһ����ĸ������Բ�һ��
	int[] groupImgs=new int[]{R.drawable.g1,R.drawable.g2,R.drawable.g3};//���ͼ��
	//ÿһ���ͼ��
	int[][]imgIds=new int[][]{
			{R.drawable.a1,R.drawable.a2,R.drawable.a3},
			{R.drawable.a4,R.drawable.a5,R.drawable.a6},
			{R.drawable.a7,R.drawable.a8,R.drawable.a9,R.drawable.a10}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);//���ý��沼���ļ�
		
		//������Դ�����ʾ��ͨ��ʵ��BaseExpandableList-Adapter����������ɡ�
		//��ȡ��չ�����б�
		ExpandableListView myExpandable = (ExpandableListView) findViewById(R.id.myExpandable);
		//����һ���Զ��������б�������������������������ʾ֮��Ĺ�ϵ
		ExpandableListAdapter myAdapter=new BaseExpandableListAdapter(){

			@Override
			public boolean isChildSelectable(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				return true;//�����Ƿ����ѡ��
			}
			
			@Override
			public boolean hasStableIds() {
				// TODO Auto-generated method stub
				return false;
			}
			
			//�Լ�����һ����ȡTextView�ķ���
			private TextView getTextView(){
				AbsListView.LayoutParams lp=new AbsListView.LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT,
						ViewGroup.LayoutParams.WRAP_CONTENT);//���ÿ�Ⱥ͸߶�
				
				TextView textview=new TextView(MainActivity.this);
				textview.setLayoutParams(lp);
				textview.setGravity(Gravity.CENTER_VERTICAL);//����ˮƽ����
				textview.setTextSize(20);//�������ִ�СΪ20sp
				textview.setPadding(30, 0, 0, 0);//������߾�Ϊ30pt
				textview.setTextColor(Color.BLACK);//�����ı���ɫ
				return textview;//��ȡ�Զ����ı��ؼ�
			}
			
			@Override
			public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
					ViewGroup parent) {
				// TODO Auto-generated method stub
				LinearLayout layout=new LinearLayout(MainActivity.this);//���Բ���
				layout.setOrientation(LinearLayout.HORIZONTAL);//�������Բ��ַ���
				layout.setGravity(Gravity.CENTER_VERTICAL);//���ô�ֱ����
				
				ImageView groupImg=new ImageView(MainActivity.this);//����һ��ImageView
				groupImg.setImageResource(groupImgs[groupPosition]);//����ImageView��ͼƬ
				layout.addView(groupImg);//�����Բ��������ͼƬ
				
				TextView textView=getTextView();//�õ�һ��textView
				textView.setText(getGroup(groupPosition).toString());//����TextView��ʾ����
				layout.addView(textView);//�ڲ��������textView
				return layout;//�����������Բ��ֿؼ�
			}

			@Override
			public long getGroupId(int groupPosition) {
				// TODO Auto-generated method stub
				return groupPosition;//��ȡ���ID
			}
			
			@Override
			public int getGroupCount() {
				// TODO Auto-generated method stub
				return type.length;//��ȡ��ĸ���
			}
			
			
			@Override
			public Object getGroup(int groupPosition) {
				// TODO Auto-generated method stub
				return type[groupPosition];//��ȡ�Զ������
			}
			
			@Override
			public int getChildrenCount(int groupPosition) {
				// TODO Auto-generated method stub
				return info[groupPosition].length;//��ȡָ��������
			}
			
			@Override
			public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
					View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				LinearLayout layout=new LinearLayout(MainActivity.this);//���Բ���
				layout.setOrientation(LinearLayout.HORIZONTAL);//�������Բ��ַ���
				layout.setPadding(20, 0, 0, 0);//�������Բ��ֵ���߾�
				
				ImageView itemImage=new ImageView(MainActivity.this);//����ͼƬ��ͼ
				itemImage.setPadding(20, 0, 0, 0);//����ͼƬ����߾�
				itemImage.setImageResource(imgIds[groupPosition][childPosition]);
				layout.addView(itemImage);//�����Բ��������ͼƬ
				
				TextView textView=getTextView();//��ȡ�ı���ʾ��
				textView.setText(getChild(groupPosition,childPosition).toString());
				layout.addView(textView);//�ڲ���������ı��ؼ�
				return layout;//�������Բ���
			}
			
			@Override
			public long getChildId(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				return childPosition;//��ȡ�����ID
			}
			
			@Override
			public Object getChild(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				return info[groupPosition][childPosition];//��ȡָ������ָ����ŵ���
			}
			
		};
		
		//����չ�����б�������������
		myExpandable.setAdapter(myAdapter);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
