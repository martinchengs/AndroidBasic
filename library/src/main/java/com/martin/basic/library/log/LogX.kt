package com.martin.basic.library.log

import android.support.annotation.IntDef
import android.util.Log
import com.martin.common.util.HSON
import java.text.Format
import java.text.SimpleDateFormat
import java.util.*

/**
 * Log 工具类
 * Created by Martin on 2017/9/5.
 */
object LogX {
    private const val V = Log.VERBOSE.toLong()
    private const val D = Log.DEBUG.toLong()
    private const val I = Log.INFO.toLong()
    private const val W = Log.WARN.toLong()
    private const val E = Log.ERROR.toLong()
    private const val A = Log.ASSERT.toLong()

    private var loggable: Boolean = true
    private var logTag: String = "LogX"
    private var stackDeep = 2 //log栈深度

    private var consoleFlag = V

    @IntDef(V, D, I, W, E, A)
    @Retention(AnnotationRetention.SOURCE)
    annotation class Type

    val LINE_T = System.getProperty("line.separator")

    val TOP_LINE = "╔═══════════════════════════════════════════════════════════════════════════════════════════════════"
    val SPLIT_LINE = "╟───────────────────────────────────────────────────────────────────────────────────────────────────"
    val LEFT_LINE = "║"
    val BOTTOM_LINE = "╚═══════════════════════════════════════════════════════════════════════════════════════════════════"
    val MAX_LENGTH = 4000
    val FORMAT: Format = SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.getDefault())
    val NULL_ERROR = "Log with null object."
    val ARGS = "args"

    fun i(message: Any?) {
        log(I.toInt(), logTag, message)
    }

    fun i(tag: String, message: Any?) {
        log(I.toInt(), tag, message)
    }

    fun d(message: Any?) {
        log(D.toInt(), logTag, message)
    }

    fun d(tag: String, message: Any?) {
        log(D.toInt(), tag, message)
    }

    fun json(message: Any?) {
        log(V.toInt(), logTag, HSON.to(message))
    }

    fun json(tag: String, message: Any?) {
        log(V.toInt(), tag, HSON.to(message))
    }

    fun v(message: Any?) {
        log(V.toInt(), logTag, message)
    }

    fun v(tag: String, message: Any?) {
        log(V.toInt(), tag, message)
    }


    fun e(message: Any?) {
        log(E.toInt(), logTag, message)
    }

    fun e(tag: String, message: Any?) {
        log(E.toInt(), tag, message)
    }


    fun w(message: Any?) {
        log(W.toInt(), logTag, message)
    }

    fun w(tag: String, message: Any?) {
        log(W.toInt(), tag, message)
    }

    fun a(message: Any?) {
        log(A.toInt(), logTag, message)
    }

    fun a(tag: String, message: Any?) {
        log(A.toInt(), tag, message)
    }


    private fun log(@Type type: Int, tag: String, vararg contents: Any?) {
        if (!loggable) return
        val type_low = type.and(0x0f)
        if (type_low < consoleFlag) return
        val tagHead: TAGHead = processTagAndHead(tag)
        val body = processBody(contents)
        printToConsole(type_low, tagHead.tag, tagHead.args, body)

    }

    private fun printToConsole(type_low: Int, tag: String, args: Array<String?>, body: Any) {
        printBoard(type_low, tag, true)
        printHead(type_low, tag, args)
        printMsg(type_low, tag, body)
        printBoard(type_low, tag, false)
    }

    private fun printMsg(type_low: Int, tag: String, body: Any) {
        val message = body.toString()
        val length = message.length
        val countOfSub = length / MAX_LENGTH
        if (countOfSub > 0) {
            val i = 0
            val index = 0
            while (i < countOfSub) {
                printSubMsg(type_low, tag, message.substring(index, index + MAX_LENGTH))
            }
            if (index != length) {
                printSubMsg(type_low, tag, message.substring(index, length))
            }
        } else {
            printSubMsg(type_low, tag, message)
        }
    }

    private fun printSubMsg(type_low: Int, tag: String, message: String) {
        val lines = message.split(LINE_T)
        for (line in lines) {
            Log.println(type_low, tag, "$LEFT_LINE$line")
        }
    }

    private fun printBoard(type: Int, tag: String, top: Boolean) {
        Log.println(type, tag, if (top) TOP_LINE else BOTTOM_LINE)
    }

    private fun printHead(type: Int, tag: String, args: Array<String?>) {
        for (msg in args) {
            Log.println(type, tag, "$LEFT_LINE$msg")
        }
        Log.println(type, tag, SPLIT_LINE)
    }

    private fun processBody(contents: Array<out Any?>): Any {
        val body: String
        if (contents.size == 1) {
            val obj = contents[0]
            body = obj?.toString() ?: NULL_ERROR
        } else {
            val sb = StringBuilder()
            for (i in contents.indices) {
                val obj = contents[i]
                sb.append(ARGS)
                        .append("[")
                        .append(i)
                        .append("]")
                        .append("=")
                        .append(obj?.toString() ?: NULL_ERROR)
                        .append(LINE_T)
            }
            body = sb.toString()
        }
        return body
    }

    private fun processTagAndHead(tag: String): TAGHead {
        val stackTrace = Throwable().stackTrace
        var element = stackTrace[3]
        val fileName = element.fileName
        val tName = Thread.currentThread().name
        val head = "(thread->$tName)::${element.className}.${element.methodName}($fileName:${element.lineNumber})"
        if (stackDeep <= 1) {
            return TAGHead(tag, arrayOf(head))
        } else {
            val consoleHead = Array<String?>(Math.min(stackDeep, stackTrace.size - 3), { null })
            consoleHead[0] = head
            val spaceLength = tName.length + 12
            val space = Formatter().format("%" + spaceLength + "s", "").toString()
            var i = 1
            val len = consoleHead.size
            while (i < len) {
                element = stackTrace[i + 3]
                consoleHead[i] = "$space${element.methodName}($fileName:${element.lineNumber})"
                ++i
            }
            return TAGHead(tag, consoleHead)
        }
    }

    private class TAGHead(val tag: String,
                          val args: Array<String?>)
}