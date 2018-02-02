/**
 * 
 */
package main.java.com.accenture.javasetupautomation.services;

import java.io.File;
import java.io.IOException;

import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JPackage;

/**
 * @author p.senthilrajan
 *
 */
public class GenerateStructure {

	public void CreateBaseFolder(String projectName){
		File fileObject = new File(projectName);
		if(!fileObject.isDirectory()){
			fileObject.mkdir();
			System.out.println("Directory Created successfully");
		}else{
			System.out.println("Dirctory already available");
		}
	}
	
	public void CreateBasicClass(String fileObject, String projectName, String packageName) throws JClassAlreadyExistsException, IOException{
		File commonFileStructure = new File(fileObject);
		commonFileStructure.mkdirs();
		System.out.println(commonFileStructure.getAbsolutePath());

		JCodeModel modelObject = new JCodeModel();
		JPackage packageObject = modelObject._package(packageName);
		JDefinedClass classObject = packageObject._class(projectName);
		
		classObject.javadoc().add("Generated class");

		JMethod getVar = classObject.method(JMod.PUBLIC | JMod.STATIC, modelObject.VOID , "main");
		
		modelObject.build(commonFileStructure);
	}

	public void CreateInterfaceWithImplementation(String fileObject, String projectName, String packageName) throws JClassAlreadyExistsException, IOException{
		File commonFileStructure = new File(fileObject);
		commonFileStructure.mkdirs();
		System.out.println(commonFileStructure.getAbsolutePath());

		JCodeModel modelObject = new JCodeModel();
		JPackage packageObject = modelObject._package(packageName);
		JDefinedClass interfaceObject = packageObject._interface(projectName);
		
		JPackage packageObject1 = modelObject._package(packageName+".impl");
		JDefinedClass classObject = packageObject1._class(projectName+"Impl");
		classObject.javadoc().add("Generated class");
		classObject._implements(interfaceObject);
		
		modelObject.build(commonFileStructure);
	}

	public void CreateClass(String fileObject, String projectName, String packageName) throws JClassAlreadyExistsException, IOException{
		File commonFileStructure = new File(fileObject);
		commonFileStructure.mkdirs();
		System.out.println(commonFileStructure.getAbsolutePath());

		JCodeModel modelObject = new JCodeModel();
		JPackage packageObject = modelObject._package(packageName);
		JDefinedClass classObject = packageObject._class(projectName);
		classObject.javadoc().add("Generated class");
		
		modelObject.build(commonFileStructure);
	}

	public void DevelopmentStructure(String projectName){
		try {
			String basePackageName = "com.accenture."+projectName.toLowerCase();
			String fileObject = projectName+"/src/main/java/";
			
			System.out.println("Create DAO folder and its IMPL");
			CreateInterfaceWithImplementation(fileObject, projectName, basePackageName+".dao");

			System.out.println("Create PC folder and its IMPL");
			CreateInterfaceWithImplementation(fileObject, projectName, basePackageName+".pc");

			System.out.println("Create providers folder");
			CreateClass(fileObject, projectName, basePackageName+".providers");

			System.out.println("Create services folder");
			CreateClass(fileObject, projectName, basePackageName+".services");

			System.out.println("Create dto folder");
			CreateClass(fileObject, projectName, basePackageName+".dto");
			
			System.out.println("Create interceptor folder");
			CreateClass(fileObject, projectName, basePackageName+".interceptor");

		} catch (JClassAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void TestStructure(String projectName){
		try {

			String basePackageName = "com.accenture."+projectName.toLowerCase();
			String fileObject = projectName+"/src/test/java/";
			
			System.out.println("Create Test - DAO folder and its IMPL");
			CreateInterfaceWithImplementation(fileObject, projectName, basePackageName+".dao");

			System.out.println("Create Test - PC folder and its IMPL");
			CreateInterfaceWithImplementation(fileObject, projectName, basePackageName+".pc");

			System.out.println("Create Test - providers folder");
			CreateClass(fileObject, projectName, basePackageName+".providers");

		} catch (JClassAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public final void createStructure(String projectName) throws JClassAlreadyExistsException, IOException{

		System.out.println("Create Base folder --  FolderName as same as project name");
		CreateBaseFolder(projectName);
		System.out.println("Creating base class with main method");
		String basePackageName = "com.accenture."+projectName.toLowerCase();
		String fileObject = projectName+"/src/main/java/";
		CreateBasicClass(fileObject, projectName, basePackageName);

		DevelopmentStructure(projectName);
		TestStructure(projectName);

	}
}
