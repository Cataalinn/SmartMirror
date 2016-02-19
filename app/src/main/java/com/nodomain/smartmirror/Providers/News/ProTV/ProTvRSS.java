package com.nodomain.smartmirror.Providers.News.ProTV;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Florescu George Cătălin on 13.02.2016.
 * Smart Mirror project
 */
@Root(name = "rss", strict = false)
public class ProTvRSS {
    @Element(name = "channel")
    public ProTvChannel proTvChannel;
}

