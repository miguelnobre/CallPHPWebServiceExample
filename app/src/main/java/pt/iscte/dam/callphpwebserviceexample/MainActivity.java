package pt.iscte.dam.callphpwebserviceexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import java.util.HashMap;
import java.util.Map;

import pt.iscte.dam.callphpwebserviceexample.model.User;
import pt.iscte.dam.callphpwebserviceexample.util.JsonParser;

public class MainActivity extends AppCompatActivity {

    private TextView txtUsername = null;
    private TextView txtPassword = null;
    private Button btnLogin = null;
    private Button btnRegister = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsername = (TextView) findViewById(R.id.txtUsername);
        txtPassword = (TextView) findViewById(R.id.txtPassword);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                doLogin();
            }
        });


        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(MainActivity.this, RegisterUser.class);
                startActivity(intent);
            }
        });


        //addUser("nome user", "user1", "userx");
    }

    private void doLogin() {
        String action = "login";
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();

        System.out.println(username);
        System.out.println(password);

        CustomStringRequest loginRequest = new CustomStringRequest(Request.Method.POST, ConfigPHP.LOGIN_URL,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        System.out.println("Resposta de Sucesso: " + response.toString());
                        Toast.makeText(getBaseContext(), response.toString(), Toast.LENGTH_LONG).show();
                        User user = JsonParser.getInstance().stringToObject(response.toString(), User.class);
                        System.out.print(user.getNome());
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
        loginRequest.addParam("action", action);
        loginRequest.addParam("username", username);
        loginRequest.addParam("password", password);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(loginRequest);
    }

    private void addUser(String nome, String username, String password) {

        CustomStringRequest csr = new CustomStringRequest(Request.Method.POST, ConfigPHP.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("Custom Sucesso: " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Custom Erro: " + error);
                    }
                }
        );

        csr.addParam("nome", nome);
        csr.addParam("username", username);
        csr.addParam("password", password);

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(csr);
    }


}
