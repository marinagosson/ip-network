package marinagosson.network.view;

import android.app.AlertDialog;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.LongClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import marinagosson.network.R;
import marinagosson.network.util.Utils;
import marinagosson.network.bean.Subnet;

@EActivity(R.layout.activity_main)
public class MainActivity extends ActionBarActivity {

    @ViewById(R.id.editTextMascara)
    EditText mascara;

    @ViewById(R.id.editTextIpv4)
    EditText ipv4;

    @ViewById(R.id.editTextIpDecimal)
    EditText ipDecimal;

    @ViewById(R.id.editTextIpBinario)
    EditText ipBinario;

    @ViewById
    android.support.v7.widget.Toolbar toolbar;

    @ViewById(R.id.adView)
    AdView adView;

    @AfterViews
    public void prepararView() {

        AdRequest adRequest = new AdRequest.Builder()
                .build();
        adView.loadAd(adRequest);
    }

    /**
     * Called when leaving the activity
     */
    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    /**
     * Called when returning to the activity
     */
    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    /**
     * Called before the activity is destroyed
     */
    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

    @Click(R.id.start_one)
    public void startOne() {

        if (this.ipv4.getText().length() == 0 || this.mascara.getText().length() == 0) {
            showDialog();
        } else if (verificaSeIpOuMascaraEstaValido()) {
            Subnet subnet = Utils.obterInformacoesRede(ipv4.getText().toString(), mascara.getText().toString());
            showDialog("Informações da Rede", subnet.toString() + "\n");
        } else {
            showDialog("Erro", "Máscara ou IP inválido.");
        }
    }

    public boolean verificaSeIpOuMascaraEstaValido() {

        String ipv4 = this.ipv4.getText().toString();
        String mascara = this.mascara.getText().toString();

        String regex = "\\d?\\d?\\d[.]\\d?\\d?\\d[.]\\d?\\d?\\d[.]\\d?\\d?\\d";

        Pattern patterIpv4 = Pattern.compile(regex);
        Matcher matcherIpv4 = patterIpv4.matcher(ipv4);
        Matcher matcherMask = patterIpv4.matcher(mascara);

        if (matcherIpv4.matches() && matcherMask.matches()) {
            return true;
        }

        return false;
    }

    @UiThread
    public void showDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);

        builder.setMessage("Preencha os cammpos IPV4 e Máscara")
                .setTitle("Atenção");

        AlertDialog dialog = builder.create();

        dialog.show();
    }

    @UiThread
    public void showDialog(String title, String mensagem) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT);

        builder.setMessage(mensagem)
                .setTitle(title);

        AlertDialog dialog = builder.create();

        dialog.show();
    }

    @Click
    public void start_decimal_binario() {
        ipBinario.setText("");
        if (ipDecimal.getText().length() != 0)
            ipBinario.setText(Utils.decimalParaBinario(ipDecimal.getText().toString()));
    }

    @Click
    public void start_binario_decimal() {
        ipDecimal.setText("");
        if (ipBinario.getText().length() != 0)
            ipDecimal.setText(Utils.binarioParaDecimal(ipBinario.getText().toString()));
    }

    @LongClick(R.id.start_decimal_binario)
    public void longClickDB() {
        Toast.makeText(getApplicationContext(), R.string.converter_decimal_em_binario, Toast.LENGTH_LONG).show();
    }

    @LongClick(R.id.start_binario_decimal)
    public void longClickBD() {
        Toast.makeText(getApplicationContext(), R.string.converter_binario_decimal, Toast.LENGTH_LONG).show();
    }

}