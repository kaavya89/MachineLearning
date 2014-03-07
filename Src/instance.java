package src;
import java.io.*;
import java.util.*;

/******************** Program that writes an instace file **********************/
public class instance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int classifier;		
		try
		{
			File f1 = new File(args[0]);
			File f = new File(args[1]);
			for(File name:f1.listFiles())
			{				
				String fname = name.getName();
				String newname = fname.substring(0,fname.indexOf("."));			
				String tok[],temp;
				StringTokenizer str;
					FileReader fn = new FileReader(name);
					System.out.println(f.getAbsolutePath());
					FileWriter fw = new FileWriter(f.getAbsolutePath()+"/"+newname+".arff");
					BufferedWriter out = new BufferedWriter(fw);			
					Scanner sc = new Scanner(fn);			
					out.write("@relation BinaryData");
					out.newLine();
					out.newLine();			
					str =new StringTokenizer(sc.nextLine(),",");
					while(str.hasMoreTokens())
					{
						out.write("@attribute "+str.nextToken()+" numeric");				
						out.newLine();			
					}
					out.newLine();
					out.write("@data");
					out.newLine();
					out.newLine();			
					while(sc.hasNext())
					{				
						temp=sc.nextLine();
						tok =temp.split(",");				
						classifier=(Integer.parseInt(tok[0])|Integer.parseInt(tok[1]))&(Integer.parseInt(tok[2])|Integer.parseInt(tok[3])|Integer.parseInt(tok[4]));
						out.write(temp);
						out.write(",");
						out.write(String.valueOf(classifier));
						out.newLine();
					}
					out.flush();
					sc.close();
					out.close();
			}
		}catch(Exception e){}		

	}

}
