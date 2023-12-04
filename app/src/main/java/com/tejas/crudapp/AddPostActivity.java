package com.tejas.crudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.tejas.crudapp.databinding.ActivityAddPostBinding;

public class AddPostActivity extends AppCompatActivity {
ActivityAddPostBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAddPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.myToolbar3.setTitleMarginStart(400);

                Intent intent = getIntent();

                if (intent.hasExtra("id")) {
                    binding.myToolbar3.setTitle("EDIT POST");

                    binding.editTextTitle.setText(intent.getStringExtra("title"));
                    binding.editTextDescription.setText(intent.getStringExtra("description"));
                   binding.editAuthor.setText(intent.getStringExtra("author"));
                    binding.saveBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            savePost();
                        }
                    });

                } else {
                    binding.myToolbar3.setTitle("ADD POST");
                    binding.saveBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            savePost();
                        }
                    });
                }
            }


            private void savePost() {
                String title = binding.editTextTitle.getText().toString();
                String description = binding.editTextDescription.getText().toString();
                String author = binding.editAuthor.getText().toString();
                int likectn = 6;

                if (title.trim().isEmpty() || description.trim().isEmpty() || author.trim().isEmpty()) {
                    Toast.makeText(this, "Please insert a title, description and author", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent data = new Intent();
                data.putExtra("title", title);
                data.putExtra("description", description);
                data.putExtra("author", author);
                data.putExtra("likectn",likectn);

                int id = getIntent().getIntExtra("id", -1);
                if (id != -1) {
                    data.putExtra("id", id);
                }

                setResult(RESULT_OK, data);
                finish();
            }

        }
