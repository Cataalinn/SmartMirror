package com.nodomain.smartmirror.Providers.News.ProTV;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Florescu George Cătălin on 13.02.2016.
 * Smart Mirror project
 */
@Root(name = "channel", strict = false)
public class ProTvChannel {
    @ElementList(name = "item", inline = true)
    public List<ProTvItems> proTvItems;
}
