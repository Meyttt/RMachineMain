package Other;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Admin on 28.10.2016.
 */

/**
 * Класс ленты, реализован на основе очереди и итератора для сохранения возможности вывода в любой момент всей ленты.
 * Чтение из ленты осуществляется методом read;
 */
public class Tape {
    Queue<Character> tape=new LinkedList<>();
    public Tape(String tapeValue){
        char[] charValue = tapeValue.toCharArray();
        LinkedList<Character> charsList = new LinkedList<>();
        for(Character ch:charValue){
            charsList.add(ch);
        }
        this.tape=charsList;
    }
    public Tape(Queue<Character> tape) {
        this.tape = tape;
    }


    Iterator<Character> it = tape.iterator();
    public Character read(){
        return it.next(); // ОБНОВЛЕНИЕ: Я же правильно понимаю, что указатель тут тоже перемещается?
    }
    public int size() {
        return tape.size();
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
