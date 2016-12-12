package Other;

import Logic.Arm;
import Logic.ArmLine;
import Logic.Condition;
import Logic.Statement;
import Memories.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by Anton on 16.10.2016.
 * здесь будет непосредственный обработчик
 * или не здесь
 * ОБНОВЛЕНИЕ: по замыслу здесь
 */
public class R_machine {
    AllStorage allStorage;
    Storage storage;
    Tape tape;
    private Stage primaryStage;
    private Pane rootLayout;

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
    public void analyzer(){
        HashMap<String, Arm> arms = this.allStorage.getStorage().arms;
        Arm firstArm=null;
        if(arms.containsKey("0")){
            firstArm=arms.get("0");
        }else{
            System.err.println("Невозможно обработать алгоритм без нулевой вершины");
            System.exit(-1);
        }
        ArrayList<ArmLine> lines = firstArm.getLines();//обход ребер одной вершины ( в данном случае первой, т.е. с номером "0"
        for(ArmLine line:lines){
            if(line.compare(this.tape)){ //Если условие в данном ребре истинно...
                String endNumber = line.getEndArmNumber();
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
                    try {
                        FileWriter file = new FileWriter("D:\\Java_programs\\RMachineMain_1\\src\\data\\ResultFile.txt");
                        String buftext = null;
                        System.out.println("Конец программы");
                        Set<String> names = this.allStorage.storage.getMemories().keySet();
                        for(String name:names){
                            buftext += this.allStorage.storage.getMemories().get(name).toString() + "\n";
                            System.out.println(this.allStorage.storage.getMemories().get(name));
                        }
                        file.write(buftext);
                        file.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    return;
                }
                analyzer(endNumber); //Если программа продолжается ( т.е. не был указан конец ("#"), переход к обработке узла с номером, указанным в ребре.
                return;
            }
        }

    }
    public void analyzer(String armNumber){
        HashMap<String, Arm> arms = this.allStorage.getStorage().arms;
        Arm firstArm=null;
        if(arms.containsKey(armNumber)){
            firstArm=arms.get(armNumber);
        }else{
            System.err.println("Ошибка в алгоритме: не существует вершины под номером: "+armNumber);
            System.exit(-1);
        }
        ArrayList<ArmLine> lines = firstArm.getLines();
        for(ArmLine line:lines){
            if(line.compare(this.tape)){
                String endNumber = line.getEndArmNumber();
                for(Statement statement:line.getStatements()){
                    statement.doStatement(storage,tape);
                }
                char tapeCurrent=this.tape.readCurrent();
                if(tapeCurrent=='#'){
                    System.out.println("Конец программы");
                    Set<String> names = this.allStorage.storage.getMemories().keySet();
                    for(String name:names){
                        System.out.println(this.allStorage.storage.getMemories().get(name));
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
                analyzer(endNumber);
                return;
            }
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
        r_machine.analyzer();

    }

}
