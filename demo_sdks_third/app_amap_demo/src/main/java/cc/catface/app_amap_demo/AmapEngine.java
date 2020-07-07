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
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        mOption.setGpsFirst(true);
        mOption.setHttpTimeOut(3_000);
        mOption.setGpsFirstTimeout(3_000);
        mOption.setInterval(mContinuedLocationInterval);
        mOption.setNeedAddress(true);
        mOption.setOnceLocation(true);  // 仅定位一次
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
    @Deprecated
    public void setContinuedLocationInterval(int interval) {
        mContinuedLocationInterval = interval;
    }


    /**
     * 供aidl使用的定位和位置列表搜索的回调
     */
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


    /**
     * 客户动作
     */
    /* 开始定位 */
    public void startLocation() {
        locationClient = new AMapLocationClient(TApp.getInstance());
        locationOption = getDefaultOption();
        locationClient.setLocationOption(locationOption);
        locationClient.setLocationListener(this);
        locationClient.startLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation location) {
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
    }

    /* 停止定位 */
    public void stopLocation() {
        if (null != locationClient) {
            locationClient.stopLocation();
        }
    }


    /* 搜索位置列表 */
    public void startPoi(String poi, int pageNum) {
        startPoi(poi, 20, pageNum);
    }

    public void startPoi(String poi, int pageSize, int pageNum) {
        PoiSearch.Query query = new PoiSearch.Query(poi, "", "");
        query.setPageSize(pageSize);  // 每页多少条结果
        query.setPageNum(pageNum);    // 查询页码
        PoiSearch search = new PoiSearch(TApp.getInstance(), query);
        // search.setBound(new PoiSearch.SearchBound(new LatLonPoint(location.getLatitude(), location.getLongitude()), 10000));
        search.setOnPoiSearchListener(this);
        search.searchPOIAsyn();
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        if (null == mPoiSearchCallback) return;
        mPoiSearchCallback.onResult(poiResult);
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) { }


    /**
     * 退出定位功能，在activity/fragment退出时调用
     */
    public void release() {
        stopLocation();
        if (null != locationClient) locationClient.unRegisterLocationListener(this);
        if (null != mLocationCallback) mLocationCallback = null;
        if (null != mPoiSearchCallback) mPoiSearchCallback = null;

    }
}
