package mase.ericsson.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mase.ericsson.dao.ApachePOIExcelRead;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;



@Singleton
@Startup
public class DatasetDirectoryWatcher {
    public static final Logger LOGGER = LoggerFactory.getLogger(DatasetDirectoryWatcher.class);

    
    @Inject
//	private NetworkEventsDAO populate;
	private ApachePOIExcelRead makeList;
	

    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(120);
    private String path = "C:/Users/David/git/team-possible/mase-ericsson-project/uploadData/";
    private String file = "autoUploadDataset.xls";
    private boolean disableScheduler = true;
    private ScheduledFuture<?> scheduledFuture;

    @PostConstruct
    public void init() {
        Path dir = null;
        try {
            dir = Paths.get(path + "read/");
            Files.createDirectories(dir);
        } catch (IOException e) {
            e.printStackTrace();
            String absolutePath = dir.toAbsolutePath().toString();
            LOGGER.warn(String.format("could not create read directory in %s", absolutePath));
        }
        System.out.println("################***************Vist init method*******************88");

        if (scheduledFuture == null)
            scheduledFuture = scheduler.scheduleWithFixedDelay(this::runScheduleRead, 10, 10, TimeUnit.SECONDS);
        
        
        
        
    }

    private void runScheduleRead() {
        if (disableScheduler) {
            try {
            	//System.out.println("################***************Before Poll directory *******************88");
                pollDirectory();
            } catch (FileNotFoundException e) {
               // e.printStackTrace();
            }
        }
    }

    /**
     * polls the watched directory, calling this method will force a poll,
     * rather than waiting for the scheduler to poll
     */
    public synchronized Boolean pollDirectory() throws FileNotFoundException {
        File fileToBeRead = new File(path + this.file);
        if (fileToBeRead.exists()) {
            String absolutePath = fileToBeRead.getAbsolutePath();
            LOGGER.info(String.format("Reading file: %s", absolutePath));
           // int count = persistData();
            System.out.println("################***************Absolute path  *******************+ " + absolutePath);
            Boolean listMade =makeList.readExcel(absolutePath);
   		 if(listMade) {
//   		 populate.addData(dbList);
   			 System.out.println("successful list made and upload to DB");
   		 }
            boolean moveReadFile = moveReadFile();
            if (!moveReadFile)
                LOGGER.warn("Could not move file after persisting data");
            return listMade;
        }else{
            throw new FileNotFoundException(fileToBeRead.getName()+" Does not exist");
        }

    }

   

    /**
     * Moves data file to the read directory and renames it to epoch time in milliseconds
     *
     * @return if successfully moves file
     */
    public boolean moveReadFile() {
        File fileToMove = new File(path + this.file);
        long newFileName = System.currentTimeMillis();

        return fileToMove.renameTo(new File(path + "read/" + newFileName + ".xls"));
    }

    public void setFileAndPath(String file, String path) {
        this.file = file;
        this.path = path;
    }

    

    public boolean isDisableScheduler() {
        return disableScheduler;
    }

    public void setDisableScheduler(boolean disableScheduler) {
        this.disableScheduler = disableScheduler;
    }
}
