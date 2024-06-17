package com.stadistic.infostatisticsms.utils.impl;

import com.stadistic.infostatisticsms.utils.CurrentCredential;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.Objects;

@Service
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CurrentUserCredentialsImpl implements CurrentCredential {

    private String authorization;
    @Override
    public void setUserCredentials(String authorization) {
        if (Objects.nonNull(this.authorization)) {
            return;
        }
        this.authorization = authorization;

    }

    @Override
    public String getAuthorization() {
        return "";
    }
}
