package com.zjhl.pad.presenter.okhttp;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

/**
 * @desc: DecodeRequestBodyConverter
 * @version: v1.0
 * @packagename: com.zjhl.pad.presenter.okhttp
 * @author: pluto
 * @create: 2018/7/20 10:39
 * @projectname: nnkj
 **/
public class DecodeRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final Gson gson;
    private final TypeAdapter<T> adapter;
    DecodeRequestBodyConverter(Gson gson,TypeAdapter<T> adapter){
        this.gson = gson;
        this.adapter = adapter;
    }
    @Override
    public RequestBody convert(T value) throws IOException {
        Buffer buffer = new Buffer();
        Writer writer = new OutputStreamWriter(buffer.outputStream(),UTF_8);
        JsonWriter jsonWriter = gson.newJsonWriter(writer);
        adapter.write(jsonWriter,value);
        jsonWriter.flush();
        return RequestBody.create(MEDIA_TYPE,buffer.readByteString());
    }
}
