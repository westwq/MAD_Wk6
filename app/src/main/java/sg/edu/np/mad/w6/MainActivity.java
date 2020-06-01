package sg.edu.np.mad.w6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Method description
     *
     * @param v view to display...
     * @return 0 if ..., 1...
     */
    public int onReturn(View v)
    {
        return 0;
    }
    public void onClick(View v)
    {
        EditText input = findViewById(R.id.editText);
        String txt = input.getText().toString();

        SharedPreferences reader = getSharedPreferences("UserAccount", MODE_PRIVATE);
        String fromSP = reader.getString("account", "Not found");

        TextView txtView = findViewById(R.id.textView);
        txtView.setText(fromSP);

        SharedPreferences.Editor editor = getSharedPreferences("UserAccount", MODE_PRIVATE).edit();
        editor.putString("account", txt);
        editor.apply();
        //serialisation

        //insert into database
        DBAdapter dba = new DBAdapter(this);
        dba.insertText(txt);

        ArrayList<String> data = dba.readText();

        for(String s : data) {
            txtView.setText(txtView.getText().toString() + "\n" + s);
        }

    }
}
