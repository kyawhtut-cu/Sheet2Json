package com.kyawhtut.test

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kyawhtut.sheet2json.Sheet2Json
import com.kyawhtut.test.adapter.TableAdapter
import com.kyawhtut.test.vo.columnHeaderList
import com.kyawhtut.test.vo.rowHeader
import com.kyawhtut.test.vo.tableCellList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val tableAdapter by lazy { TableAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Sheet2Json.init()
        setContentView(R.layout.activity_main)

        table_view.apply {
            setHasFixedWidth(false)
            adapter = tableAdapter
        }

        Sheet2Json.get("1bA0N-e5n-kLnMGE3isMMOv6lAOKDRBELF6YWJBlZmbQ")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val peopleList = Gson().fromJson<List<People>>(
                        it,
                        object : TypeToken<List<People>>() {}.type
                    )
                    tableAdapter.setAllItems(
                        columnHeaderList {
                            columnHeader {
                                data = "ID"
                            }
                            columnHeader {
                                data = "Name"
                            }
                            columnHeader {
                                data = "Age"
                            }
                            columnHeader {
                                data = "Address"
                            }
                            columnHeader {
                                data = "Remark"
                            }
                        },
                        peopleList.mapIndexed { index, _ ->
                            rowHeader {
                                data = "${index + 1}"
                            }
                        },
                        peopleList.mapIndexed { index, people ->
                            tableCellList {
                                tableCell {
                                    cellId = "$index"
                                    data = people.id.toString()
                                }
                                tableCell {
                                    cellId = "$index"
                                    data = people.name
                                }
                                tableCell {
                                    cellId = "$index"
                                    data = people.age.toString()
                                }
                                tableCell {
                                    cellId = "$index"
                                    data = people.address
                                }
                                tableCell {
                                    cellId = "$index"
                                    data = people.remark
                                }
                            }
                        }
                    )
                },
                {
                    it.printStackTrace()
                }
            )
            .isDisposed
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
