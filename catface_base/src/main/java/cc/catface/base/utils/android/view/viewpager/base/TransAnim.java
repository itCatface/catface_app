package cc.catface.base.utils.android.view.viewpager.base;

import cc.catface.base.utils.android.view.viewpager.AccordionTransformer;
import cc.catface.base.utils.android.view.viewpager.BackgroundToForegroundTransformer;
import cc.catface.base.utils.android.view.viewpager.CubeInTransformer;
import cc.catface.base.utils.android.view.viewpager.CubeOutTransformer;
import cc.catface.base.utils.android.view.viewpager.DepthPageTransformer;
import cc.catface.base.utils.android.view.viewpager.DepthPageTransformer2;
import cc.catface.base.utils.android.view.viewpager.DrawFromBackTransformer;
import cc.catface.base.utils.android.view.viewpager.FlipHorizontalTransformer;
import cc.catface.base.utils.android.view.viewpager.FlipVerticalTransformer;
import cc.catface.base.utils.android.view.viewpager.ForegroundToBackgroundTransformer;
import cc.catface.base.utils.android.view.viewpager.ParallaxPageTransformer;
import cc.catface.base.utils.android.view.viewpager.RotateDownTransformer;
import cc.catface.base.utils.android.view.viewpager.RotateUpTransformer;
import cc.catface.base.utils.android.view.viewpager.StackTransformer;
import cc.catface.base.utils.android.view.viewpager.TabletTransformer;
import cc.catface.base.utils.android.view.viewpager.ZoomInTransformer;
import cc.catface.base.utils.android.view.viewpager.ZoomOutPageTransformer;
import cc.catface.base.utils.android.view.viewpager.ZoomOutSlideTransformer;
import cc.catface.base.utils.android.view.viewpager.ZoomOutTransformer;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TransAnim {
    public static AccordionTransformer accordionTransformer = new AccordionTransformer();
    public static BackgroundToForegroundTransformer backgroundToForegroundTransformer = new BackgroundToForegroundTransformer();
    public static CubeInTransformer cubeInTransformer = new CubeInTransformer();
    public static CubeOutTransformer cubeOutTransformer = new CubeOutTransformer();
    public static DepthPageTransformer depthPageTransformer = new DepthPageTransformer();
    public static DepthPageTransformer2 depthPageTransformer2 = new DepthPageTransformer2();
    public static DrawFromBackTransformer drawFromBackTransformer = new DrawFromBackTransformer();
    public static FlipHorizontalTransformer flipHorizontalTransformer = new FlipHorizontalTransformer();
    public static FlipVerticalTransformer flipVerticalTransformer = new FlipVerticalTransformer();
    public static ForegroundToBackgroundTransformer foregroundToBackgroundTransformer = new ForegroundToBackgroundTransformer();
    public static ParallaxPageTransformer parallaxPageTransformer = new ParallaxPageTransformer(20);
    public static RotateDownTransformer rotateDownTransformer = new RotateDownTransformer();
    public static RotateUpTransformer rotateUpTransformer = new RotateUpTransformer();
    public static StackTransformer stackTransformer = new StackTransformer();
    public static TabletTransformer tabletTransformer = new TabletTransformer();
    public static ZoomInTransformer zoomInTransformer = new ZoomInTransformer();
    public static ZoomOutPageTransformer zoomOutPageTransformer = new ZoomOutPageTransformer();
    public static ZoomOutSlideTransformer zoomOutSlideTransformer = new ZoomOutSlideTransformer();
    public static ZoomOutTransformer zoomOutTransformer = new ZoomOutTransformer();
}
