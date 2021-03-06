import org.apache.jackrabbit.core.TransientRepository;

import javax.jcr.*;
import java.io.File;
import java.util.Iterator;

public class Main {
    private static final String REP_DEFAULT_DIRECTORY = "C:/jcr_dir";

    public static void main(String[] args)  {
        Repository repository = new TransientRepository(getRepositoryDir());

        Session session = null;
        try{
            session = repository.login(
                    new SimpleCredentials("admin","admin".toCharArray()));            

            Node node = session.getRootNode();

//            node.addNode("content");
//            session.save();
            
            Iterator<Node> iterator = node.getNodes();
            
            while (iterator.hasNext()) 
                p(iterator.next().getPath());
                
        } catch (RepositoryException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isLive()) session.logout();
        }
    }

    private static File getRepositoryDir(){
        File file = new File(REP_DEFAULT_DIRECTORY);
        
        if (!file.exists()) file.mkdir();
        
        return file;
    }
    
    public static void p(Object o){
        System.out.println(o);
    }
}
