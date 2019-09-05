import java.io.File;


public class FileDemo {
   public static void main(String[] args) {
      
      File f = null;
      String path;
      long len;
      boolean bool = false;
      
      try{      
         // create new file
         f = new File("C:/Users/Administrator/Desktop/test.txt");
         
         // true if the file path is a file, else false
         bool = f.exists();
         
         // if path exists
         if(bool)
         {
            // returns the length in bytes文件的大小
            len = f.length();
                                 
            // path
            path = f.getPath();
            
            // print
            System.out.print(path+" file length: "+len);
         }
      }catch(Exception e){
         // if any error occurs
         e.printStackTrace();
      }
   }
}