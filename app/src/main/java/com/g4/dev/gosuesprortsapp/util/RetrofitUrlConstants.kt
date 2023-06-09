package com.g4.dev.gosuesprortsapp.util

object RetrofitUrlConstants {
    const val BASE_URL = "https://gosuelansw.ddns.net/api/v1/LanCenterSW/"
    //const val BASE_URL = "https://8697-201-218-143-107.ngrok-free.app/api/v1/LanCenterSW/"
    const val BASE_URL_GAMES_API = "https://videogames-news2.p.rapidapi.com/videogames_news/"


    const val AUTH_URL = "auth/login"

    const val USER_DATILS_URL = "usuarios/buscar"
    const val LIST_CATEGORIES_URL  = "categorias"
    const val  LIST_PRODUCTS_PAGE_URL  = "producto/pag/categoria"

    const val  POST_NEW_SALE_URL = "ventas"
    const val  GET_SALES_HISTORY_URL = "ventas/listar"


    const val  RECENT_NOTICE_URL = "recent"
    const val  GET_COMPUTERS_URL = "ordenadores"

    const val  POST_NEW_BOOKING = "reservas"
    const val  GET_BOOKING_BY_USER_ID_URL = "reservas/buscar"
    const val  CANCEL_BOOKING_URL="reservas/"

    //Payment
    const val  CREDIT_CARD_TOKEN_URL = "payment/credit-card"
    const val  YAPE_TOKEN_URL = "payment/yape"
    const val  CHARGE_TOKEN_URL = "payment/charge"
}