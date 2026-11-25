package com.example.quizzer;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Pergunta5 extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioCorreta;
    private Button btnAnterior, btnProximo;

    private int acertosAnteriores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pergunta5);

        // 1. Pega a pontuação acumulada que veio da Pergunta 4
        acertosAnteriores = getIntent().getIntExtra("PONTUACAO", 0);

        // 2. Vincula os componentes com os IDs do activity_pergunta5.xml
        radioGroup = findViewById(R.id.radioGroupPergunta5);
        
        // Na Questão 5 (Observatórios Astronômicos), a alternativa correta é a (A)
        radioCorreta = findViewById(R.id.radioButton5A); 
        
        btnAnterior = findViewById(R.id.btnAnterior5);
        btnFinalizar = findViewById(R.id.btnFinalizar);

        // 3. Botão "Anterior" (volta para a Pergunta 4)
        btnAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 4. Botão "Finalizar"
        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idSelecionado = radioGroup.getCheckedRadioButtonId();

                if (idSelecionado == -1) {
                    Toast.makeText(Pergunta5.this, "Por favor, selecione uma alternativa!", Toast.LENGTH_SHORT).show();
                } else {
                    int acertosFinais = acertosAnteriores;

                    // Se acertou a Questão 5, soma o último ponto
                    if (idSelecionado == radioCorreta.getId()) {
                        acertosFinais = acertosFinais + 1;
                    }

                    // Manda o resultado final para a tela de Resultados
                    Intent intent = new Intent(Pergunta5.this, Results.class);
                    intent.putExtra("PONTUACAO", acertosFinais);
                    startActivity(intent);
                }
            }
        });

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pergunta5);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}