package ru.skillbranch.devintensive.ui.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.skillbranch.devintensive.R

class ProfileActivity : AppCompatActivity() {

	/**
	 * Вызывается при первом создании или перезапуске Activity.
	 *
	 * Здесь задаётся внешний вид активности (UI) через метод setContentView().
	 * Инициализируются представления.
	 * Представления связываются с необходимыми данными и ресурсами.
	 * Связываются данные со списками.
	 *
	 * Этот метод также предоставляет Bundle, содержащий ранее сохранённое
	 * состояние Activity, если оно было.
	 *
	 * Всегда сопровождается вызовом onStart().
	 */
    override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_profile)
     }

	/**
	 * Этот метод сохраняет состояние представления в Bundle.
	 * Для API Level < 28 (Android P) этот метод будет выполняться до onStop(),
	 * и нет никаких гарантий относительно того,
	 * произойдёт ли это до или после onPause().
	 * Для API Level >= 28 будет вызван после onStop().
	 * Не будет выван если Activity будет явно закрыто пользователем при нажатии на системную клавишу back.
	 */
	override fun onSaveInstanceState(outState: Bundle) {
		super.onSaveInstanceState(outState)
	}
}
