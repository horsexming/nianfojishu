package com.task.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.*;
import org.apache.http.util.EntityUtils;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;


public class ResponseHander {
    private static ResponseHander instance;
    private ResponseHandler<byte[]> defaultHandler = null;
    private ResponseHander() {
    }
    public static synchronized ResponseHander getInstance() {
        if (instance == null) {
            instance = new ResponseHander();
        }
        return instance;
    }
    public synchronized ResponseHandler<byte[]> getDefault() {
        if (defaultHandler == null) {
            defaultHandler = new ResponseHandler<byte[]>() {
                public byte[] handleResponse(HttpResponse response)
                        throws ClientProtocolException, IOException {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        return EntityUtils.toByteArray(entity);
                    } else {
                        return null;
                    }
                }
            };
        }
        return defaultHandler;
    }
 
    public String toString(HttpResponse response, String encode)
            throws ParseException, IOException {
        HttpEntity entity = response.getEntity();
        return (entity != null) ? EntityUtils.toString(entity, encode)
                : "Error datas";
    }
    public byte[] toBytes(HttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        return (entity != null) ? EntityUtils.toByteArray(entity) : null;
    }
    public InputStream toStream(HttpResponse response)
            throws IllegalStateException, IOException {
        HttpEntity entity = response.getEntity();
        return (entity != null) ? entity.getContent() : null;
    }
    public void showHeaders(HttpResponse response) {
        Header[] headers = response.getAllHeaders();
        for (Header h : headers) {
            System.out.println(h.getName() + ":" + h.getValue());
        }
    }
 
    public String getHeaders(HttpResponse response) {
        StringBuffer sb = new StringBuffer();
        Header[] headers = response.getAllHeaders();
        for (Header h : headers) {
        }
        return sb.toString();
    }
}
