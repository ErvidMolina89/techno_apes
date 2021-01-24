package com.example.apes_techno.nuevo.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.apes_techno.DataAccess.DBLocal.ModelsDB.Images
import com.example.apes_techno.DataAccess.DBLocal.ModelsDB.Item

class NewMovie {
        var id                  : Int? = null
        var name                : String? = null
        var rating              : String? = null
        var release_date        : String? = null
        var runtime             : String? = null
        var total_revenue       : String? = null
        var box_office_revenue  : String? = null
        var budget              : String? = null
        var description         : String? = null
        var site_detail_url     : String? = null
        var api_detail_url      : String? = null
        var image               : Images? = null
        var producers           : MutableList<Item>? = null
        var studios             : MutableList<Item>? = null
        var writers             : MutableList<Item>? = null
}