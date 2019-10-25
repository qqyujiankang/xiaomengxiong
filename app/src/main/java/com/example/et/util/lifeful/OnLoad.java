package com.example.et.util.lifeful;

/**
 * Created by ywx on 2018/5/18.
 */


public interface OnLoad<T> {

    /**
     * Return info when request success
     *
     * @param success success detail
     */
    void onSuccess(T success);

    /**
     * Return info when request fail
     *
     * @param error error detail
     */
    void onError(String error);
}