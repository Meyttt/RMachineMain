package gui.window.makery.address.model;

import Other.*;
import XmlReader.AlgorithmReaderNew;


/**
 * Created by Admin on 28.01.2017.
 */
public class DebuggerRunner {
	String filepath;
	String tape;

	public DebuggerRunner(String filepath, String tape) {
		this.filepath = filepath;
		this.tape = tape;
	}

	public void start () throws Exception{
		AlgorithmReaderNew algorithmReader = new AlgorithmReaderNew(filepath);
		algorithmReader.readMemories();
		algorithmReader.readAlgorithm();
		Tape tape = new Tape(this.tape);
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
