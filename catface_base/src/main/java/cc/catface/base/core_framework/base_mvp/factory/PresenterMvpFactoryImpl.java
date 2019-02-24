package cc.catface.base.core_framework.base_mvp.factory;

import cc.catface.base.core_framework.base_mvp.presenter.MvpPresenter;
import cc.catface.base.core_framework.base_mvp.view.MvpView;

public class PresenterMvpFactoryImpl<V extends MvpView, P extends MvpPresenter<V>> implements PresenterMvpFactory<V, P> {

    private final Class<P> mPresenterClass;

    private PresenterMvpFactoryImpl(Class<P> presenterClass) {
        this.mPresenterClass = presenterClass;
    }


    public static <V extends MvpView, P extends MvpPresenter<V>> PresenterMvpFactoryImpl<V, P> createFactory(Class<?> viewClazz) {
        CreatePresenter annotation = viewClazz.getAnnotation(CreatePresenter.class);
        Class<P> clz = null;
        if (annotation != null) {
            clz = (Class<P>) annotation.value();
        }
        return clz == null ? null : new PresenterMvpFactoryImpl<>(clz);
    }


    @Override public P createMVPPresenter() {
        try {
            return mPresenterClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("检查是否已声明@CreatePresenter(xx.class)注解");
        }
    }
}
