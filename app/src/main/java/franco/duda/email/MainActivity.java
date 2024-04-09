package franco.duda.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtemos o botao atraves do findViewById, passando como parametro o id do botao
        Button btnEnviar = (Button) findViewById(R.id.btnEnviar);
        // Definiçao da acao do click do botao
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtendo dados digitados pelo usuario
                EditText etEmail = (EditText) findViewById(R.id.etEmail); // pegamos o campo EdtText de id etEmail atrves do metodo findViewById
                String email = etEmail.getText().toString(); //obtem o valor digitado pelo usuario

                EditText etAssunto = (EditText) findViewById(R.id.etAssunto);
                String assunto = etAssunto.getText().toString();

                EditText etTexto = (EditText) findViewById(R.id.etTexto);
                String texto = etTexto.getText().toString();

                // criamos um Intent e indicamos a acao que queremos realizar
                Intent i = new Intent(Intent.ACTION_SENDTO);

                i.setData(Uri.parse("mailto:")); // definimos q estamos interessados em apps que resolvem URI"malito:" (apps que trabalham com envio de email)

                //preenchemos o Intent com os dados q queremos enviar para a app externa
                String[] emails = new String[]{email};
                i.putExtra(Intent.EXTRA_EMAIL, emails); // lista de String onde cada String e um email
                i.putExtra(Intent.EXTRA_SUBJECT, assunto); // lista de String onde cada String e um assunto
                i.putExtra(Intent.EXTRA_TEXT, texto); // lista de String onde cada String e um texto

                try { // tentamos executar o Intent
                    startActivity(Intent.createChooser(i, "Escolha o APP")); //Intent.createChooser mostra um menu com apps
                }
                catch (ActivityNotFoundException e) { //caso n tenha nenhuma app de envio essa mensagem aparece
                    Toast.makeText(MainActivity.this, "Não há nenhum app que posso realizar essa operação", Toast.LENGTH_LONG).show();
                }



            }
        });
    }
}