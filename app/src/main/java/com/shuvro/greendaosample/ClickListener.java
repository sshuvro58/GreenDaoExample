package com.shuvro.greendaosample;

import android.view.View;

/**
 * Author:Mithun Sarker Shuvro
 */

public interface ClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}