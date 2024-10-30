package com.example.ac2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FilmeAdapter filmeAdapter;
    private List<Filme> filmeList;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerViewFilmes = findViewById(R.id.recyclerViewFilmes);

        // Configurar LinearLayoutManager horizontal
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewFilmes.setLayoutManager(layoutManager);

        filmeList = new ArrayList<>();
        filmeAdapter = new FilmeAdapter(this, filmeList);
        recyclerViewFilmes.setAdapter(filmeAdapter);

        // Adicionar PagerSnapHelper para snap de página
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerViewFilmes);

        // Inicializar o Firestore
        db = FirebaseFirestore.getInstance();

        // Carregar dados do Firestore
        carregarDadosDoFirestore();
    }


        @SuppressLint("NotifyDataSetChanged")
    private void carregarDadosDoFirestore() {
        CollectionReference filmesRef = db.collection("filmes");
        filmesRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    String titulo = document.getString("titulo");
                    long ano = document.getLong("ano");
                    String sinopse = document.getString("sinopse");
                    String diretor = document.getString("diretor");
                    String imagem = document.getString("imagem");

                    Filme filme = new Filme(titulo, (int) ano, sinopse, diretor, imagem);
                    filmeList.add(filme);
                }
                filmeAdapter.notifyDataSetChanged();
            } else {
                Log.w("MainActivity", "Erro ao carregar documentos.", task.getException());
            }
        });
    }
}
