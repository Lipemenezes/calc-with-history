package com.example.aluno.calculadorahistorico;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText editNumber2;
    private EditText editNumber1;
    private LinearLayout layoutResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initViews();
    }

    public void calculate(View view) {

        Double number1 = Double.parseDouble(editNumber1.getText().toString());
        Double number2 = Double.parseDouble(editNumber2.getText().toString());
        String operationSign = ((Button)view).getText().toString();
        Double result = null;

        switch (view.getId()) {
            case R.id.sum:
                result = sum(number1, number2);
                break;
            case R.id.subtraction:
                result = subtract(number1, number2);
                break;
            case R.id.multiplication:
                result = multiply(number1, number2);
                break;
            case R.id.division:
                result = divide(number1, number2);
                break;
        }

        setOperationToHistory(
                getOperationDescription(number1, number2, operationSign, result)
        );

        clearOldHistory();
    }

    private void clearOldHistory() {

        if (layoutResults.getChildCount() > 5) {
            layoutResults.removeViewAt(5);
        }
    }

    private void setOperationToHistory(String operation) {
        TextView view = createTextViewForOperation(operation);
        layoutResults.addView(view, 0);
    }

    private TextView createTextViewForOperation(String operation) {
        TextView textOperation = new TextView(this);
        textOperation.setText(operation);
        textOperation.setLayoutParams(getOperationLayoutParams());
        textOperation.setGravity(Gravity.CENTER);

        return textOperation;
    }

    private ViewGroup.LayoutParams getOperationLayoutParams() {
        return new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
    }

    private String getOperationDescription(Double n1, Double n2, String op, Double result) {
        return n1.toString() + " " + op + " " + n2.toString() + " = " + result;
    }

    private Double sum(Double n1, Double n2) {
        return n1 + n2;
    }

    private Double subtract(Double n1, Double n2) {
        return n1 - n2;
    }

    private Double multiply(Double n1, Double n2) {
        return n1 * n2;
    }

    private Double divide(Double n1, Double n2) {
        return n1 / n2;
    }

    private void initViews() {
        this.layoutResults = findViewById(R.id.layout_results);
        this.editNumber1 = findViewById(R.id.edit_number_1);
        this.editNumber2 = findViewById(R.id.edit_number_2);
    }

}
