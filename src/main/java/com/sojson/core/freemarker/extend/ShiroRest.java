package com.sojson.core.freemarker.extend;

import freemarker.template.SimpleHash;

public class ShiroRest extends SimpleHash {
    public ShiroRest(){
        this.put("urlType",new TestTag());
    }

}
