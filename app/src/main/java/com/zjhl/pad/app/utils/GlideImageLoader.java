package com.zjhl.pad.app.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.youth.banner.loader.ImageLoader;

/**
 * @desc: GlideImageLoader
 * @version: v1.0
 * @packagename: com.zjhl.pad.app.utils
 * @author: pluto
 * @create: 2018/4/28 10:26
 * @projectname: nnkj
 **/
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        /**
         注意：
         1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
         2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
         传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
         切记不要胡乱强转！
         */

        //Glide 加载图片简单用法
        Glide.with(context).load(path).into(imageView);

        //Picasso 加载图片简单用法
//        Picasso.with(context).load(path).into(imageView);

        //用fresco加载图片简单用法，记得要写下面的createImageView方法
//        Uri uri = Uri.parse((String) path);
//        imageView.setImageURI(uri);
    }

    //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
//    @Override
//    public ImageView createImageView(Context context) {
//        //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
//        SimpleDraweeView simpleDraweeView=new SimpleDraweeView(context);
//        return simpleDraweeView;
//    }

    public static void displayImage0(Context context, Object path, int id, ImageView imageView) {
        /**
         注意：
         1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
         2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
         传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
         切记不要胡乱强转！
         */
        //声明一个SimpleTarget
        SimpleTarget target = new SimpleTarget<Bitmap>(400,400) {

            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {

            }

        };
//        RequestBuilder<Drawable> thumbnailRequest = Glide
//                .with(context)
//                .load(id);
//        Glide.with(context)
//                .load(url)
//                .thumbnail(thumbnailRequest)
//                .into(view);
        //设置默认和出错时的图片
//        Glide.with(context).load(path).placeholder(resId).error(resId).into(imageView);
        //普通的图片加载
        Glide.with(context).load(path).thumbnail(Glide.with(context).load(id)).into(imageView);
        //可理解为加载动态图的第一帧的Bitmap,比如Gif
//        Glide.with(context).load(path).asBitmap().into(imageView);
        //GIF加载，URL指向的资源必须是gif，如果是普通图片则不显示。
        //相反，如果指向正确但没有执行asGif方法，则只是作为普通图片展示
//        Glide.with(context).asGif().load(path).into(imageView);
        //缩略图的加载
//        Glide.with(context).load(path).thumbnail(0.1f).into(imageView);

    }
}
