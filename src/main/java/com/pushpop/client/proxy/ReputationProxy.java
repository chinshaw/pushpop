package com.pushpop.client.proxy;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;
import com.pushpop.server.domain.Reputation;

@ProxyFor(value= Reputation.class)
public interface ReputationProxy  extends ValueProxy{

    
    public Integer getReputationScore();

    public List<BadgeProxy> getBadges();
}
