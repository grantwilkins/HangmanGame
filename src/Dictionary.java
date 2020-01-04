//Dictionary.java
//Grant Wilkins
//May 2018
//These methods are the basic methods for reading a file for a dictionary.
//the reason this is an interface is because this could be used for any game that
//reads random words.

import java.io.InputStream;

public interface Dictionary 
{
	public String randRead(int wordLength);
	public boolean readFile(InputStream filename);
}
