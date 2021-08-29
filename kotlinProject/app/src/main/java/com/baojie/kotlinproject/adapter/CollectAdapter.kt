package com.baojie.kotlinproject.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baojie.kotlinproject.R
import com.baojie.kotlinproject.database.Student

/**
 * @Description: TODO
 * @Author baojie@qding.me
 * @Date 2021/8/29 11:24 下午
 */
class CollectAdapter: RecyclerView.Adapter<CollectAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var tvId = itemView.findViewById<TextView>(R.id.tv_id)
        var tvName = itemView.findViewById<TextView>(R.id.tv_name)
        var tvAge = itemView.findViewById<TextView>(R.id.tv_age)
    }

    var allStudents: List<Student> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_collect_list, parent, false)
        return MyViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val student = allStudents[position]
        holder.tvId.text = "${position + 1}"
        holder.tvName.text = student.name
        holder.tvAge.text = "${student.age}"
        // 点击Item的时候，可以跳转到  有道词典 查询
        holder.itemView.setOnClickListener {
            val uri: Uri = Uri.parse("https://m.youdao.com/dict?le=eng&q=" + holder.tvName.text)
            val intent: Intent = Intent(Intent.ACTION_VIEW)
            intent.data = uri;
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return allStudents.size
    }
}