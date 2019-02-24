package cc.catface.base.core_framework.base_mvp.factory;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import cc.catface.base.core_framework.base_mvp.presenter.MvpPresenter;

/**
 * @author 刘镓旗[2017.11][https://blog.csdn.net/yulong0809/article/details/78622428]
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface CreatePresenter {
    Class<? extends MvpPresenter> value();
}
