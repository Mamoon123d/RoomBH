package com.e.roomexample.activity

import android.app.Dialog
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.roomexample.R
import com.e.roomexample.adapter.ItemAdapter
import com.e.roomexample.data.model
import com.e.roomexample.database.Db
import com.e.roomexample.databinding.HomeBinding
import com.e.roomexample.factory.MainMvFactory
import com.e.roomexample.repo.Repository
import com.e.roomexample.vm.MainViewModel
import com.moon.baselibrary.base.BaseActivity
import com.moon.baselibrary.base.BaseRvAdapter

class Home : BaseActivity<HomeBinding>() {
    lateinit var mainViewModel: MainViewModel

    override fun initM() {

        setRvList()

    }

    private fun setRvList() {
        val dao = Db.getDatabase(this).myDao()
        val repository = Repository(dao)
        val rv = binding.rv
        mainViewModel =
            ViewModelProvider(this, MainMvFactory(repository))[MainViewModel::class.java]
        mainViewModel.getList().observe(this) {
            val itemAdapter = ItemAdapter(this@Home, it)
            rv.apply {
                adapter = itemAdapter
                layoutManager = LinearLayoutManager(this@Home, RecyclerView.VERTICAL, false)
                itemAdapter.setOnItemClickListener(object : BaseRvAdapter.OnItemClickListener {
                    override fun onItemClick(v: View?, position: Int) {
                        updateItem(it[position], true)

                        /* if (mainViewModel.isExistsTD(11)>0){
                             showMsg("hai")
                         }else{
                             showMsg("nahi")
                         }*/
                    }

                })
                itemAdapter.setOnItemDeleteListener(object : ItemAdapter.OnItemDeleteListener {
                    override fun onItemDelete(position: Int) {
                        mainViewModel.deleteItem(it[position])

                    }

                })
            }
        }
        binding.addItem.setOnClickListener {
            updateItem(null, false)
        }
    }

    private fun updateItem(data: model?, forUpdate: Boolean) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setTitle("Update App")
        dialog.setContentView(R.layout.update_dialog)
        val window: Window? = dialog.window
        window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        val btn = dialog.findViewById<TextView>(R.id.done)
        val tdEt = dialog.findViewById<EditText>(R.id.td_et)
        val mdEt = dialog.findViewById<EditText>(R.id.md_et)
        if (forUpdate) {
            tdEt.setText(data!!.td.toString())
            mdEt.setText(data.md.toString())
        }
        btn.setOnClickListener {
            val td = tdEt.text.trim().toString()
            val md = mdEt.text.trim().toString()
            if (forUpdate) {
                mainViewModel.updateItem(
                    model(
                        id = data!!.id,
                        td = if (td.isBlank()) 0 else td.toInt(),
                        md = if (md.isBlank()) 0 else md.toInt()
                    )
                )

            } else {
                mainViewModel.insertItem(
                    model(
                        td = if (td.isBlank()) 0 else td.toInt(),
                        md = if (md.isBlank()) 0 else md.toInt()
                    )
                )
            }
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun setLayoutId(): Int {
        return R.layout.home
    }
}