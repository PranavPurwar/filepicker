package dev.pranav.filepicker.sample

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import dev.pranav.filepicker.FilePickerCallback
import dev.pranav.filepicker.FilePickerDialogFragment
import dev.pranav.filepicker.FilePickerOptions
import dev.pranav.filepicker.SelectionMode
import dev.pranav.filepicker.sample.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dialog = FilePickerDialogFragment(
            FilePickerOptions().apply {
                selectionMode = SelectionMode.BOTH
                extensions = arrayOf("jpg", "png", "pdf", "txt")
                title = "Select Files"
                initialDirectory = "/storage/emulated/0/Download"
                multipleSelection = true
            },
            object : FilePickerCallback() {
                override fun onFileSelected(file: File) {
                    super.onFileSelected(file)
                    binding.textView.text = file.absolutePath
                }

                override fun onFilesSelected(files: List<File>) {
                    super.onFilesSelected(files)
                    binding.textView.text = files.joinToString("\n") { it.absolutePath }
                }

                override fun onFileSelectionCancelled(): Boolean {
                    super.onFileSelectionCancelled()
                    binding.textView.text = "Selection canceled"
                    return super.onFileSelectionCancelled()
                }
            }
        )

        binding.pickFileButton.setOnClickListener {
            dialog.show(supportFragmentManager, "file_picker")
        }
    }
}
