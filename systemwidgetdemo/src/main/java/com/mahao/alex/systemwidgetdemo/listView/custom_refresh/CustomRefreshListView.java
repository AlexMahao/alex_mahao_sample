package com.mahao.alex.systemwidgetdemo.listView.custom_refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mahao.alex.systemwidgetdemo.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomRefreshListView extends ListView implements OnScrollListener{

	/**
	 * 头布局
	 */
	private View headerView;

	/**
	 * 头部布局的高度
	 */
	private int headerViewHeight;

	/**
	 * 头部旋转的图片
	 */
	private ImageView iv_arrow;

	/**
	 * 头部下拉刷新时状态的描述
	 */
	private TextView tv_state;

	/**
	 * 下拉刷新时间的显示控件
	 */
	private TextView tv_time;


	/**
	 * 底部布局
	 */
	private View footerView;

	/**
	 * 底部旋转progressbar
	 */
	private ProgressBar pb_rotate;


	/**
	 * 底部布局的高度
	 */
	private int footerViewHeight;


	/**
	 * 按下时的Y坐标
	 */
	private int downY;
	
	private final int PULL_REFRESH = 0;//下拉刷新的状态
	private final int RELEASE_REFRESH = 1;//松开刷新的状态
	private final int REFRESHING = 2;//正在刷新的状态

	/**
	 * 当前下拉刷新处于的状态
	 */
	private int currentState = PULL_REFRESH;

	/**
	 * 头部布局在下拉刷新改变时，图标的动画
	 */
	private RotateAnimation upAnimation,downAnimation;

	/**
	 * 当前是否在加载数据
	 */
	private boolean isLoadingMore = false;

	public CustomRefreshListView(Context context) {
		this(context,null);
	}

	public CustomRefreshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	private void init(){
		//设置滑动监听
		setOnScrollListener(this);
		//初始化头布局
		initHeaderView();
		//初始化头布局中图标的旋转动画
		initRotateAnimation();
		//初始化为尾布局
		initFooterView();
	}


	/**
	 * 初始化headerView
	 */
	private void initHeaderView() {
		headerView = View.inflate(getContext(), R.layout.head_custom_listview, null);
		iv_arrow = (ImageView) headerView.findViewById(R.id.iv_arrow);
		pb_rotate = (ProgressBar) headerView.findViewById(R.id.pb_rotate);
		tv_state = (TextView) headerView.findViewById(R.id.tv_state);
		tv_time = (TextView) headerView.findViewById(R.id.tv_time);

		//测量headView的高度
		headerView.measure(0, 0);
		//获取高度，并保存
		headerViewHeight = headerView.getMeasuredHeight();
		//设置paddingTop = -headerViewHeight;这样，该控件被隐藏
		headerView.setPadding(0, -headerViewHeight, 0, 0);
		//添加头布局
		addHeaderView(headerView);
	}
	
	/**
	 * 初始化旋转动画
	 */
	private void initRotateAnimation() {

		upAnimation = new RotateAnimation(0, -180, 
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		upAnimation.setDuration(300);
		upAnimation.setFillAfter(true);

		downAnimation = new RotateAnimation(-180, -360, 
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		downAnimation.setDuration(300);
		downAnimation.setFillAfter(true);
	}

	//初始化底布局，与头布局同理
	private void initFooterView() {
		footerView = View.inflate(getContext(), R.layout.foot_custom_listview, null);
		footerView.measure(0, 0);
		footerViewHeight = footerView.getMeasuredHeight();
		footerView.setPadding(0, -footerViewHeight, 0, 0);
		addFooterView(footerView);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			//获取按下时y坐标
			downY = (int) ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			
			if(currentState==REFRESHING){
				//如果当前处在滑动状态，则不做处理
				break;
			}
			//手指滑动偏移量
			int deltaY = (int) (ev.getY() - downY);

			//获取新的padding值
			int paddingTop = -headerViewHeight + deltaY;
			if(paddingTop>-headerViewHeight && getFirstVisiblePosition()==0){
				//向下滑，且处于顶部，设置padding值，该方法实现了顶布局慢慢滑动显现
				headerView.setPadding(0, paddingTop, 0, 0);

				if(paddingTop>=0 && currentState==PULL_REFRESH){
					//从下拉刷新进入松开刷新状态
					currentState = RELEASE_REFRESH;
					//刷新头布局
					refreshHeaderView();
				}else if (paddingTop<0 && currentState==RELEASE_REFRESH) {
					//进入下拉刷新状态
					currentState = PULL_REFRESH;
					refreshHeaderView();
				}
				
				
				return true;//拦截TouchMove，不让listview处理该次move事件,会造成listview无法滑动
			}
			
			
			break;
		case MotionEvent.ACTION_UP:
			if(currentState==PULL_REFRESH){
				//仍处于下拉刷新状态，未滑动一定距离，不加载数据，隐藏headView
				headerView.setPadding(0, -headerViewHeight, 0, 0);
			}else if (currentState==RELEASE_REFRESH) {
				//滑倒一定距离，显示无padding值得headcView
				headerView.setPadding(0, 0, 0, 0);
				//设置状态为刷新
				currentState = REFRESHING;

				//刷新头部布局
				refreshHeaderView();
				
				if(listener!=null){
					//接口回调加载数据
					listener.onPullRefresh();
				}
			}
			break;
		}
		return super.onTouchEvent(ev);
	}
	
	/**
	 * 根据currentState来更新headerView
	 */
	private void refreshHeaderView(){
		switch (currentState) {
		case PULL_REFRESH:
			tv_state.setText("下拉刷新");
			iv_arrow.startAnimation(downAnimation);
			break;
		case RELEASE_REFRESH:
			tv_state.setText("松开刷新");
			iv_arrow.startAnimation(upAnimation);
			break;
		case REFRESHING:
			iv_arrow.clearAnimation();//因为向上的旋转动画有可能没有执行完
			iv_arrow.setVisibility(View.INVISIBLE);
			pb_rotate.setVisibility(View.VISIBLE);
			tv_state.setText("正在刷新...");
			break;
		}
	}
	
	/**
	 * 完成刷新操作，重置状态,在你获取完数据并更新完adater之后，去在UI线程中调用该方法
	 */
	public void completeRefresh(){
		if(isLoadingMore){
			//重置footerView状态
			footerView.setPadding(0, -footerViewHeight, 0, 0);
			isLoadingMore = false;
		}else {
			//重置headerView状态
			headerView.setPadding(0, -headerViewHeight, 0, 0);
			currentState = PULL_REFRESH;
			pb_rotate.setVisibility(View.INVISIBLE);
			iv_arrow.setVisibility(View.VISIBLE);
			tv_state.setText("下拉刷新");
			tv_time.setText("最后刷新："+getCurrentTime());
		}
	}
	
	/**
	 * 获取当前系统时间，并格式化
	 * @return
	 */
	private String getCurrentTime(){
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		return format.format(new Date());
	}
	
	private OnRefreshListener listener;
	public void setOnRefreshListener(OnRefreshListener listener){
		this.listener = listener;
	}
	public interface OnRefreshListener{
		void onPullRefresh();
		void onLoadingMore();
	}
	
	/**
	 * SCROLL_STATE_IDLE:闲置状态，就是手指松开
	 * SCROLL_STATE_TOUCH_SCROLL：手指触摸滑动，就是按着来滑动
	 * SCROLL_STATE_FLING：快速滑动后松开
	 */
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if(scrollState==OnScrollListener.SCROLL_STATE_IDLE 
				&& getLastVisiblePosition()==(getCount()-1) &&!isLoadingMore){
			isLoadingMore = true;
			
			footerView.setPadding(0, 0, 0, 0);//显示出footerView
			setSelection(getCount());//让listview最后一条显示出来
			
			if(listener!=null){
				listener.onLoadingMore();
			}
		}
	}


	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
	}

}
