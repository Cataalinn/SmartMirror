package com.nodomain.smartmirror.Providers.News.ProTV;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by Florescu George Cătălin on 13.02.2016.
 * Smart Mirror project
 */
@Root(name = "enclosure", strict = false)
public class ProTvEnclosure {
    @Attribute(name = "url")
    public String imageUrl;
}
