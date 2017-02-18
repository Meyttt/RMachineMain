package Other;

import Logic.Arm;
import Logic.ArmLine;
import Logic.Condition;
import Logic.Statement;
import Memories.*;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by Anton on 16.10.2016.
 * здесь будет непосредственный обработчик
 * или не здесь
 * ОБНОВЛЕНИЕ: по замыслу здесь
 */
public class R_machine extends Thread implements Runnable{
    AllStorage allStorage;
    Storage storage;
    Tape tape;
    private Stage primaryStage;
    private Pane rootLayout;
    private TextArea textArea;
    public String endNumber=null;

    public R_machine(AllStorage allStorage) {
        this.allStorage=allStorage;
        this.storage=allStorage.getStorage();
        this.tape=allStorage.getTape();
    }
    public Arm start(){
        Set<String> armnumbers = storage.arms.keySet();
        Arm firstArm = null;
        if(armnumbers.contains("0")){
            firstArm=storage.arms.get("0");
        }else{
            System.err.println("Нет узла с нулевым номером!");
        }
        return firstArm;
    }

    public void checkArm(Arm curretArm) {
        ArrayList<ArmLine> lines = curretArm.getLines();
        for(ArmLine line:lines){
            if(line.compare(tape)){

            }
        }
    }

    /**
     * Основной цикл обхода алгортма. Без аргуентов вызывается один аз при запуске Р-машины.
     */

    public synchronized void run(){
        Arm firstArm=null;
        HashMap<String, Arm> arms = this.allStorage.getStorage().arms;
        if(endNumber==null){
            if(arms.containsKey("0")){
                firstArm=arms.get("0");
                endNumber="0";
            }else{
                System.err.println("Невозможно обработать алгоритм без нулевой вершины");
                System.exit(-1);
            }
        }else{
            firstArm=arms.get(endNumber);
        }
        String endNumber =null;
        ArrayList<ArmLine> lines = firstArm.getLines();//обход ребер одной вершины ( в данном случае первой, т.е. с номером "0"
        for(ArmLine line:lines){
                try {
                    System.out.println("R-Machine now waiting in condition: "+line.getCondition());
                this.wait();
            } catch (InterruptedException e) {

            }
            if(line.compare(this.tape)){ //Если условие в данном ребре истинно...
                endNumber=line.getEndArmNumber();
                for(Statement statement:line.getStatements()){ //выполнение всех выражений (операций) , перечисленных в ребре
                    try{
                        System.out.println("R machine is now doing:"+statement);
                        this.wait();
                    } catch (InterruptedException e) {

                    }
                    statement.doStatement(storage,tape);
                }
                //if ( this.allStorage.getTape().)
//                if(Objects.equals(endNumber, "#")) { //если указано, что следующая вершина конечная - вывод состояний вех памятей и возврат из обхода
//                    System.out.println("Конец программы");
//                    Set<String> names = this.allStorage.storage.getMemories().keySet();
//                    for(String name:names){
//                        System.out.println(this.allStorage.storage.getMemories().get(name));
//                    }
//                    return;
//                }
//                if(endNumber == "#") {
//                    System.out.println("Конец программы");
//                    Set<String> names = this.allStorage.storage.getMemories().keySet();
//                    for(String name:names){
//                        System.out.println(this.allStorage.storage.getMemories().get(name));
//                    }
//                    return;
//                }
                char tapeCurrent=this.tape.readCurrent();
                if(tapeCurrent=='#'){
//                    System.out.println("Конец программы");
//                    try {
//                        FileWriter file = new FileWriter("data/ResultFile.txt");
//                        String buftext = null;
//
//                        Set<String> names = this.allStorage.storage.getMemories().keySet();
//                        for(String name:names){
//                            buftext += this.allStorage.storage.getMemories().get(name).toString() + "\n";
//                            System.out.println(this.allStorage.storage.getMemories().get(name));
//                        }
//                        file.write(buftext);
//                        file.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }

                    return;
                }
                this.endNumber=endNumber;
                run(); //Если программа продолжается ( т.е. не был указан конец ("#"), переход к обработке узла с номером, указанным в ребре.
                return;
            }
        }

    }

    public synchronized void run(TextArea textArea){
        this.textArea = textArea;
        HashMap<String, Arm> arms = this.allStorage.getStorage().arms;
        Arm firstArm=null;
        if(arms.containsKey("0")){
            firstArm=arms.get("0");
        }else{
            System.err.println("Невозможно обработать алгоритм без нулевой вершины");
            System.exit(-1);
        }
        ArrayList<ArmLine> lines = firstArm.getLines();//обход ребер одной вершины ( в данном случае первой, т.е. с номером "0"
        String endNumber =null;
        for(ArmLine line:lines){
            if(line.compare(this.tape)){ //Если условие в данном ребре истинно...
                endNumber = line.getEndArmNumber();
                for(Statement statement:line.getStatements()){ //выполнение всех выражений (операций) , перечисленных в ребре
                    statement.doStatement(storage,tape);
                }
               //if ( this.allStorage.getTape().)
//                if(Objects.equals(endNumber, "#")) { //если указано, что следующая вершина конечная - вывод состояний вех памятей и возврат из обхода
//                    System.out.println("Конец программы");
//                    Set<String> names = this.allStorage.storage.getMemories().keySet();
//                    for(String name:names){
//                        System.out.println(this.allStorage.storage.getMemories().get(name));
//                    }
//                    return;
//                }
//                if(endNumber == "#") {
//                    System.out.println("Конец программы");
//                    Set<String> names = this.allStorage.storage.getMemories().keySet();
//                    for(String name:names){
//                        System.out.println(this.allStorage.storage.getMemories().get(name));
//                    }
//                    return;
//                }
                char tapeCurrent=this.tape.readCurrent();
                if(tapeCurrent=='#'){
                    textArea.appendText("Конец программы\n");
                    System.out.println("Конец программы");
//                    try {
//                        FileWriter file = new FileWriter("data/ResultFile.txt");
//                        String buftext = "";
//                        Set<String> names = this.allStorage.storage.getMemories().keySet();
//                        for(String name:names){
//                            buftext += this.allStorage.storage.getMemories().get(name).toString() + "\n";
//                            textArea.appendText(String.valueOf(this.allStorage.storage.getMemories().get(name) + "\n"));
//                            System.out.println(this.allStorage.storage.getMemories().get(name));
//                        }
//                        file.write(buftext);
//                        file.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }

                    return;
                }
                this.endNumber=endNumber;
                run(); //Если программа продолжается ( т.е. не был указан конец ("#"), переход к обработке узла с номером, указанным в ребре.
                return;
            }
        }
        if (endNumber==null){
            System.err.println("Нет выхода из вершины под номером 0");

        }

    }
//    public void run(String armNumber){
//        HashMap<String, Arm> arms = this.allStorage.getStorage().arms;
//        Arm firstArm=null;
//        if(arms.containsKey(armNumber)){
//            firstArm=arms.get(armNumber);
//        }else{
//            System.err.println("Ошибка в алгоритме: не существует вершины под номером: "+armNumber);
//            System.exit(-1);
//        }
//        ArrayList<ArmLine> lines = firstArm.getLines();
//        for(ArmLine line:lines){
//            if(line.compare(this.tape)){
//                String endNumber = line.getEndArmNumber();
//                for(Statement statement:line.getStatements()){
//                    statement.doStatement(storage,tape);
//                }
//                char tapeCurrent=this.tape.readCurrent();
//                if(tapeCurrent=='#'){
//                    System.out.println("Конец программы");
//                    Set<String> names = this.allStorage.storage.getMemories().keySet();
//                    for(String name:names){
//                        System.out.println(this.allStorage.storage.getMemories().get(name));
//                    }
//                    return;
//                }
//
////                if(endNumber == "#") {
////                    System.out.println("Конец программы");
////                    Set<String> names = this.allStorage.storage.getMemories().keySet();
////                    for(String name:names){
////                        System.out.println(this.allStorage.storage.getMemories().get(name));
////                    }
////                    return;
////                }
//                run(endNumber);
//                return;
//            }
//        }
//
//    }

    public synchronized void run(String armNumber){
        HashMap<String, Arm> arms = this.allStorage.getStorage().arms;
        Arm firstArm=null;
        if(arms.containsKey(armNumber)){
            firstArm=arms.get(armNumber);
        }else{
            System.err.println("Ошибка в алгоритме: не существует вершины под номером: "+armNumber);
            System.exit(-1);
        }
        ArrayList<ArmLine> lines = firstArm.getLines();
        String endNumber = null;
        for(ArmLine line:lines){
            if(line.compare(this.tape)){

                endNumber = line.getEndArmNumber();
                for(Statement statement:line.getStatements()){
                    statement.doStatement(storage,tape);
                }
                char tapeCurrent=this.tape.readCurrent();
                if(tapeCurrent=='#'){
                    try {
                        this.textArea.appendText("Конец программы");
                        System.out.println("Конец программы");
                        Set<String> names = this.allStorage.storage.getMemories().keySet();
                        for (String name : names) {
                            this.textArea.appendText(String.valueOf(this.allStorage.storage.getMemories().get(name)));
                            System.out.println(this.allStorage.storage.getMemories().get(name));
                        }
                    }catch(NullPointerException e1){
                        System.out.println("Конец программы");
                        Set<String> names = this.allStorage.storage.getMemories().keySet();
                        for (String name : names) {
                            System.out.println(this.allStorage.storage.getMemories().get(name));
                        }
                    }
                    return;
                }

//                if(endNumber == "#") {
//                    System.out.println("Конец программы");
//                    Set<String> names = this.allStorage.storage.getMemories().keySet();
//                    for(String name:names){
//                        System.out.println(this.allStorage.storage.getMemories().get(name));
//                    }
//                    return;
//                }
                run(endNumber);
                return;
            }
        }
        if (endNumber==null){
            System.err.println("Нет выхода из вершины под номером "+armNumber);
            System.out.println("Конец программы");
            Set<String> names = this.allStorage.storage.getMemories().keySet();
            for (String name : names) {
                System.out.println(this.allStorage.storage.getMemories().get(name));
            }
        }

    }

    public HashMap<String,Memory> getMemories(){
        return this.allStorage.storage.getMemories();

    }
    public void printMemories(){
        Set<String> names = this.allStorage.storage.getMemories().keySet();
        for(String name:names){
            System.out.println(this.allStorage.storage.getMemories().get(name));
        }
    }

    public static void main(String args[]) throws FileNotFoundException {
        HashMap<String, Arm> arms= new HashMap<>();
        HashMap<String,Memory> memories = new HashMap<>();
        HashMap<String,Alphabet> alphabets = new HashMap<>();

        //Cоздание ленты ввода
        Tape tape = new Tape("t#");
        Alphabet alphabet = new Alphabet("Alphabet", "Alph","abcdefghi".toCharArray());
        alphabets.put(alphabet.getFullname(),alphabet);

        //Создание памятей пустыми (начальное сстояние)
        Wagon wag1 = new Wagon("LW","RW", null);
        Register reg1 = new Register("reg1",null);
        Register reg2 = new Register("reg2", null);
        Table table = new Table("tab"/*,new String[]{"0","1"}*/);


        memories.put(reg1.getname(),reg1);
        memories.put(reg2.getname(),reg2);
        memories.put("LW*RW",wag1);
        memories.put("tab",table);


        ArrayList<ArmLine> armlines = new ArrayList<>();
        ArrayList<Statement> statements01 = new ArrayList<>();
        statements01.add(new Statement("LW",Statement.getOperator("<-"),"Cat"));
        statements01.add(new Statement("RW",Statement.getOperator("<-"),"Dog"));
        statements01.add(new Statement("LW*RW",Statement.getOperator("<-"),"Animals"));
        statements01.add(new Statement("reg2",Statement.getOperator("<-"),"13/3-6"));
        statements01.add(new Statement("tab.engineer",Statement.getOperator("<-"),"Scotty"));
        ArmLine arm01 = new ArmLine("0",new Condition("t"),statements01,"1");
        armlines.add(arm01);

        ArrayList<Statement> statements02 = new ArrayList<>();
        statements02.add(new Statement("reg1",Statement.getOperator("<-"),"nop"));
        statements02.add(new Statement("reg2",Statement.getOperator("<-"),"Kirk"));
        ArmLine arm02 = new ArmLine("0",new Condition("test"),statements02,"2");
        armlines.add(arm02);
        Arm arm0 = new Arm("0", armlines);

        ArrayList<ArmLine> armlines1 = new ArrayList<>();
        ArrayList<Statement> statements12 = new ArrayList<>();
        statements12.add(new Statement("reg1",Statement.getOperator("<-"),"test"));
        statements12.add(new Statement("tab.engineer",Statement.getOperator("<-"),"Scotty"));
        statements12.add(new Statement("tab.woman",Statement.getOperator("<-"),"Kate"));
        statements12.add(new Statement("tab.0",Statement.getOperator("^="),"Nik"));
        statements12.add(new Statement("tab.commander",Statement.getOperator(".="),"Spok"));
        statements12.add(new Statement("tab.key",Statement.getOperator("<-"),"Nik"));
//        statements12.add(new Statement("tab.Name",Statement.getOperator("&="),"Nik"));
        statements12.add(new Statement("tab.key",Statement.getOperator("|-"),"tab.key = "));
        statements12.add(new Statement("reg1",Statement.getOperator("/-|"),"Input value of reg1: "));
        statements12.add(new Statement("RW",Statement.getOperator("->"),"reg2"));
        ArmLine arm12 = new ArmLine("1",new Condition("*"),statements12,"#");
        armlines.add(arm12);
        Arm arm1 = new Arm("1", armlines1);
        arms.put("0",arm0);
        arms.put("1",arm1);
        AllStorage allStorage = new AllStorage(new Storage(arms,memories,alphabets),tape);
        R_machine r_machine = new R_machine(allStorage);
        r_machine.run();

    }

}
