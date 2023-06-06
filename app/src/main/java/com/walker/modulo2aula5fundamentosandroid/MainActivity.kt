package com.walker.modulo2aula5fundamentosandroid

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.walker.modulo2aula5fundamentosandroid.databinding.ActivityMainBinding
import org.json.JSONArray

class MainActivity : AppCompatActivity(), OnItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private var listaNoticias: List<NoticiaModel> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v(this.javaClass.simpleName, "onCreate")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listaNoticias = lerJson(this)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = NoticiaAdapter(listaNoticias, Glide.with(this), this@MainActivity)
    }

    private fun lerJson(context: Context): List<NoticiaModel> {
        val listaTemp = mutableListOf<NoticiaModel>()
        Log.w(this.javaClass.simpleName, "lerJson: Atenção! Essa operação pode resultar numa exceção")

        try {
            val json: String =
                context.assets.open("data.json").bufferedReader().use { it.readText() }
            val jsonArray = JSONArray(json)

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val authorName = jsonObject.getString("authorName")
                val authorImageUrl = jsonObject.getString("authorImageUrl")
                val newsImageUrl = jsonObject.getString("newsImageUrl")
                val newsTitle = jsonObject.getString("newsTitle")
                val newsDescription = jsonObject.getString("newsDescription")
                val newsDate = jsonObject.getString("newsDate")
                val link = jsonObject.getString("link")

                val noticia = NoticiaModel(
                    authorName,
                    authorImageUrl,
                    newsImageUrl,
                    newsTitle,
                    newsDescription,
                    link,
                    newsDate
                )

                Log.d(this.javaClass.simpleName, "Objeto de titulo $newsTitle foi criado com sucesso!")
                listaTemp.add(noticia)
            }
        } catch (error: Exception) {
            Log.e(this.javaClass.simpleName, "Mensagem de erro: ${error.message}")
        }

        return listaTemp
    }

    override fun abrirLink(link: String) {
        Log.i(this.javaClass.simpleName, "Novo link $link foi aberto.")
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(link)
        startActivity(intent)
    }
}