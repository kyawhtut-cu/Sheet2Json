package com.kyawhtut.test.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder
import com.kyawhtut.test.R
import com.kyawhtut.test.vo.TableRowHeaderVO
import kotlinx.android.synthetic.main.table_row_header_layout.view.*

/**
 * @author kyawhtut
 * @date 07/04/2020
 */
class TableRowHeaderViewHolder(private val view: View) : AbstractViewHolder(view) {

    private val tvRowData: TextView by lazy {
        view.tv_row_header
    }

    fun bind(data: TableRowHeaderVO) {
        tvRowData.text = data.data
        view.tv_row_header.requestLayout()
    }

    override fun setSelected(selectionState: SelectionState?) {
        super.setSelected(selectionState)
        view.setBackgroundColor(
            ContextCompat.getColor(
                view.context,
                when (selectionState) {
                    SelectionState.SELECTED -> R.color.selected_background_color
                    SelectionState.UNSELECTED -> R.color.unselected_header_background_color
                    else -> R.color.selected_background_color
                }
            )
        )
        tvRowData.setTextColor(
            ContextCompat.getColor(
                view.context,
                when (selectionState) {
                    SelectionState.SELECTED -> R.color.colorBlack
                    else -> R.color.colorBlack
                }
            )
        )
    }
}
