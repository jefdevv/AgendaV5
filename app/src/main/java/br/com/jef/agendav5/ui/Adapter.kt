package br.com.jef.agendav5.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.jef.agendav5.R
import br.com.jef.agendav5.ui.utils.listener.AgendaListener


class Adapter(
    private val listName: MutableList<String>,
    private val listTelephone: MutableList<String>,
    private val listGender: MutableList<String>,
    private val listPhoneType: MutableList<String>,
    private val listener: AgendaListener
) :
    RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount() = listName.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val name = listName[position]
        val telephone = listTelephone[position]
        val gender = listGender[position]
        val phoneType = listPhoneType[position]

        holder.txtName.text = name
        holder.txtTelephone.text = telephone
        holder.txtGender.text = gender
        holder.txtPhoneType.text = phoneType

        holder.imgRemove.setOnClickListener() {
            listName.remove(name)
            listTelephone.remove(telephone)
            listGender.remove(gender)
            notifyDataSetChanged()
        }

        holder.itemView.setOnClickListener() {
            listener.onClick(name, phoneType, telephone, gender)

        }


    }
}

class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val txtName = view.findViewById<TextView>(R.id.txtNameRow)
    val txtTelephone = view.findViewById<TextView>(R.id.txtNumberRow)
    val txtGender = view.findViewById<TextView>(R.id.txtGenderRow)
    val txtPhoneType = view.findViewById<TextView>(R.id.txtPhoneType)
    val imgRemove = view.findViewById<ImageView>(R.id.imgRemove)

}
