
import com.googlecode.javacpp.Loader;
import com.googlecode.javacv.cpp.opencv_contrib;
import static com.googlecode.javacv.cpp.opencv_contrib.createEigenFaceRecognizer;
import com.googlecode.javacv.cpp.opencv_core;
import static com.googlecode.javacv.cpp.opencv_core.CV_AA;
import static com.googlecode.javacv.cpp.opencv_core.IPL_DEPTH_8U;
import static com.googlecode.javacv.cpp.opencv_core.cvClearMemStorage;
import static com.googlecode.javacv.cpp.opencv_core.cvCreateImage;
import static com.googlecode.javacv.cpp.opencv_core.cvGetSeqElem;
import static com.googlecode.javacv.cpp.opencv_core.cvGetSize;
import static com.googlecode.javacv.cpp.opencv_core.cvLoad;
import static com.googlecode.javacv.cpp.opencv_core.cvPoint;
import static com.googlecode.javacv.cpp.opencv_core.cvResetImageROI;
import static com.googlecode.javacv.cpp.opencv_core.cvSetImageROI;
import com.googlecode.javacv.cpp.opencv_highgui;
import static com.googlecode.javacv.cpp.opencv_highgui.CV_CAP_ANY;
import static com.googlecode.javacv.cpp.opencv_highgui.CV_LOAD_IMAGE_GRAYSCALE;
import static com.googlecode.javacv.cpp.opencv_highgui.cvCreateCameraCapture;
import static com.googlecode.javacv.cpp.opencv_highgui.cvDestroyWindow;
import static com.googlecode.javacv.cpp.opencv_highgui.cvQueryFrame;
import static com.googlecode.javacv.cpp.opencv_highgui.cvReleaseCapture;
import static com.googlecode.javacv.cpp.opencv_highgui.cvWaitKey;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_BGR2GRAY;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_INTER_LINEAR;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvCvtColor;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvEqualizeHist;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvResize;
import com.googlecode.javacv.cpp.opencv_objdetect;
import static com.googlecode.javacv.cpp.opencv_objdetect.CV_HAAR_DO_CANNY_PRUNING;
import static com.googlecode.javacv.cpp.opencv_objdetect.cvHaarDetectObjects;
import java.io.File;
import java.io.FilenameFilter;
import javax.swing.JOptionPane;


public class FaceRecog {
      public static opencv_core.IplImage binimg = null;
    public static opencv_core.IplImage resizedImage = opencv_core.IplImage.create(128, 152, 8, 1);
    private static final int SCALE = 2;
    private static final String CASCADE_FILE = "C:\\Users\\REC_UTLD11\\Downloads\\haarcascade_frontalface_default.xml";
    private static int count=0;
    public static int faceIden() {
        System.out.println("Image Processing Started");
        // boolean status = false;
        opencv_core.IplImage origin, img;
        opencv_highgui.cvNamedWindow("This");
        int head =0;
        count=0;
        int headfinal=0;
         int headtemp=0;
         opencv_highgui.CvCapture cap1 = cvCreateCameraCapture(CV_CAP_ANY);
        for (;;) {
           
            origin = cvQueryFrame(cap1);
           
            img = saveDetectedImage(origin);
            head = faceRecog();
            if(head==0)
            {
                count=0;
                continue;
            }
            if(headtemp==head){
                count++;
            }
            System.out.println(count);
            headtemp=head;
           
            if(count>35){
                headfinal=headtemp;
                break;
            }
            
            System.out.println("name :" + head);
            opencv_highgui.cvShowImage("This", origin);
            char c = (char) cvWaitKey(15);

            if (c == 'q' || c == 'Q') {
                break;
            }

        }
        
        cvReleaseCapture(cap1);
        cvDestroyWindow("This");
        return headfinal;

    }

    public static opencv_core.IplImage saveDetectedImage(opencv_core.IplImage origin) {
        //boolean status=false;

        Loader.load(opencv_objdetect.class);

        opencv_core.IplImage gray = cvCreateImage(cvGetSize(origin), IPL_DEPTH_8U, 1);
        cvCvtColor(origin, gray, CV_BGR2GRAY);

        opencv_core.IplImage small = opencv_core.IplImage.create(gray.width() / SCALE, gray.height() / SCALE, IPL_DEPTH_8U, 1);
        cvResize(gray, small, CV_INTER_LINEAR);
        cvEqualizeHist(small, small);
        opencv_core.CvMemStorage Storage = opencv_core.CvMemStorage.create();

        opencv_objdetect.CvHaarClassifierCascade cascade = null;
        try {
            cascade = new opencv_objdetect.CvHaarClassifierCascade(cvLoad(CASCADE_FILE));
        } catch (Exception ex) {
            System.out.println("Classifier not found");
        }
        System.out.print("Detecting faces");
        opencv_core.CvSeq faces = cvHaarDetectObjects(small, cascade, Storage, 1.5, 3, CV_HAAR_DO_CANNY_PRUNING);

        cvClearMemStorage(Storage);

        int total = faces.total();
        System.out.println("Total faces detected = " + total);
        if(total==0||total>1){
            System.out.print("Someome Else");
            
            count =0;
           
        }

        opencv_core.CvRect cvr = null;
        try {

            cvr = new opencv_core.CvRect(cvGetSeqElem(faces, 1));
            opencv_core.CvRect cvr2 = new opencv_core.CvRect(cvr.x() * SCALE, cvr.y(), (cvr.x() + cvr.width()), (cvr.y() + cvr.height() * SCALE));
            cvSetImageROI(origin, cvr2);

        } catch (NullPointerException npe) {
          return origin;
        }

        binimg = cvCreateImage(cvGetSize(origin), IPL_DEPTH_8U, 1);
        cvCvtColor(origin, binimg, CV_BGR2GRAY);
        cvResize(binimg, resizedImage);
        cvEqualizeHist(resizedImage, resizedImage);
        opencv_highgui.cvSaveImage("E:\\Computer Vision\\Astray Images\\temp2.png", resizedImage);//put your self-defined address here

        cvResetImageROI(origin);
      try{ opencv_core.cvRectangle(origin, cvPoint(cvr.x() * SCALE, cvr.y() * SCALE), cvPoint((cvr.x() + cvr.width()) * SCALE, (cvr.y() + cvr.height()) * SCALE), opencv_core.CvScalar.YELLOW, 6, CV_AA, 0);}
      catch(NullPointerException npe)
      {
          JOptionPane.showMessageDialog(null, "No Face detected");
          System.exit(1);
      }

        return origin;
    }

    public static int faceRecog() {
        String trainingDir = "E:\\Computer Vision\\MyTrainingSet";
        //The line below this can be changed and will be later 
        opencv_core.IplImage test = opencv_highgui.cvLoadImage("E:\\Computer Vision\\Astray Images\\temp2.png", CV_LOAD_IMAGE_GRAYSCALE);

        File root = new File(trainingDir);
        FilenameFilter imgFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                name = name.toLowerCase();
                return name.endsWith(".png");
            }

        };
        File[] imageFiles = root.listFiles(imgFilter);
        opencv_core.MatVector images = new opencv_core.MatVector(imageFiles.length);

        int[] labels = new int[imageFiles.length];

        int counter = 0;
        int label;
        opencv_core.IplImage img, grayimg = null;
        for (File image : imageFiles) {
            img = opencv_highgui.cvLoadImage(image.getAbsolutePath(), CV_LOAD_IMAGE_GRAYSCALE);

            labels[counter] = Integer.parseInt(image.getName().split("\\-")[0]);

            images.put(counter, img);
            counter++;
        }

        opencv_contrib.FaceRecognizer faceRecognizer = createEigenFaceRecognizer();
        faceRecognizer.train(images, labels);
        int predictLabel = faceRecognizer.predict(test);

     
        System.out.println(predictLabel);

        return predictLabel;

    }
    
}
