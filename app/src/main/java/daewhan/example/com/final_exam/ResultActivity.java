package daewhan.example.com.final_exam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.Serializable;
import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    Button bt_back;
    TextView tv_result;

    /*array 객체, mainActivity에서 받아와 저장할 곳*/
    static ArrayList<Question> questions;

    //출력용 배열
    String[] printout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = this.getIntent();

        /*array 객체, mainActivity에서 받아와 저장*/
        ArrayList<Question> questions = (ArrayList<Question>)intent.getSerializableExtra("questions");

        tv_result = (TextView)findViewById(R.id.tv_result);

        printout = new String[questions.size()];

        for(int i =0; i < questions.size(); i++){
            if(questions.get(i).isCorrect()){ //맞힌 문제일 경우 저장할 문자열
                printout[i] = (i+1) + "번 문제 맞음\n";
            }
            else{//틀린 문제일 경우 저장할 문자열
                printout[i] = (i+1) +"번 문제 틀림\n";
            }
        }

        tv_result.setText("");//null로 초기화

        for(int i = 0; i < questions.size(); i++)
        {//문제별 결과를 확장해서 붙인후 보여준다.
            tv_result.append(printout[i]);
        }

        //돌아가기 버튼
        bt_back = (Button)findViewById(R.id.bt_back);
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
