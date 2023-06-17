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
        value = new QuestionAnswer("Hoat dong binh thuong", 1);
        questionAnswers.add(value);
        value = new QuestionAnswer("Hoat dong nhieu", 2);
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
        value = new QuestionAnswer("Cay ", 2);
        questionAnswers.add(value);
        value = new QuestionAnswer("Chua ", 3);
        questionAnswers.add(value);
        value = new QuestionAnswer("Ngot ", 4);
        questionAnswers.add(value);

        mapofanswer.put(4, questionAnswers);

        questionAnswers = new ArrayList<>();
        value = new QuestionAnswer("Buon buc", 1);
        questionAnswers.add(value);
        value = new QuestionAnswer("Tieu cuc ", 2);
        questionAnswers.add(value);
        value = new QuestionAnswer("Vui ve ", 3);
        questionAnswers.add(value);

        mapofanswer.put(5, questionAnswers);

        return (HashMap<Integer, List<QuestionAnswer>>) mapofanswer;
    }
}
