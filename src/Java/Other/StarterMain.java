package Other;

import XmlReader.AlgorithmReaderNew;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

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

    public StarterMain(String filepath, boolean debugType, String tape) throws ParserConfigurationException, SAXException, IOException {
        this.filepath = filepath;
        this.debugType = debugType;
        this.tape = tape;
        start();
    }

    public String start() throws IOException, SAXException, ParserConfigurationException {
        AlgorithmReaderNew algorithmReader = new AlgorithmReaderNew(filepath);
        algorithmReader.readMemories();
        algorithmReader.readAlgorithm();
        Storage storage = new Storage(algorithmReader.arms,algorithmReader.memoryHashMap,algorithmReader.alphabetHashMap);
        AllStorage allStorage = new AllStorage(storage,new Tape(tape));
        R_machine r_machine = new R_machine(allStorage);
        r_machine.run();
        return "0";
    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        StarterMain starterMain = new StarterMain("templateStrorageTest.xml",false,"perfectapple#");
    }

}
