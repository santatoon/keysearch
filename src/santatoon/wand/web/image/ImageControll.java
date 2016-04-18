package santatoon.wand.web.image;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageControll {
	public ImageControll(){}
	
	public static boolean createThumbnail(File saveFile, String target, int targetW) throws Exception {

		int saveFileWidth = 0;
		
		boolean returnValue = false;
		BufferedImage mi = ImageIO.read(saveFile);
		saveFileWidth = mi.getWidth();
		
		// 지정된 가로사이즈보다 크다면 가로에 의해서 비율검사(가로기준)
		if (saveFileWidth > targetW) {
			Image imgSource = new ImageIcon(saveFile.getAbsolutePath()).getImage();
			int oldW = imgSource.getWidth(null);
			int oldH = imgSource.getHeight(null);
			int newW = targetW;
			int newH = (targetW * oldH) / oldW;
			Image imgTarget = imgSource.getScaledInstance(newW, newH,
					Image.SCALE_SMOOTH);
			int pixels[] = new int[newW * newH];
			PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, newW, newH,
					pixels, 0, newW);
			pg.grabPixels();
			BufferedImage bi = new BufferedImage(newW, newH,
					BufferedImage.TYPE_INT_RGB);
			bi.setRGB(0, 0, newW, newH, pixels, 0, newW);
			
			File outputfile = new File(target);
			ImageIO.write(bi, "jpg", outputfile);
			
			/*
			FileOutputStream fos = new FileOutputStream(target);
			JPEGImageEncoder jpeg = JPEGCodec.createJPEGEncoder(fos);
			JPEGEncodeParam jep = jpeg.getDefaultJPEGEncodeParam(bi);
			jep.setQuality(1, false);
			jpeg.encode(bi, jep);
			fos.close();
			*/
			
			
			returnValue = true;
		}

		return returnValue;
	}
	
	public void deleteImage(String path, String filename){
		File file = new File(path, filename);
		file.delete();
	}
}
