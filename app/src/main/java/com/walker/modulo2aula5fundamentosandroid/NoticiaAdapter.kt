package com.walker.modulo2aula5fundamentosandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.walker.modulo2aula5fundamentosandroid.databinding.ItemListBinding

class NoticiaAdapter(
    private val listaNoticia: List<NoticiaModel>,
    private val glide: RequestManager,
    private val onItemClickListener: OnItemClickListener
): RecyclerView.Adapter<NoticiaAdapter.ViewHolder>() {

    private lateinit var binding: ItemListBinding

    inner class ViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val titulo: TextView = binding.tituloNoticia
        val descricao: TextView = binding.corpoNoticia
        val autor: TextView = binding.nomeAutor
        val autorImagem: ImageView = binding.imagemAutor
        val noticiaImagem: ImageView = binding.noticiaImagem
        val data: TextView = binding.dataNoticia
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return listaNoticia.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemAtual = listaNoticia[position]

        holder.titulo.text = itemAtual.tituloNoticia
        holder.descricao.text = itemAtual.corpoNoticia
        holder.autor.text = itemAtual.nomeAutor
        holder.data.text = itemAtual.dataNoticia

        holder.itemView.setOnClickListener {
            onItemClickListener.abrirLink(itemAtual.link)
        }

        glide.load(itemAtual.imagemAutorUrl)
            .into(holder.autorImagem)

        glide.load(itemAtual.imagemNoticaUrl)
            .into(holder.noticiaImagem)
    }

}