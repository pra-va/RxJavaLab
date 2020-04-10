package me.escoffier.lab.chapter5;

import io.reactivex.Observable;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Code11 {

	private static final Path DIRECTORY = new File("src/main/resources/super/heroes").toPath();

	public static void main(String[] args) {
		Observable<String> files = getHeroesNames();
		files.subscribe(value -> System.out.println("Subscriber 1: " + value),
			Throwable::printStackTrace);
		files.subscribe(value -> System.out.println("Subscriber 2: " + value),
			Throwable::printStackTrace);
	}
	
	private static Observable<String> getHeroesNames() {
		return Observable.<Path>create(emitter -> {
			DirectoryStream<Path> stream;
			// Emit the directory stream here.
			try {
				stream = Files.newDirectoryStream(DIRECTORY);
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
			for(Path path : stream) {
				emitter.onNext(path);
			}
		}).map(path -> path.toFile().getName());
	}

}
