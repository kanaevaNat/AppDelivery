package com.example.appdelivery.ui.map

import android.app.Activity
import com.yandex.mapkit.mapview.MapView
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.yandex.mapkit.MapKitFactory
import com.example.appdelivery.R
import com.example.appdelivery.databinding.FragmentSlideshowBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition

/**
 * В этом примере показывается карта и камера выставляется на указанную точку.
 * Не забудьте запросить необходимые разрешения.
 */
class map : Activity(){
    /**
     * Замените "your_api_key" валидным API-ключом.
     * Ключ можно получить на сайте https://developer.tech.yandex.ru/
     */
    private val MAPKIT_API_KEY = "a8b44f41-823e-46df-b5ee-7ca399b45780"
    private val TARGET_LOCATION = Point(59.945933, 30.320045)
    private var mapView: MapView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        /**
         * Задайте API-ключ перед инициализацией MapKitFactory.
         * Рекомендуется устанавливать ключ в методе Application.onCreate(),
         * но в данном примере он устанавливается в Activity.
         */
        MapKitFactory.setApiKey(MAPKIT_API_KEY)
        /**
         * Инициализация библиотеки для загрузки необходимых нативных библиотек.
         * Рекомендуется инициализировать библиотеку MapKit в методе Activity.onCreate()
         * Инициализация в методе Application.onCreate() может привести к лишним вызовам и увеличенному использованию батареи.
         */
        MapKitFactory.initialize(this)
        // Создание MapView.
        setContentView(R.layout.fragment_slideshow)
        super.onCreate(savedInstanceState)
        mapView = findViewById<View>(R.id.mapview) as MapView

        // Перемещение камеры в центр Санкт-Петербурга.
        mapView!!.map.move(
            CameraPosition(TARGET_LOCATION, 14.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 5F),
            null
        )
    }

    override fun onStop() {
        // Вызов onStop нужно передавать инстансам MapView и MapKit.
        mapView!!.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onStart() {
        // Вызов onStart нужно передавать инстансам MapView и MapKit.
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView!!.onStart()
    }

}