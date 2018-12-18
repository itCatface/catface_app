package cc.catface.base.core_framework.base_mvp.factory;

import cc.catface.base.core_framework.base_mvp.presenter.BaseMVPPresenter;
import cc.catface.base.core_framework.base_mvp.view.BaseMVPView;

public class PresenterMVPFactoryImpl<V extends BaseMVPView, P extends BaseMVPPresenter<V>> implements PresenterMVPFactory<V, P> {

    private final Class<P> mPresenterClass;

    private PresenterMVPFactoryImpl(Class<P> presenterClass) {
        this.mPresenterClass = presenterClass;
    }


    public static <V extends BaseMVPView, P extends BaseMVPPresenter<V>> PresenterMVPFactoryImpl<V, P> createFactory(Class<?> viewClazz) {
        CreatePresenter annotation = viewClazz.getAnnotation(CreatePresenter.class);
        Class<P> clz = null;
        if (annotation != null) {
            clz = (Class<P>) annotation.value();
        }
        return clz == null ? null : new PresenterMVPFactoryImpl<>(clz);
    }


    @Override public P createMvpPresenter() {
        try {
            return mPresenterClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("检查是否已声明@CreatePresenter(xx.class)注解");
        }
    }
}
