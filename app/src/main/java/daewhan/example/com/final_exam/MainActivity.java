package daewhan.example.com.final_exam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /*layout 객체들*/
    Button bt_insert;
    Button bt_result;
    Button bt_right;
    Button bt_left;
    EditText et_input;
    TextView tv_questions;

    /*intent 객체*/
    Intent intent1;

    /*array 객체*/
    static ArrayList<Question> questions;

    static int now_here = 0; //문제 count용

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*layout 객체들*/
        bt_insert = (Button)findViewById(R.id.bt_insert);
        bt_result = (Button)findViewById(R.id.bt_result);
        bt_left = (Button)findViewById(R.id.bt_left);
        bt_right = (Button)findViewById(R.id.bt_right);
        et_input = (EditText)findViewById(R.id.et_input);
        tv_questions = (TextView)findViewById(R.id.tv_questions);

        /*array 객체*/
        questions = new ArrayList<Question>();
        questions.add(new Question("1.보기 중 가장 큰 수를 고르시오\n 1)0  2)4  3)50", 3));
        questions.add(new Question("2.보기 중 가장 작은 수를 고르시오\n 1)0  2)4  3)50", 1));
        questions.add(new Question("3.보기 중 음수를 고르시오\n 1)-5  2)4  3)50", 1));
        questions.add(new Question("4.보기 중 알파벳을 고르시오\n 1)0  2)A  3)50", 2));

        //시작 문제 초기화
        tv_questions.setText(questions.get(now_here).getQuestion());


        //정답입력 버튼
        bt_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_input.getText().toString().equals(""))
                {//아무것도 입력 안했을 경우
                    Toast.makeText(getApplicationContext(), "정답을 입력 하십시오", Toast.LENGTH_LONG).show();
                }
                else
                {
                    questions.get(now_here).setUserAnswer(Integer.parseInt(et_input.getText().toString()));//question객체에 유저가 입력한 값 저장
                    Toast.makeText(getApplicationContext(), (now_here + 1) + "번문제 " + et_input.getText() + "보기 선택", Toast.LENGTH_LONG).show();
                }
            }
        });

        //결과보기 버튼
        bt_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent1 = new Intent(MainActivity.this, ResultActivity.class);
                intent1.putExtra("questions", questions); // 객체배열을 resultActivity에 전달한다.
                startActivity(intent1);

                int crt_num = 0;
                for (int i = 0; i < questions.size(); i++)
                {//몇개의 답이 맞았는지 검사
                    if (questions.get(i).isCorrect())
                        crt_num++;
                }
                Toast.makeText(getApplicationContext(), "현재 맞힌 갯수는 " + crt_num + " 입니다", Toast.LENGTH_LONG).show();
            }
        });

        //이전문제 버튼
        bt_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (now_here == 0)
                {//첫번째 문제에 있는 경우
                    Toast.makeText(getApplicationContext(), "현재 첫번째 문제입니다", Toast.LENGTH_LONG).show();
                }
                else
                {//now_here을 감소시키고, 그 문제를 보여준다
                    tv_questions.setText(questions.get(--now_here).getQuestion());
                }
            }
        });

        //다음문제 버튼
        bt_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(now_here == questions.size() -1)
                { //마지막 문제에 있는 경우
                    Toast.makeText(getApplicationContext(), "현재 마지막 문제입니다", Toast.LENGTH_LONG).show();
                }
                else
                {//now_here을 증가시키고, 그 문제를 보여준다.
                    tv_questions.setText(questions.get(++now_here).getQuestion());
                }
            }
        });

    }
}
