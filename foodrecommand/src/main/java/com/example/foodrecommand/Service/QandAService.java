package com.example.foodrecommand.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.foodrecommand.Model.QuestionAnswer;


@Service
public class QandAService {

    public HashMap<Integer, List<QuestionAnswer>> getAllQandA() {
        QuestionAnswer value = new QuestionAnswer();
        Map<Integer, List<QuestionAnswer>> mapofanswer = new HashMap<>();
        List<QuestionAnswer> questionAnswers = new ArrayList<>();
        value = new QuestionAnswer("Nam", 0);
        questionAnswers.add(value);
        value = new QuestionAnswer("Nu", 1);
        questionAnswers.add(value);
        mapofanswer.put(0, questionAnswers);

        questionAnswers = new ArrayList<>();
        value = new QuestionAnswer("Thieu nien", 1);
        questionAnswers.add(value);
        value = new QuestionAnswer("Thanh nien", 2);
        questionAnswers.add(value);
        value = new QuestionAnswer("Trung nien", 3);
        questionAnswers.add(value);
        mapofanswer.put(1, questionAnswers);

        questionAnswers = new ArrayList<>();
        value = new QuestionAnswer("Khong hoat dong manh", 0);
        questionAnswers.add(value);
        value = new QuestionAnswer("Co nhung khong nhieu", 1);
          questionAnswers.add(value);
        value = new QuestionAnswer("Tren 2 lan 1 tuan", 3);
          questionAnswers.add(value);
        value = new QuestionAnswer("Tren 4 lan 1 tuan",4);
        questionAnswers.add(value);

        mapofanswer.put(2, questionAnswers);

        questionAnswers = new ArrayList<>();
        value = new QuestionAnswer("Mau sang", 1);
        questionAnswers.add(value);
        value = new QuestionAnswer("Mau toi", 2);
        questionAnswers.add(value);

        mapofanswer.put(3, questionAnswers);

        questionAnswers = new ArrayList<>();
        value = new QuestionAnswer("Man", 1);
        questionAnswers.add(value);
        value = new QuestionAnswer("Ngot ", 2);
        questionAnswers.add(value);
        value = new QuestionAnswer("Chua ", 3);
        questionAnswers.add(value);
        value = new QuestionAnswer("Cay ", 4);
        questionAnswers.add(value);

        mapofanswer.put(4, questionAnswers);

        questionAnswers = new ArrayList<>();
        value = new QuestionAnswer("Buon buc", 1);
        questionAnswers.add(value);
        value = new QuestionAnswer("Tieu cuc ", 2);
        questionAnswers.add(value);
        value = new QuestionAnswer("Tich cuc ", 3);
        questionAnswers.add(value);

        mapofanswer.put(5, questionAnswers);


        questionAnswers = new ArrayList<>();
        value = new QuestionAnswer("Giu dang", 0);
        questionAnswers.add(value);
        value = new QuestionAnswer("Giam can", 1);
        questionAnswers.add(value);
          value = new QuestionAnswer("Tang can", 2);
        questionAnswers.add(value);

        mapofanswer.put(6, questionAnswers);
        return (HashMap<Integer, List<QuestionAnswer>>) mapofanswer;
    }
}
