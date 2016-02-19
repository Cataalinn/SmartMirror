package com.nodomain.smartmirror.Providers.News.Digi24;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by Florescu George Cătălin on 13.02.2016.
 * Smart Mirror project
 */
@Root(name = "enclosure", strict = false)
public class Digi24Enclosure {
    @Attribute(name = "url")
    public String imageUrl;
}
