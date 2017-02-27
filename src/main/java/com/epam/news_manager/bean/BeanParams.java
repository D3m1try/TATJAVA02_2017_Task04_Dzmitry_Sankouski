package com.epam.news_manager.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dzmitry_Sankouski on 27-Feb-17.
 */
public class BeanParams {
    private Map<String, String> params = new HashMap<>();

    public BeanParams() {

    }

    public Map<String, String> getParams() {
        return params;
    }
}
