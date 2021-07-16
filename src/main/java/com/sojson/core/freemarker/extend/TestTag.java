package com.sojson.core.freemarker.extend;

import com.jagregory.shiro.freemarker.SecureTag;
import com.sojson.common.model.UPermission;
import com.sojson.core.shiro.token.SampleRealm;
import com.sojson.core.shiro.token.manager.TokenManager;
import freemarker.core.Environment;
import freemarker.log.Logger;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class TestTag extends SecureTag {

    @Override
    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        Object url=params.get("url");
        Object type=params.get("type");
        if (authUrl(url.toString(),type.toString())) {
            renderBody(env, body);
        }

    }
    private Boolean authUrl(String uri,String type) {
        boolean mark = false;
        String username = TokenManager.getNickname();
        Set<UPermission> set = (Set<UPermission>)TokenManager.getVal2Session("mark");
        for (UPermission upermission : set) {
            if (uri.equals(upermission.getUrl()) && (upermission.getType().equals("ALL") || upermission.getType().equals(type))) {
                mark = true;
                break;
            }
        }
        return mark;
    }
}