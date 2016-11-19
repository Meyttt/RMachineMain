package Logic;

import Memories.Memory;
import Other.Storage;
import Other.Tape;

import java.util.HashMap;
import java.util.Set;

/**
 * Левая и правая часть - строки, их надо парсить
 * Оператор представлен массивом из 4 символов
 */
public class Statement {
    //TODO: реализовать выполнение выражения
    String lefStr, rightStr;
    Memory leftMem,rightMem;
    Operator operator;

    public Statement(String lefStr, Operator operator, Memory rightMem) {
        this.lefStr = lefStr;
        this.rightMem = rightMem;
        this.operator = operator;
        this.rightStr=null;
        this.leftMem=null;
    }
    public Statement(Memory leftMem, Operator operator, Memory rightMem) {
        this.lefStr = null;
        this.rightMem = rightMem;
        this.operator = operator;
        this.rightStr=null;
        this.leftMem=leftMem;
    }
    public Statement(Memory leftMem, Operator operator, String rightStr) {
        this.lefStr = null;
        this.rightMem = null;
        this.operator = operator;
        this.rightStr=rightStr;
        this.leftMem=leftMem;
    }


    /**
     *  В языке представлены 3(4) вида операторов (по длине).
     *  Общая структура оператора такова: левая часть, середина, правая часть.
     *  Левая и правая части зарезервированы под оператор очистки памяти("/") и могут отстутствовать.
     *  Основная часть может состоять из 1 или двух символов и присутствует всегда.
     * @return экземпляр класса Оператор
     */
    public static Operator getOperator(String strOp){
        char[] chars = strOp.toCharArray();
        switch (chars.length){
            case(3):
                if(chars[0]=='/'){
                    return new Operator(chars[0],(""+chars[1]+chars[2]).toCharArray());
                }else
                    return new Operator((""+chars[0]+chars[1]).toCharArray(),chars[2]);
            case (4):
                return new Operator(chars[0],(""+chars[1]+chars[2]).toCharArray(),chars[3]);
            case (2):
                return new Operator(chars);
            default:
                return null;

        }
    }
    /**
     * У нас есть 2 базовых вида выражений: чтение/запись (<-, ->), для каждого из которых можно задать очистку ячейки памяти.
     * Т.о., у нас есть три основных поля для этого варианта (очистка левого операнда, стрелка, очистка правого операнда)
     * Для второго варианта(=^ вставка, =. добавление,&= поиск по совпадению, &~ поиск по несовпадению) нам нужно только одно поле, поэтому 1 и 3 заполняются null-ами.
     * ОБНОВЛЕНИЕ: надо, по идее, еще учитывать такие операторы как ==, !=, <, <=, >, >=, они, как и &= и &~ становятся в одно поле.
     */
    public static class Operator{
        Character left;
        char[] middle;
        Character right;

        private Operator(char ch1, char[] ch2, char ch3){
            left = ch1;
            middle = ch2;
            right=ch3;
        }
        private Operator(char ch1, char[] ch2){
            left = ch1;
            middle = ch2;
            right=null;
        }
        private Operator(char[] ch2, char ch3){
            left = null;
            middle = ch2;
            right=ch3;

        }
        public Operator(char[] ch2){
            left = null;
            middle = ch2;
            right=null;
        }
        public String toString(){
            String answer="";
            if(left!=null){
                answer+=left;
            }
            if(middle!=null){
                answer+=String.copyValueOf(middle);
            }
            if(right!=null){
                answer+=right;
            }
            return answer;
        }
    }
//    public static class RightSide{
//        String stringRight;
//        Memory memoryRight;
//        RightSide(String text){
//            stringRight = text;
//            memoryRight=null;
//        }
//        RightSide(Memory rightSide){
//            stringRight=null;
//            memoryRight=rightSide;
//        }
//    }

    public void write(String varName, String value, HashMap<String, Memory> memories){
        if(memories.containsKey(varName)){
            memories.get(varName).write(value);
            return;
        }else{
            String key=findWagon(varName, memories);
            if(key!=null){
                memories.get(key).write(varName);
                return;
            }
        }
        System.err.println("Нет такой переменной "+varName);
    }
    public String read(String varName, HashMap<String, Memory> memories){
        if(memories.containsKey(varName)){
            return memories.get(varName).read();
        }else{
            String key=findWagon(varName,memories);
            if(key!=null){
                return memories.get(key).read(varName);
            }
        }
        System.err.println("Нет такой переменной "+varName);
        return null;
    }
    String findWagon(String varName, HashMap<String, Memory> memories){
        Set<String> keys = memories.keySet();
        for(String key:keys) {
            String[] parts = key.split("/*");
            if (parts.length > 1) {
                for (String part : parts) {
                    if (part.equals(varName)) {
                        return key;
                    }
                }
            }
        }
        return null;
    }
    void clear(String varName, HashMap<String, Memory> memories){
        if (memories.containsKey(varName)){
            memories.get(varName).clear();
            return;
        }else{
            String wagon=findWagon(varName, memories);
            if (wagon!=null){
                memories.get(wagon).clear();
                return;
            }
        }
        System.err.println("Нет такой переменной "+varName);
    }

    public void doStatement(Storage storage, Tape tape){
        //"'a'<-'b'"
        // 'hello' /<- 'b'
        if(this.operator.middle == "<-".toCharArray()) {
            if (this.operator.left != null){
                if (this.leftMem != null)
                    this.leftMem.clear();
                else System.err.println("Как ты собираешься константную строку удалять и записывать в нее?!!");
            }
            leftMem.write(this.rightMem.read(),this.rightMem)

            if(this.operator.right != null)
                if(this.rightMem != null)
                    this.rightMem.clear();
                else System.err.println("Как ты собираешься константную строку удалять?!!");
        } else if(this.operator.middle == "->".toCharArray()) {
            if(this.operator.right != null)
                if(this.rightMem != null)
                    this.rightMem.clear();
                else System.err.println("Как ты собираешься константную строку удалять и записывать в нее?!!");
        }
    }

//
//    public Statement(String leftArg, Operator operator, String rightArg){
//        this.leftArg=leftArg;
//        this.operator=operator;
//        this.rightArg=rightArg;
//    }
//    public String toString(){
//        return leftArg.toString()+operator.toString()+rightArg.toString();
//    }
//    public static void main(String[] args) {
//        Logic.Statement statement=new Logic.Statement(new Memories.Wagon("ЛВ","ПВ",new ArrayList<String>(Arrays.asList("first,Second"))),new Operator("<-".toCharArray()),new Memories.Register(""));
//
//    }
}
