package gui.window.makery.address.model;

import Other.*;
import XmlReader.AlgorithmReaderNew;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
//		Debugger debugger = new Debugger();
//		ExecutorService executorService= Executors.newFixedThreadPool(1);
//		executorService.execute(debugger);
		//TODO: Зачем мне в конструкторе р_машина? может ее у3брать нахрен? +1
		DebuggerWindow debugger = new DebuggerWindow(r_machine,workExchange);
//		DebuggerWindow debugger = new DebuggerWindow(r_machine);
//		debugger.setR_machine(r_machine);
		Thread threaddb = new Thread(debugger);
		r_machine.start();
		threaddb.start();

		Thread.sleep(5000);







	}
}
