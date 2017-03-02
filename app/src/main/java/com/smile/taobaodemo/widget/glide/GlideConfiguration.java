package com.smile.taobaodemo.widget.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.module.GlideModule;
import com.smile.taobaodemo.utils.FileUtil;

/**
 * @author Smile Wei
 * @since 2016/2/25.
 */
public class GlideConfiguration implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        if (!Glide.isSetup()) {
            GlideBuilder gb = new GlideBuilder(context);
            DiskCache cache = DiskLruCacheWrapper.get(FileUtil.getCacheFolder(), 1000 * 1024 * 1024);
            gb.setDiskCache(cache);
            Glide.setup(gb);
        }
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
