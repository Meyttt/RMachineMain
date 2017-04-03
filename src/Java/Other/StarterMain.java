package Other;

import XmlReader.AlgorithmReaderNew;
import gui.window.makery.address.model.DebuggerRunner;

/**
 * Created by master on 20.03.2017.
 */
public class StarterMain {

    /**
     *
     * @param args
     * 1)String Путь к хмл файлу программы
     * 2)boolean Тип запуска пока только прямой(не дебаг): false - run, true - debug
     * 3)String сама лента, ели null - ввод с консоли( выпрыгивающего окна)
     */

    String filepath;
    boolean debugType;
    String tape;

    public StarterMain(String filepath, boolean debugType, String tape) throws Exception {
        this.filepath = filepath;
        this.debugType = debugType;
        this.tape = tape;
        start();
    }

    public String start() throws Exception {
        if(!this.debugType) {
            AlgorithmReaderNew algorithmReader = new AlgorithmReaderNew(filepath);
            algorithmReader.readMemories();
            algorithmReader.readAlgorithm();
            Storage storage = new Storage(algorithmReader.arms, algorithmReader.memoryHashMap, algorithmReader.alphabetHashMap);
            AllStorage allStorage = new AllStorage(storage, new Tape(tape));
            R_machine r_machine = new R_machine(allStorage);
            r_machine.simpleRun();
            return "0";
        }else{
            DebuggerRunner debuggerRunner = new DebuggerRunner(this.filepath,this.tape);
            debuggerRunner.start();
        }
        return "0";
    }

    public static void main(String[] args) throws Exception {
        StarterMain starterMain = new StarterMain("templateStrorageTest.xml",false,"perfectapple#");
        StarterMain starterMain2 = new StarterMain("templateStrorageTest.xml",true,"perfectapple#");
    }

}
