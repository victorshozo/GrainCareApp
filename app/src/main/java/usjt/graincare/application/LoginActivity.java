package usjt.graincare.application;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import usjt.graincare.R;
import usjt.graincare.json.GrainCareApi;
import usjt.graincare.rest.GrainCareRestGenerator;

import static android.text.TextUtils.isEmpty;

public class LoginActivity extends AppCompatActivity{

    @BindView(R.id.et_login_email)
    EditText etEmail;
    @BindView(R.id.et_login_password)
    EditText etPassword;

    private GrainCareApi api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);

        api = GrainCareRestGenerator.create(GrainCareApi.class);
    }

    @OnClick(R.id.fab_login)
    public void login(final View view) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (isEmpty(email) || isEmpty(password)) {
            GrainCareSnackBar.show(view, "Informe todos os campos", Snackbar.LENGTH_SHORT);
            return;
        }

        final ProgressDialog loginDialog = ProgressDialog.show(this, "", getString(R.string.loading), true);

        api.login(email, password).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    loginDialog.dismiss();
                    finish();
                    return;
                }

                loginDialog.cancel();
                GrainCareSnackBar.show(view, "Credenciais inválidas", Snackbar.LENGTH_SHORT);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                loginDialog.cancel();
                GrainCareSnackBar.show(view, "Ocorreu um erro ao tentar logar", Snackbar.LENGTH_SHORT);
            }
        });
    }
}
