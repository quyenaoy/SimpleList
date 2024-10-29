package project1.com.informationform

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var txtInput: EditText? = null
    private var radioGroup: RadioGroup? = null
    private var btnShow: Button? = null
    private var listView: ListView? = null
    private var txtError: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        txtInput = findViewById<EditText>(R.id.txtInput)
        radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        btnShow = findViewById<Button>(R.id.btnShow)
        listView = findViewById<ListView>(R.id.listView)
        txtError = findViewById<TextView>(R.id.txtError)

        btnShow?.setOnClickListener({ showNumbers() })
    }

    private fun showNumbers() {
        val input = txtInput!!.text.toString()
        txtError!!.text = "" // Reset error message

        // Kiểm tra dữ liệu đầu vào
        if (input.isEmpty()) {
            txtError!!.text = "Vui lòng nhập một số nguyên dương."
            return
        }

        val n: Int
        try {
            n = input.toInt()
            if (n < 0) {
                txtError!!.text = "Vui lòng nhập số nguyên dương."
                return
            }
        } catch (e: NumberFormatException) {
            txtError!!.text = "Vui lòng nhập một số hợp lệ."
            return
        }

        val numbers = ArrayList<String>()
        val checkedRadioButtonId = radioGroup!!.checkedRadioButtonId

        // Kiểm tra xem có radio button nào được chọn không
        if (checkedRadioButtonId == -1) {
            txtError!!.text = "Vui lòng chọn một loại số."
            return
        }

        // Xử lý theo loại số được chọn
        if (checkedRadioButtonId == R.id.radioSoChan) {
            var i = 0
            while (i <= n) {
                numbers.add(i.toString())
                i += 2
            }
        } else if (checkedRadioButtonId == R.id.radioSoLe) {
            var i = 1
            while (i <= n) {
                numbers.add(i.toString())
                i += 2
            }
        } else if (checkedRadioButtonId == R.id.radioSoChinhPhuong) {
            var i = 0
            while (i * i <= n) {
                numbers.add((i * i).toString())
                i++
            }
        }

        // Hiển thị kết quả trong ListView
        val adapter = ArrayAdapter(this, R.layout.list_item, numbers)
        listView!!.adapter = adapter
    }
}