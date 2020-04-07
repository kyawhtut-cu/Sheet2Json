package com.kyawhtut.test.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.evrencoskun.tableview.adapter.AbstractTableAdapter
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder
import com.kyawhtut.test.R
import com.kyawhtut.test.adapter.viewholder.TableCellItemViewHolder
import com.kyawhtut.test.adapter.viewholder.TableColumnHeaderViewHolder
import com.kyawhtut.test.adapter.viewholder.TableRowHeaderViewHolder
import com.kyawhtut.test.vo.TableCellVO
import com.kyawhtut.test.vo.TableColumnHeaderVO
import com.kyawhtut.test.vo.TableRowHeaderVO

/**
 * @author kyawhtut
 * @date 07/04/2020
 */
class TableAdapter(private val ctx: Context) :
    AbstractTableAdapter<TableColumnHeaderVO, TableRowHeaderVO, TableCellVO>(ctx) {

    override fun onCreateCellViewHolder(parent: ViewGroup?, viewType: Int): AbstractViewHolder {
        return TableCellItemViewHolder(
            LayoutInflater.from(ctx).inflate(R.layout.table_cell_item_layout, parent, false)
        )
    }

    override fun onBindCellViewHolder(
        holder: AbstractViewHolder,
        cellItemModel: Any?,
        columnPosition: Int,
        rowPosition: Int
    ) {
        when (holder) {
            is TableCellItemViewHolder -> {
                holder.bind(cellItemModel as TableCellVO, columnPosition)
            }
        }
    }

    override fun onCreateColumnHeaderViewHolder(
        parent: ViewGroup?,
        viewType: Int
    ): AbstractViewHolder {
        return TableColumnHeaderViewHolder(
            LayoutInflater.from(ctx).inflate(
                R.layout.table_column_header_layout,
                parent,
                false
            ),
            tableView
        )
    }

    override fun onBindColumnHeaderViewHolder(
        holder: AbstractViewHolder?,
        columnHeaderItemModel: Any?,
        columnPosition: Int
    ) {
        when (holder) {
            is TableColumnHeaderViewHolder -> holder.bind(
                columnHeaderItemModel as TableColumnHeaderVO,
                columnPosition
            )
        }
    }

    override fun onCreateRowHeaderViewHolder(
        parent: ViewGroup?,
        viewType: Int
    ): AbstractViewHolder {
        return TableRowHeaderViewHolder(
            LayoutInflater.from(ctx).inflate(
                R.layout.table_row_header_layout,
                parent,
                false
            )
        )
    }

    override fun onBindRowHeaderViewHolder(
        holder: AbstractViewHolder?,
        rowHeaderItemModel: Any?,
        rowPosition: Int
    ) {
        (holder as TableRowHeaderViewHolder).bind(rowHeaderItemModel as TableRowHeaderVO)
    }

    override fun onCreateCornerView(): View {
        return LayoutInflater.from(ctx).inflate(
            R.layout.table_corner_layout, null, false
        )
    }

    override fun getColumnHeaderItemViewType(position: Int): Int {
        return getColumnHeaderType(position)
    }

    override fun getRowHeaderItemViewType(position: Int): Int {
        return 0
    }

    override fun getCellItemViewType(position: Int): Int = 0

    private fun getColumnHeaderType(column: Int): Int = 0
}
