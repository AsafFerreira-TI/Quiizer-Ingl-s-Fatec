package com.example.quizzer;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Results extends AppCompatActivity {

    private TextView txtPontuacao, txtFeedback;
    private Button btnRefazer, btnInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_resultado);

        // 1. Vincula os componentes com os IDs do activity_resultado.xml
        txtPontuacao = findViewById(R.id.textViewPontuacao);
        txtFeedback = findViewById(R.id.textViewFeedback);
        btnRefazer = findViewById(R.id.btnRefazerQuiz);
        btnInicio = findViewById(R.id.btnVoltarInicio);

        // 2. Recebe a pontuação final enviada pela Pergunta 5
        int acertos = getIntent().getIntExtra("PONTUACAO", 0);

        // 3. Atualiza o texto grande com a nota
        txtPontuacao.setText(acertos + " / 5 acertos");

        // 4. Define uma mensagem motivacional personalizada conforme o desempenho
        if (acertos == 5) {
            txtFeedback.setText("Perfeito! Você gabaritou! Mandará bem na prova da Fatec.");
        } else if (acertos >= 3) {
            txtFeedback.setText("Muito bom! Sem dúvidas, um ótimo resultado.");
        } else {
            txtFeedback.setText("Bom treino! Vale a pena revisar a interpretação de texto em inglês e tentar novamente.");
        }

        // 5. Botão "Refazer Quiz" (volta direto para a Pergunta 1 e limpa as telas antigas da memória)
        btnRefazer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Results.this, Pergunta1.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        // 6. Botão "Tela Inicial" (volta para a MainActivity e limpa o histórico de navegação)
        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Results.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_results);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}