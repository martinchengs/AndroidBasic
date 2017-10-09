/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.martin.basic.library.widget.navigation

import android.content.Context

/**
 * Depict:
 * Author: Martin
 * Create:2017-10-09 14:36
 */
class NavData(name: String, res: Int) {
    constructor(context: Context, nameRes: Int, res: Int) : this(context.getString(nameRes), res)
}