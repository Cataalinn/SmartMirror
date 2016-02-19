package com.nodomain.smartmirror.Providers.News.Digi24;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Florescu George Cătălin on 13.02.2016.
 * Smart Mirror project
 */
@Root(name = "rss", strict = false)
public class Digi24RSS {
    @Element(name = "channel")
    public Digi24Channel digi24Channel;
}

