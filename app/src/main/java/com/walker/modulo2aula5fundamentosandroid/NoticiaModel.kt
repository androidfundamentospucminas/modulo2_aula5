package com.walker.modulo2aula5fundamentosandroid

data class NoticiaModel(
    val nomeAutor: String,
    val imagemAutorUrl: String,
    val imagemNoticaUrl: String,
    val tituloNoticia: String,
    val corpoNoticia: String,
    val link: String,
    val dataNoticia: String
)