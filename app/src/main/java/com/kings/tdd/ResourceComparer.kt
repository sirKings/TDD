package com.kings.tdd

import android.content.Context

class ResourceComparer {

    fun compareString(context: Context, res: Int, string: String): Boolean{
        return context.getString(res) == string
    }
}