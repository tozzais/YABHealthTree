//package com.hanyu.healthtree.yabhealthtree.util;
//
//import android.app.Activity;
//import android.app.Dialog;
//import android.content.Context;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.TextView;
//
//import com.ipd.east.jumpboxlibrary.kankan.wheel.widget.OnWheelChangedListener;
//import com.ipd.east.jumpboxlibrary.kankan.wheel.widget.WheelView;
//import com.ipd.east.jumpboxlibrary.kankan.wheel.widget.adapters.AbstractWheelTextAdapter;
//
//import java.util.List;
//
//import hanyu.com.truckgang.R;
//import hanyu.com.truckgang.bean.address.CityBean;
//import hanyu.com.truckgang.bean.address.CityDao;
//
//
//public class CityUtil3s implements OnWheelChangedListener {
//
//	public static final int LEVEL_ONE = 1 ,LEVEL_TWO = 2 , DEFAULT = 3;
//
//	private static CityUtil3s cityUtils;
//
//	public static CityUtil3s getInstance() {
//		if (cityUtils == null) {
//			synchronized (CityUtil3s.class) {
//				if (cityUtils == null) {
//					cityUtils = new CityUtil3s();
//				}
//			}
//		}
//		return cityUtils;
//	}
//
//	private WheelView mViewProvince;
//	private WheelView mViewCity;
//	private WheelView mViewCounty;
//	private TextView mBtnConfirm;
//	private Context context;
//	private CityDao cityDao;
//	private CityWheelAdapter provinceAdapter;
//	private CityWheelAdapter cityAdapter;
//	private Dialog cityDialog;
//
//	public void showSelectDialog(final Context context,int number ,final onSelectCityFinishListener listener) {
//		cityDao = new CityDao(context);
//		this.context = context;
//		View view = View.inflate(context, R.layout.city_choose_dialog2, null);
//		mViewProvince = (WheelView) view.findViewById(R.id.wv_country);
//		mViewCity = (WheelView) view.findViewById(R.id.wv_city);
//		mViewCounty = (WheelView) view.findViewById(R.id.wv_county);
//		if(LEVEL_ONE == number){
//			mViewCity.setVisibility(View.GONE);
//			mViewCounty.setVisibility(View.GONE);
//		}else if (LEVEL_TWO == number){
//			mViewCounty.setVisibility(View.GONE);
//		}
//
//		mBtnConfirm = (TextView) view.findViewById(R.id.tv_commit);
//		TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
//		mBtnConfirm.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				cityDialog.dismiss();
//				CityBean provinceBean = cityDao.getProvince().get(mViewProvince.getCurrentItem());
//				List<CityBean> citys = cityDao.getCity(provinceBean.id);
//				CityBean city = citys.get(mViewCity.getCurrentItem());
//
//				List<CityBean> countrys = cityDao.getCity(city.id);
//				CityBean county = countrys.get(mViewCounty.getCurrentItem());
//				if (listener != null) {
//					listener.onFinish(provinceBean, city,county);
//				}
//			}
//		});
//		tv_cancel.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				cityDialog.dismiss();
//			}
//		});
//
//		setUpListener();
//		setUpData();
//
//		cityDialog = new Dialog(context,R.style.transparentFrameWindowStyle);
//		cityDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//		cityDialog.setContentView(view);
//		Window window = cityDialog.getWindow();
//		window.setWindowAnimations(R.style.PopupAnimation);
//
//		WindowManager.LayoutParams wl = window.getAttributes();
//		wl.x = 0;
//		wl.y = ((Activity) context).getWindowManager().getDefaultDisplay().getHeight();
//		wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
//		wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
//
//		cityDialog.getWindow().setAttributes(wl);
////		cityDialog.onWindowAttributesChanged(wl);
//
//		cityDialog.setCanceledOnTouchOutside(true);
//		cityDialog.show();
//	}
//
//	private void setUpData() {
//		List<CityBean> province = cityDao.getProvince();
//		provinceAdapter = new CityWheelAdapter(context, province);
//		mViewProvince.setViewAdapter(provinceAdapter);
//
//		mViewProvince.setVisibleItems(7);
//		mViewCity.setVisibleItems(7);
//		mViewCounty.setVisibleItems(7);
//		updateCities();
//		updateCounty();
//	}
//
//	private void setUpListener() {
//		mViewProvince.addChangingListener(this);
//		mViewCity.addChangingListener(this);
//		mViewCounty.addChangingListener(this);
//	}
//
//	@Override
//	public void onChanged(WheelView wheel, int oldValue, int newValue) {
//		if (wheel == mViewProvince) {
//			updateCities();
//			updateCounty();
//		}else if(wheel == mViewCity){
//			updateCounty();
//		}
//	}
//
//	private void updateCities() {
//
//		List<CityBean> city =  cityDao.getCity(provinceAdapter.getCityId(mViewProvince.getCurrentItem()));
//		cityAdapter = new CityWheelAdapter(context, city);
////		mViewProvince.setViewAdapter(provinceAdapter);
//
//		mViewCity.setViewAdapter(cityAdapter);
//		mViewCity.setCurrentItem(0);
//	}
//	private void updateCounty(){
//		mViewCounty.setViewAdapter(new CityWheelAdapter(context,
//				cityDao.getCity(cityAdapter.getCityId(mViewCity.getCurrentItem()))));
//		mViewCounty.setCurrentItem(0);
//	}
//
//	class CityWheelAdapter extends AbstractWheelTextAdapter {
//		List<CityBean> list;
//
//		public CityWheelAdapter(Context context, List<CityBean> list) {
//			super(context);
//			this.list = list;
//		}
//
//		@Override
//		public int getItemsCount() {
//			return list == null ? 0 : list.size();
//		}
//
//		public String getCityId(int position) {
//			return list.get(position).id;
//		}
//
//		@Override
//		protected CharSequence getItemText(int index) {
//			return list.get(index).name;
//		}
//
//	}
//
//	public interface onSelectCityFinishListener {
//		public void onFinish(CityBean province, CityBean city, CityBean county);
//	}
//
//}
