package usjt.graincare.application;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import usjt.graincare.R;
import usjt.graincare.json.GrainCareApi;
import usjt.graincare.models.EmailPasswordDTO;
import usjt.graincare.rest.GrainCareRestGenerator;
import usjt.graincare.util.GrainDialog;
import usjt.graincare.util.Preferences;

import static android.text.TextUtils.isEmpty;

public class ForgotPasswordActivity extends AppCompatActivity {

    @BindView(R.id.et_forgot_password_email)
    EditText etEmail;

    private GrainCareApi api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
        Preferences.setActivity(this);

        api = GrainCareRestGenerator.create(GrainCareApi.class);
    }

    @OnClick(R.id.btn_send_email)
    public void send(final View view) {
        String email = etEmail.getText().toString();

        if (isEmpty(email)) {
            GrainCareSnackBar.show(view, "Informe o e-mail para continuar.", Snackbar.LENGTH_SHORT);
            return;
        }
        EmailPasswordDTO emailDTO = new EmailPasswordDTO();
        emailDTO.setEmail(email);
        api.forgotPassword(emailDTO).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    GrainCareSnackBar.show(view, "Em breve você receberá sua senha pelo e-mail informado.", Snackbar.LENGTH_SHORT);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                GrainCareSnackBar.show(view, "Ocorreu um erro de comunicação com o servidor.", Snackbar.LENGTH_SHORT);
            }
        });
    }
}
