package cc.catface.iflytek.service;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;

import cc.catface.ctool.context.TApp;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class AmapEngine implements AMapLocationListener, PoiSearch.OnPoiSearchListener {


    /**
     * 初始化定位
     */
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;


    /**
     * 默认的定位参数
     */
    private AMapLocationClientOption mOption;

    private AMapLocationClientOption getDefaultOption() {
        mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mOption.setGpsFirst(true);
        mOption.setHttpTimeOut(30000);
        mOption.setInterval(mContinuedLocationInterval);
        mOption.setNeedAddress(true);
        mOption.setOnceLocation(false);
        mOption.setOnceLocationLatest(false);
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);
        mOption.setSensorEnable(false);
        mOption.setWifiScan(true);
        mOption.setLocationCacheEnable(true);
        mOption.setGeoLanguage(AMapLocationClientOption.GeoLanguage.DEFAULT);
        return mOption;
    }

    private int mContinuedLocationInterval = 2_000;

    /**
     * 设置持续定位的时间间隔
     *
     * @param interval 单位毫秒ms[1s=1000ms]
     */
    public void setContinuedLocationInterval(int interval) {
        mContinuedLocationInterval = interval;
    }


    private LocationCallback mLocationCallback;

    public void setLocationCallback(LocationCallback locationCallback) {
        this.mLocationCallback = locationCallback;
    }

    public interface LocationCallback {
        void onLocation(long timestamp, AMapLocation location);

        void onError(int code, String info, String detail);
    }

    private PoiSearchCallback mPoiSearchCallback;

    public void setPoiSearchCallback(PoiSearchCallback poiSearchCallback) {
        this.mPoiSearchCallback = poiSearchCallback;
    }

    public interface PoiSearchCallback {
        void onResult(PoiResult result);
    }

    @Override public void onLocationChanged(AMapLocation location) {
        if (null == mLocationCallback) return;
        if (null == location) {
            mLocationCallback.onError(-0x99, "null == location", "null == location");
            return;
        }

        int code = location.getErrorCode();
        if (0 != code) {
            mLocationCallback.onError(code, location.getErrorInfo(), location.getLocationDetail());
            return;
        }

        mLocationCallback.onLocation(System.currentTimeMillis(), location);

        // 位置列表
        if (null == mPoiSearchCallback) return;
        PoiSearch.Query query = new PoiSearch.Query("", "", "");
        query.setPageSize(20);  // 每页多少条结果
        query.setPageNum(1);    // 查询页码
        PoiSearch search = new PoiSearch(TApp.getInstance(), query);
        search.setBound(new PoiSearch.SearchBound(new LatLonPoint(location.getLatitude(), location.getLongitude()), 10000));
        search.setOnPoiSearchListener(this);
        search.searchPOIAsyn();
    }

    @Override public void onPoiSearched(PoiResult poiResult, int i) {
        if (null == mPoiSearchCallback) return;
        mPoiSearchCallback.onResult(poiResult);
    }

    @Override public void onPoiItemSearched(PoiItem poiItem, int i) { }

    /**
     * 开始定位
     */
    public void startLocation() {
        locationClient = new AMapLocationClient(TApp.getInstance());
        locationOption = getDefaultOption();
        locationClient.setLocationOption(locationOption);
        locationClient.setLocationListener(this);
        locationClient.startLocation();
    }

    /**
     * 停止定位
     */
    public void stopLocation() {
        if (null != locationClient) {
            locationClient.stopLocation();
        }
    }


    /**
     * 退出定位功能，在activity/fragment退出时调用
     */
    public void release() {
        stopLocation();
        if (null != locationClient) locationClient.unRegisterLocationListener(this);

        mLocationCallback = null;
        mPoiSearchCallback = null;
    }
}
