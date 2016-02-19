package com.nodomain.smartmirror.Providers.News.Digi24;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Florescu George Cătălin on 13.02.2016.
 * Smart Mirror project
 */
@Root(name = "item", strict = false)
public class Digi24Items {
    @Element(name = "title")
    public String title;

    @Element(name = "enclosure", required = true)
    public Digi24Enclosure imageUrl;

    @Element(name = "pubDate")
    public String publicationDate;
}
