package com.sinius15.updater;

import java.io.File;

/**
 * This is a super small application. You give the program arguments and that
 * arguments are run in the terminal. For example: <br>
 * <code> updater.jar "echo 'This is a test' > test.txt" "echo 'test 2' > test2.txt" </code>
 * <br>
 * This will create two files: test.txt with in it the text "This is a test" and
 * a second file test2.txt with the text "test 2" in it.<br>
 * <br>
 * If the first argument starts with <code> cd </code> than all the commands
 * will be excecuted from the specified directory. Example: <br>
 * 
 * <code> updater.jar "cd /foo/" "echo 'This is a test' > test.txt"  </code><br>
 * This will  create a file test.txt in the subDir called foo.
 * 
 * @author Sinius15
 * @see www.sinius15.com
 */
public class Updater {
	
	public static void main(String[] args) {
		File startDir = null;
		if (args.length > 0 && args[0].startsWith("cd ")) {
			startDir = new File(args[0].substring(3));
			args[0] = "";
		}
		for (String command : args) {
			if (command.equals(""))
				continue;
			System.out.println("now running command: " + command);
			try {
				ProcessBuilder builder = new ProcessBuilder(args);
				builder.redirectErrorStream(true);
				if (startDir != null)
					builder.directory(startDir);
				Process process = builder.start();
				
				new StreamStreamer(process.getInputStream(), "Output").start();
				new StreamStreamer(process.getInputStream(), "Error").start();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
}
