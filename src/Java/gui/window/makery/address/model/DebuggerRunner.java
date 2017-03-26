package gui.window.makery.address.model;

import Other.AllStorage;
import Other.R_machine;
import Other.Storage;
import Other.Tape;
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
		R_machine r_machine = new R_machine(allStorage);
		//todo: переписать на работу с тредом без машины
		Thread threadrm = new Thread(r_machine);
		threadrm.setDaemon(true);
//		Debugger debugger = new Debugger();
		ExecutorService executorService= Executors.newFixedThreadPool(2);
//		executorService.execute(debugger);

		DebuggerWindow debugger = new DebuggerWindow();
		debugger.setRmThread(threadrm,r_machine);
		Thread threaddb = new Thread(debugger);
		threaddb.start();

		Thread.sleep(5000);

		threadrm.start();





	}
}
