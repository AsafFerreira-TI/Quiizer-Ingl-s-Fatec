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

public class Pergunta2 extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioCorreta;
    private Button btnAnterior, btnProximo;

    private int acertosAnteriores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pergunta2);

        // 1. Pega a pontuação acumulada que veio da tela anterior (se não vier nada, assume 0)
        acertosAnteriores = getIntent().getIntExtra("PONTUACAO", 0);

        // 2. Vincula os componentes com os IDs do activity_pergunta2.xml
        radioGroup = findViewById(R.id.radioGroupPergunta2);
        
        // Na Questão 2 (Rocket Man - "in fact"), a alternativa correta é a (E)
        radioCorreta = findViewById(R.id.radioButtonE); 
        
        btnAnterior = findViewById(R.id.btnAnterior2);
        btnProximo = findViewById(R.id.btnProximo2);

        // 3. Botão "Anterior" (fecha a Pergunta 2 e volta para a Pergunta 1)
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
                    Toast.makeText(Pergunta2.this, "Por favor, selecione uma alternativa!", Toast.LENGTH_SHORT).show();
                } else {
                    // Começa com a pontuação que já estava acumulada
                    int acertosTotais = acertosAnteriores;

                    // Se acertou a Questão 2, soma mais 1 ponto
                    if (idSelecionado == radioCorreta.getId()) {
                        acertosTotais = acertosTotais + 1;
                    }

                    // Manda o total atualizado para a Pergunta 3
                    Intent intent = new Intent(Pergunta2.this, Pergunta3.class);
                    intent.putExtra("PONTUACAO", acertosTotais);
                    startActivity(intent);
                }
            }
        });

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pergunta2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}

