package pt.atp.a38128_42575

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.MediaStore
import android.widget.*
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide

private const val IMAGE = 500
private const val FILM = 500
@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {


    private lateinit var timer: CountDownTimer
    private var untilFinished = 10000L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button2).setOnClickListener {
            openCameraFilm()
        }


        findViewById<Button>(R.id.button3).setOnClickListener {
            openCameraPic()
        }
        findViewById<Button>(R.id.button4).setOnClickListener {
            Gallery()
        }
        findViewById<Button>(R.id.button5).setOnClickListener {
            openListActivity()
        }



    }

    override fun onResume() {
        super.onResume()

        startCountDownTimer(untilFinished)
    }


    override fun onPause() {
        super.onPause()

        timer.cancel()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        if(requestCode == IMAGE && resultCode == RESULT_OK){
            val imageBitmap = data?.extras?.get("data") as Bitmap
            findViewById<ImageView>(R.id.imageView3).setImageBitmap(imageBitmap)
        }


        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun openCameraFilm() {
        val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        startActivity(intent)
    }

    private fun openCameraPic() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, IMAGE)
    }
    private fun Gallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("video/*")
        startActivityForResult(intent, FILM)
    }
    private fun openListActivity(){
        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
    }



    private fun startCountDownTimer (time: Long){
        timer = object: CountDownTimer(time, 10000){
            override fun onTick(millisUntilFinished: Long) {
                untilFinished = millisUntilFinished
                findViewById<TextView>(R.id.countdown).text = "segundos restantes: ${millisUntilFinished/1000}"
            }

            override fun onFinish() {
                findViewById<TextView>(R.id.countdown).text = "Adeus!"
            }

        }

        timer.start()
    }
}