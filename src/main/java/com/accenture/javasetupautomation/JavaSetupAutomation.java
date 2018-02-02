/**
 * 
 */
package main.java.com.accenture.javasetupautomation;

import java.io.IOException;

import com.sun.codemodel.JClassAlreadyExistsException;

import main.java.com.accenture.javasetupautomation.services.GenerateStructure;

/**
 * @author p.senthilrajan
 *
 */
public class JavaSetupAutomation extends GenerateStructure{

	/**
	 * @param args
	 * @throws IOException 
	 * @throws JClassAlreadyExistsException 
	 */
	public static void main(String[] args) throws JClassAlreadyExistsException, IOException {
		// TODO Auto-generated method stub
		String projectName = "NPSServices";
		GenerateStructure myObject = new GenerateStructure();
//		myObject.CreateBaseFolder(projectName);
//		myObject.CreateClass(projectName);
//		myObject.CreateInterfaceWithImplementation(projectName);

		myObject.createStructure(projectName);
	}

}
