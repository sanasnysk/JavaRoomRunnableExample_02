package com.sansan.javaroomrunnableexample_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateWordActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "com.sansan.javaroomrunnableexample_02.EXTRA_ID";
    public static final String EXTRA_WORD = "com.sansan.javaroomrunnableexample_02.EXTRA_WORD";
    private EditText edt_id;
    private EditText edt_word;
    private Button btn_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_word);

        edt_id = findViewById(R.id.up_id);
        edt_word = findViewById(R.id.edt_word);
        btn_edit = findViewById(R.id.btn_edit);

        Intent positionIntent = getIntent();
        edt_id.setText(positionIntent.getStringExtra(EXTRA_ID));
        edt_word.setText(positionIntent.getStringExtra(EXTRA_WORD));

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(edt_word.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String word = edt_word.getText().toString();
                    replyIntent.putExtra(EXTRA_WORD, word);
                    setResult(RESULT_OK, replyIntent);

                }
                finish();
            }
        });

    }
}