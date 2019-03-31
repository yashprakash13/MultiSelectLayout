package tech.pcreate.multiselectlayout.activity;

import androidx.appcompat.app.AppCompatActivity;
import tech.pcreate.multiselectlayout.R;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        List<String> selectedGenres = getIntent().getStringArrayListExtra("selected");

        TextView textView  =  findViewById(R.id.textView);
        textView.setText(String.format("%s%s", getString(R.string.selected_message), selectedGenres));
    }
}
