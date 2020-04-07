package com.kyawhtut.test.adapter.viewholder

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.evrencoskun.tableview.ITableView
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractSorterViewHolder
import com.evrencoskun.tableview.sort.SortState
import com.kyawhtut.test.R
import com.kyawhtut.test.vo.TableColumnHeaderVO
import kotlinx.android.synthetic.main.table_column_header_layout.view.*

/**
 * @author kyawhtut
 * @date 07/04/2020
 */
class TableColumnHeaderViewHolder(private val view: View, private val table: ITableView) :
    AbstractSorterViewHolder(view) {

    private val container: ConstraintLayout by lazy {
        view.column_header_container
    }
    private val tvCellData: TextView by lazy {
        view.column_header_textView
    }

    fun bind(data: TableColumnHeaderVO, columnPosition: Int) {
        tvCellData.apply {
            text = data.data
        }
        container.layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT
        container.maxWidth = 400
        requestLayout()
    }

    private fun requestLayout() {
        tvCellData.requestLayout()
        container.requestLayout()
        view.requestLayout()
    }

    override fun setSelected(selectionState: SelectionState?) {
        super.setSelected(selectionState)

        container.setBackgroundColor(
            ContextCompat.getColor(
                view.context,
                when (selectionState) {
                    SelectionState.SELECTED -> R.color.selected_background_color
                    SelectionState.UNSELECTED -> R.color.unselected_background_color
                    else -> R.color.unselected_background_color
                }
            )
        )
        tvCellData.setTextColor(
            ContextCompat.getColor(
                view.context,
                when (selectionState) {
                    SelectionState.SELECTED -> R.color.colorBlack
                    else -> R.color.colorBlack
                }
            )
        )
    }

    override fun onSortingStatusChanged(pSortState: SortState) {
        super.onSortingStatusChanged(pSortState)
        container.layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT

        controlSortState(pSortState)

        requestLayout()
    }

    private fun controlSortState(stortState: SortState) {
        /*btnSort.apply {
            visibility = View.GONE
            when (stortState) {
                SortState.ASCENDING -> {
                    setImageResource(R.drawable.ic_arrow_drop_down_black)
                }
                SortState.DESCENDING -> {
                    setImageResource(R.drawable.ic_arrow_drop_up_black)
                }
                else -> visibility = View.GONE
            }
        }*/
    }
}
