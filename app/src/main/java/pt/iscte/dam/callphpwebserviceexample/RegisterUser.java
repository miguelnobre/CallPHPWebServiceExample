package pt.iscte.dam.callphpwebserviceexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

public class RegisterUser extends AppCompatActivity {

    private TextView txtNome = null;
    private TextView txtIdade = null;
    private TextView txtUsername = null;
    private TextView txtPassword = null;
    private TextView txtEmail = null;

    private Button btnRegistarUser = null;
    private Button btnVoltar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        txtNome = (TextView) findViewById(R.id.txtNewNome);
        txtIdade = (TextView) findViewById(R.id.txtIdade);

        txtUsername = (TextView) findViewById(R.id.txtNewUsername);
        txtPassword = (TextView) findViewById(R.id.txtNewPassword);

        txtEmail = (TextView) findViewById(R.id.txtEmail);

        btnRegistarUser = (Button) findViewById(R.id.btnRegistUser);
        btnRegistarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registarUser();
            }
        });

        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void registarUser() {

        String nome = txtNome.getText().toString();
        String idade = txtIdade.getText().toString();
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();
        String email = txtEmail.getText().toString();

        CustomStringRequest newUserRequest = new CustomStringRequest(Request.Method.POST, ConfigPHP.REGISTER_URL,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        System.out.println("Resposta de Sucesso: " + response.toString());
                        Toast.makeText(getBaseContext(), response.toString(), Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Erro: " + error.getMessage());
                        Toast.makeText(getBaseContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        );

        //Parametros POST necessarios para o Login
        newUserRequest.addParam("name", nome);
        newUserRequest.addParam("age", idade);
        newUserRequest.addParam("username", username);
        newUserRequest.addParam("password", password);
        newUserRequest.addParam("email", email);


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(newUserRequest);
    }
}
