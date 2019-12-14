package cn.xyh.tree.domain;

public class Question {
    private int question_id ;
    private String question_type;   //题目类型
    private String question_descri;  //题目内容
    private String question_right; //答案
    private int question_power;

    public  Question(){
        super();
    }
    public Question( String question_type, String question_descri, String question_right, int question_power) {

        this.question_type = question_type;
        this.question_descri = question_descri;
        this.question_right = question_right;
        this.question_power = question_power;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(String question_type) {
        this.question_type = question_type;
    }

    public String getQuestion_descri() {
        return question_descri;
    }

    public void setQuestion_descri(String question_descri) {
        this.question_descri = question_descri;
    }

    public String getQuestion_right() {
        return question_right;
    }

    public void setQuestion_right(String question_right) {
        this.question_right = question_right;
    }

    public int getQuestion_power() {
        return question_power;
    }

    public void setQuestion_power(int question_power) {
        this.question_power = question_power;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question_id=" + question_id +
                ", question_type='" + question_type + '\'' +
                ", question_descri='" + question_descri + '\'' +
                ", question_right='" + question_right + '\'' +
                ", question_power=" + question_power +
                '}';
    }
}
