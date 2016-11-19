package Other;

import Logic.Arm;
import Logic.ArmLine;
import Logic.*;
import Memories.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
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
    public void analyzer(){
        HashMap<String, Arm> arms = this.allStorage.getStorage().arms;
        Arm firstArm=null;
        if(arms.containsKey("0")){
            firstArm=arms.get("0");
        }else{
            System.err.println("Невозможно обработать алгоритм без нулевой вершины");
            System.exit(-1);
        }
        ArrayList<ArmLine> lines = firstArm.getLines();
        for(ArmLine line:lines){
            if(line.compare(this.tape)){
                String endNumber = line.getEndArmNumber();
                for(Statement statement:line.getStatements()){
                    statement.doStatement(storage,tape);
                }
                if(endNumber == "#") {
                    System.out.println("Конец программы");
                    return;
                }
                analyzer(endNumber);
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
                if(endNumber == "#") {
                    System.out.println("Конец программы");
                    return;
                }
                analyzer(endNumber);
            }
        }

    }



    public static void main(String args[]) throws FileNotFoundException {
        Tape tape = new Tape("thisIsTape");
        Register reg1 = new Register("reg1",null);
        Register reg2 = new Register("reg2", null);
        ArrayList<ArmLine> armlines = new ArrayList<>();
        ArrayList<Statement> statements01 = new ArrayList<>();
        statements01.add(new Statement(reg1,Statement.getOperator("<-"),"Cat"));
        statements01.add(new Statement(reg2,Statement.getOperator("<-"),"Dog"));
        ArmLine arm01 = new ArmLine("0",new Condition("t"),statements01,"1");
        armlines.add(arm01);
        ArrayList<Statement> statements02 = new ArrayList<>();
        statements01.add(new Statement(reg1,Statement.getOperator("<-"),"Spok"));
        statements01.add(new Statement(reg2,Statement.getOperator("<-"),"Kirk"));
        ArmLine arm02 = new ArmLine("0",new Condition("t"),statements02,"2");
        armlines.add(arm02);
        Arm arm1 = new Arm("0", armlines);
    }
}
