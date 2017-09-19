
/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.martin.basic.library.app

/**
 * 资源类型
 * Created by Martin on 2017/9/19.
 */
enum class ResourceType(val type: String) {
    ANIM("anim"),
    ATTR("attr"),
    DIMEN("dimen"),
    DRAWABLE("drawable"),
    ID("id"),
    INTEGER("integer"),
    LAYOUT("layout"),
    STRING("string"),
    STYLEABLE("styleable"),
    COLOR("color"),
    BOOL("bool")
}