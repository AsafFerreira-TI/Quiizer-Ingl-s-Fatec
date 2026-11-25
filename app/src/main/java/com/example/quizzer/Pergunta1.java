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

public class Pergunta1 extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioCorreta;
    private Button btnAnterior, btnProximo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pergunta1);

        // 2. Vinculando os componentes do Java com os IDs do XML
        radioGroup = findViewById(R.id.radioGroupPergunta1);
        
        // Na Questão 1 (Zayu), a alternativa correta é a (B): "força, agilidade e criatividade"
        radioCorreta = findViewById(R.id.radioButton1B); 
        
        btnAnterior = findViewById(R.id.btnAnterior1);
        btnProximo = findViewById(R.id.btnProximo1);

        // 3. Ação do botão "Voltar" (fecha a tela atual e volta para a MainActivity)
        btnAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 4. Ação do botão "Próxima"
        btnProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verifica qual botão foi marcado dentro do RadioGroup (-1 significa que nenhum foi marcado)
                int idSelecionado = radioGroup.getCheckedRadioButtonId();

                if (idSelecionado == -1) {
                    // Mostra um aviso rápido na tela se o usuário tentar avançar sem responder
                    Toast.makeText(Pergunta1.this, "Por favor, selecione uma alternativa!", Toast.LENGTH_SHORT).show();
                } else {
                    int acertos = 0;

                    // Se o ID marcado for igual ao ID da alternativa correta, soma 1 ponto
                    if (idSelecionado == radioCorreta.getId()) {
                        acertos = 1;
                    }

                    // Prepara a transição para a Pergunta 2 levando a pontuação acumulada na "mochila" (putExtra)
                    Intent intent = new Intent(Pergunta1.this, Pergunta2.class);
                    intent.putExtra("PONTUACAO", acertos);
                    startActivity(intent);
                }
            }
        });

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pergunta1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}

