package gui.window.makery.address.model;

import Other.AllStorage;
import Other.R_machine;
import Other.Storage;
import Other.Tape;
import XmlReader.AlgorithmReaderNew;
import com.sun.corba.se.spi.orbutil.threadpool.NoSuchThreadPoolException;
import com.sun.corba.se.spi.orbutil.threadpool.ThreadPool;
import com.sun.corba.se.spi.orbutil.threadpool.ThreadPoolChooser;
import com.sun.corba.se.spi.orbutil.threadpool.ThreadPoolManager;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

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
		Thread threadrm = new Thread(r_machine);
		threadrm.setDaemon(true);
//		Debugger debugger = new Debugger();
		ExecutorService executorService= Executors.newFixedThreadPool(2);
//		executorService.execute(debugger);

		DebuggerWindow debugger = new DebuggerWindow();
		debugger.setRmThread(threadrm,r_machine);
		Thread threaddb = new Thread(debugger);
		threaddb.start();

		Thread.sleep(10000);

		threadrm.start();





	}
}
