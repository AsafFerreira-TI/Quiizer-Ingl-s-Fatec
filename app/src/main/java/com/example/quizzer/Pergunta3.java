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

public class Pergunta3 extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioCorreta;
    private Button btnAnterior, btnProximo;

    private int acertosAnteriores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pergunta3);

        // 1. Pega a pontuação acumulada que veio da tela anterior (se não vier nada, assume 0)
        acertosAnteriores = getIntent().getIntExtra("PONTUACAO", 0);

        // 2. Vincula os componentes com os IDs do activity_pergunta3.xml
        radioGroup = findViewById(R.id.radioGroupPergunta3);
        
        // Na Questão 3, a alternativa correta é a (D)
        radioCorreta = findViewById(R.id.radioButton3D); 
        
        btnAnterior = findViewById(R.id.btnAnterior3);
        btnProximo = findViewById(R.id.btnProximo3);

        // 3. Botão "Anterior" (fecha a Pergunta 3 e volta para a Pergunta 2)
        btnAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 4. Botão "Próxima"
        btnProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idSelecionado = radioGroup.getCheckedRadioButtonId();

                if (idSelecionado == -1) {
                    Toast.makeText(Pergunta3.this, "Por favor, selecione uma alternativa!", Toast.LENGTH_SHORT).show();
                } else {
                    // Começa com a pontuação que já estava acumulada
                    int acertosTotais = acertosAnteriores;

                    // Se acertou a Questão 3, soma mais 1 ponto
                    if (idSelecionado == radioCorreta.getId()) {
                        acertosTotais = acertosTotais + 1;
                    }

                    // Manda o total atualizado para a Pergunta 4
                    Intent intent = new Intent(Pergunta3.this, Pergunta4.class);
                    intent.putExtra("PONTUACAO", acertosTotais);
                    startActivity(intent);
                }
            }
        });

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pergunta3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}