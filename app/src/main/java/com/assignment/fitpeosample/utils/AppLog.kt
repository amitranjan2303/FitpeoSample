package com.assignment.fitpeosample.utils

import android.os.Build
import android.util.Log

class AppLog {

    companion object{
        fun d(tag:String?,message:String){
            Log.d(tag,message)
        }
        fun e(tag:String?,message:String){
            Log.e(tag,message)
        }
        fun w(tag:String?,message:String){
            Log.v(tag,message)
        }
    }
}