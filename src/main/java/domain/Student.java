package domain;

import json.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    protected List<Tuple<String, Integer>> exams;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.exams = Arrays.asList(exams);
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObject jsonObject = super.toJsonObject();

        List<Json> exams = new ArrayList<>();
        for (Tuple<String, Integer> examInfo: this.exams) {
            JsonPair subject = new JsonPair("course", new JsonString(examInfo.key));
            JsonPair mark = new JsonPair("mark", new JsonNumber(examInfo.value));
            JsonPair passed = new JsonPair("passed", new JsonBoolean(examInfo.value > 2));
            JsonObject exam = new JsonObject(subject, mark, passed);
            exams.add(exam);
        }
        Json[] examsArr = exams.toArray(new Json[0]);
        JsonArray jsonExams = new JsonArray(examsArr);

        jsonObject.add(new JsonPair("exams", jsonExams));

        return jsonObject;
    }
}