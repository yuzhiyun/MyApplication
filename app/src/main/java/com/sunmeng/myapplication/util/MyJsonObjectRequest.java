package com.sunmeng.myapplication.util;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;


/**
 * Created by yuzhiyun on 2017-01-12.
 */
public class MyJsonObjectRequest extends JsonRequest<JSONObject> {
    private String stringRequest;

    public MyJsonObjectRequest(String url, String stringRequest,
                               Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(Method.POST, url,stringRequest , listener, errorListener);
        //这个是用来构造POST的表头
        this.stringRequest = stringRequest;
    }

    @Override
    public String getBodyContentType() {
        return "application/x-www-form-urlencoded; charset=" + getParamsEncoding();
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }
}
