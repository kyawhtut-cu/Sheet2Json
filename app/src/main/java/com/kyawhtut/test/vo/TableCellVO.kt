package com.kyawhtut.test.vo

import com.evrencoskun.tableview.filter.IFilterableModel
import com.evrencoskun.tableview.sort.ISortableModel

/**
 * @author kyawhtut
 * @date 07/04/2020
 */
data class TableCellVO private constructor(
    val cellId: String,
    val data: Any
) : ISortableModel, IFilterableModel {

    override fun getFilterableKeyword(): String {
        return when (data) {
            is String -> data
            else -> ""
        }
    }

    override fun getContent(): Any {
        return data
    }

    override fun getId(): String {
        return cellId
    }

    class Builder {
        var cellId: String = ""
        var data: Any = Object()

        fun build() = TableCellVO(cellId, data)
    }
}

class TableCellVOList : ArrayList<TableCellVO>() {
    fun tableCell(block: TableCellVO.Builder.() -> Unit) {
        add(TableCellVO.Builder().apply(block).build())
    }
}

fun tableCellList(block: TableCellVOList.() -> Unit) = TableCellVOList().apply(block)

fun tableCell(block: TableCellVO.Builder.() -> Unit) = TableCellVO.Builder().apply(block).build()
