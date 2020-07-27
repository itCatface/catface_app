package cc.catface.app_amap_demo;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;

import cc.catface.ctool.context.TApp;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 *
 * @desc amap实际操作类
 */
public class AmapEngine implements AMapLocationListener, PoiSearch.OnPoiSearchListener {


    private static final class Holder {
        private static final AmapEngine instance = new AmapEngine();
    }

    public static AmapEngine getInstance() {
        return Holder.instance;
    }


    private AMapLocationClient mAMapLocationClient;
    private AMapLocationClientOption mAMapLocationClientOption;


    private AmapEngine() {
        /* 初始化amap设置 */
        mAMapLocationClientOption = new AMapLocationClientOption();
        mAMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        mAMapLocationClientOption.setGpsFirst(true);
        mAMapLocationClientOption.setHttpTimeOut(3_000);
        mAMapLocationClientOption.setGpsFirstTimeout(3_000);
        mAMapLocationClientOption.setNeedAddress(true);
        mAMapLocationClientOption.setInterval(2_000);
        mAMapLocationClientOption.setOnceLocation(true);
        mAMapLocationClientOption.setOnceLocationLatest(false);
        mAMapLocationClientOption.setSensorEnable(false);
        mAMapLocationClientOption.setWifiScan(true);
        mAMapLocationClientOption.setLocationCacheEnable(true);
        mAMapLocationClientOption.setGeoLanguage(AMapLocationClientOption.GeoLanguage.DEFAULT);
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);

        /* 初始化amap */
        mAMapLocationClient = new AMapLocationClient(TApp.getInstance());
        mAMapLocationClient.setLocationListener(this);
    }


    public interface LocationCallback {
        void onLocation(long timestamp, AMapLocation location);

        void onError(int code, String info, String detail);
    }

    private LocationCallback mLocationCallback;

    public void setLocationCallback(LocationCallback locationCallback) {
        this.mLocationCallback = locationCallback;
    }

    public interface PoiSearchCallback {
        void onResult(PoiResult result);
    }

    private PoiSearchCallback mPoiSearchCallback;

    public void setPoiSearchCallback(PoiSearchCallback poiSearchCallback) {
        this.mPoiSearchCallback = poiSearchCallback;
    }


    /* 开始定位 */
    public void startLocation() {
        mAMapLocationClient.setLocationOption(mAMapLocationClientOption);
        mAMapLocationClient.startLocation();
    }


    /* 停止定位 */
    public void stopLocation() {
        if (null != mAMapLocationClient) mAMapLocationClient.stopLocation();
    }


    /* 搜索位置列表 */
    public void startPoi(String poi, int pageNum) {
        startPoi(poi, 10, pageNum);
    }

    String mCity = "";
    private String mCityCode = "";

    public void startPoi(String poi, int pageSize, int pageNum) {
        PoiSearch.Query query = new PoiSearch.Query(poi, "", mCityCode);
        query.setPageSize(pageSize);  // 每页多少条结果
        query.setPageNum(pageNum);    // 查询页码
        PoiSearch search = new PoiSearch(TApp.getInstance(), query);
        // search.setBound(new PoiSearch.SearchBound(new LatLonPoint(location.getLatitude(), location.getLongitude()), 10000));
        search.setOnPoiSearchListener(this);
        search.searchPOIAsyn();
    }


    @Override
    public void onLocationChanged(AMapLocation location) {
        int code = location.getErrorCode();
        if (0 != code) {
            mLocationCallback.onError(code, location.getErrorInfo(), location.getLocationDetail());
            return;
        }

        mLocationCallback.onLocation(System.currentTimeMillis(), location);
        mCity = location.getCity();
        mCityCode = location.getCityCode();
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        if (null != mPoiSearchCallback) mPoiSearchCallback.onResult(poiResult);
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) { }

    public String getCity() {
        return mCity;
    }

    /**
     * 退出定位功能，在activity/fragment退出时调用
     */
    public void release() {
        stopLocation();
        if (null != mAMapLocationClient) mAMapLocationClient.unRegisterLocationListener(this);
        if (null != mLocationCallback) mLocationCallback = null;
        if (null != mPoiSearchCallback) mPoiSearchCallback = null;
    }
}
