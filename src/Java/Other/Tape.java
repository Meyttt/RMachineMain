package Other;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by Admin on 28.10.2016.
 */
//TODO: Переписать Tape под постоянное обращене и проверку, какой символ сейчас на ленте
/**
 * Класс ленты, реализован на основе очереди и итератора для сохранения возможности вывода в любой момент всей ленты.
 * Чтение из ленты осуществляется методом read;
 */
//TODO: запись с ленты в память
public class Tape implements Serializable {
    char[] tape;
    int counter;
//    Queue<Character> tape=new LinkedList<>();
    public Tape(String tapeValue){
        char[] charValue = tapeValue.toCharArray();
        LinkedList<Character> charsList = new LinkedList<>();
        for(Character ch:charValue){
            charsList.add(ch);
        }
        this.tape=tapeValue.toCharArray();
        this.counter=0;
    }
//    Iterator<Character> it;
    public Tape(char[] tape) {
        this.tape = tape;
        this.counter=0;
    }



//    public Character read(){
//        return tape[this.counter++];
//    }
    public Character read(int addition){
        return tape[counter+addition];
    }
    public void move(int length){
        this.counter+=length;
    }
    public Character readCurrent(){
        return tape[this.counter];
    }
//    public Character readN(){
//        return it.toString();
//    }
    public int size() {
        return tape.length-counter;
    }
    public String toString(){
        String answer="";
        try {
            for (Character ch : tape) {
                answer += ch;
            }
            return answer;
        }catch (NullPointerException e){
            return null;
        }
    }
//    public static void main(String[] args) {
//        Other.Tape tape = new Other.Tape();
//        System.out.println(tape.read());
//        System.out.println(tape.read());
//    }
}
