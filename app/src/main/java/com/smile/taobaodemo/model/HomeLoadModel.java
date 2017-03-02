package com.smile.taobaodemo.model;

import com.smile.taobaodemo.model.entity.HomeBottom;
import com.smile.taobaodemo.model.entity.HomeTop;
import com.smile.taobaodemo.presenter.OnLoadListener;

/**
 * @author Smile Wei
 * @since 2016/9/22.
 */

public interface HomeLoadModel extends LoadModel {

    void load(OnLoadListener<HomeTop> listener, int type);

    void load(OnLoadListener<HomeBottom> listener, int type, int page, int pageSize);
}
