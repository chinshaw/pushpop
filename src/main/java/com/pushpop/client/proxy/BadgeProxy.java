package com.pushpop.client.proxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;
import com.pushpop.server.domain.Badge;

@ProxyFor(value = Badge.class)
public interface BadgeProxy extends ValueProxy {

}
