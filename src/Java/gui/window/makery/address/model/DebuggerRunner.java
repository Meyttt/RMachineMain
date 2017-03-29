package gui.window.makery.address.model;

import Other.*;
import XmlReader.AlgorithmReaderNew;


/**
 * Created by Admin on 28.01.2017.
 */
public class DebuggerRunner {
	public static void main (String[] args) throws Exception{
		AlgorithmReaderNew algorithmReader = new AlgorithmReaderNew("templateStrorageTest.xml");
		algorithmReader.readMemories();
		algorithmReader.readAlgorithm();
		Tape tape = new Tape("perfectapple#");
		Storage storage = new Storage(algorithmReader.arms,algorithmReader.memoryHashMap,algorithmReader.alphabetHashMap);
		AllStorage allStorage = new AllStorage(storage,tape);
		WorkExchange workExchange = new WorkExchange();
		R_machine r_machine = new R_machine(allStorage,workExchange);
		r_machine.setDaemon(true);
		DebuggerWindow debugger = new DebuggerWindow(workExchange);
		Thread threaddb = new Thread(debugger);
		r_machine.start();
		threaddb.start();
	}
}
