package com.kyawhtut.test.adapter.viewholder

import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder
import com.kyawhtut.test.R
import com.kyawhtut.test.vo.TableCellVO
import kotlinx.android.synthetic.main.table_cell_item_layout.view.*

/**
 * @author kyawhtut
 * @date 07/04/2020
 */
class TableCellItemViewHolder(private val view: View) : AbstractViewHolder(view) {

    fun bind(data: TableCellVO, columnPosition: Int) {
        view.tv_cell_data.apply {
            text = data.data as String
        }
        requestLayout()
    }

    private fun requestLayout() {
        view.tv_cell_data.layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT
        view.tv_cell_data.requestLayout()
    }

    override fun setSelected(selectionState: SelectionState) {
        super.setSelected(selectionState)

        view.tv_cell_data.setTextColor(
            ContextCompat.getColor(
                view.context,
                if (selectionState == SelectionState.SELECTED) R.color.colorBlack
                else R.color.colorBlack
            )
        )
    }
}
