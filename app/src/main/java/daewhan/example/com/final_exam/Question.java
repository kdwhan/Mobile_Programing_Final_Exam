package daewhan.example.com.final_exam;

import java.io.Serializable;

/**
 * Created by daewhan on 2015-12-17.
 */
public class Question implements Serializable {//activity간 객체 전달을 위해 Serializable을 implements했다.
    //질문을 담을 변수
    String question;
    //대한 답을 담을 변수
    int correctAnswer;
    //사용자가 입력한 정답을 담을 변수. 처음엔 -1로 초기화
    int userAnswer;

    //질문, 정답으로 초기화 하는 생성자
    public Question(String q, int answer) {
        question = q;
        correctAnswer = answer;
        userAnswer = -1; // -1은 사용자 답 초기화 하는 용도
    }

    //정답과 사용자의 답을 비교
    public boolean isCorrect() {
        return (correctAnswer == userAnswer);
    }

    //질문을 리턴. 화면에 문제를 보여줄 때 사용한다.
    public String getQuestion(){
        return question;
    }

    //사용자의 답을 셋팅한다.
    public void setUserAnswer(int answer) {
        userAnswer = answer;
    }

    //사용자의 답을 -1로 초기화한다.
    public void resetAnswer() {
        userAnswer = -1;
    }

}
