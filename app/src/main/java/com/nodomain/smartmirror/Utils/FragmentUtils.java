package com.nodomain.smartmirror.Utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Florescu George Cătălin on 03.02.2016.
 * Smart Mirror project
 */
public class FragmentUtils {

    public static void addFragment(FragmentActivity activity, Fragment fragment, int content) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(content, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
