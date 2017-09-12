import java.io.File;

public class Main 
{
	/**
	 * Fonction principale
	 * @param args (non utilise)
	 */
	public static void main(String[] args) 
	{

		String location = "./src/ed.ppm";
		File f = new File(location);
		if(!f.exists())
			location = "ed.ppm";

		/**
		 * Exercice 1
		 */

		// PixelMap pmc = new PixelMap(location);
		// PixelMap pmg = pmc.toGrayImage();
		// PixelMap pmb = pmc.toBWImage();

		// PixelMap pmt = pmc.toTransparentImage();
		// for(int i = 0; i < pmt.height; ++i)
		// 	for(int j = 0; j < pmt.width; ++j)
		// 		pmt.getPixel(i, j).setAlpha(127);

		// String wName = "Edsger Dijkstra (original)";
		// new DisplayImageWindow(wName, pmc, 50, 50);

		// wName = "Edsger Dijkstra (gris)";
		// new DisplayImageWindow(wName, pmg, 50+50, 50+50);

		// wName = "Edsger Dijkstra (B&W)";
		// new DisplayImageWindow(wName, pmb, 50+100, 50+100);

		// wName = "Edsger Dijkstra (Transparent)";
		// new DisplayImageWindow(wName, pmt, 200, 200);

		// TEST ==================================

		PixelMapPlus pmTest = new PixelMapPlus(location);
		// new DisplayImageWindow("Test Normal", pmTest, 50, 50);

		// pmTest.rotate(pmTest.height/2, pmTest.width/2, Math.toRadians(180));
		pmTest.resize(pmTest.height / 2, pmTest.width / 2);
		pmTest.convertToBWImage();
		new DisplayImageWindow("Test Resize", pmTest, 800, 50);
		
		PixelMapPlus pmTest2 = new PixelMapPlus(location);
		// pmTest2.inset(pmTest, 200, 200);
		pmTest2.crop(300, 200);
		new DisplayImageWindow("Test Crop", pmTest2, 50, 50);


		
		// =======================================

		/**
		 * Exercice 2
		*/
		
		// PixelMapPlus pmp = new PixelMapPlus(location);

		// PixelMapPlus hpmp = new PixelMapPlus( pmp );
		// hpmp.zoomIn(0, 0, 2);
		// hpmp.resize(hpmp.width/2, hpmp.height/2);
		// int[] tMin = new int[3];
		// tMin[0] = 110;
		// tMin[1] = 110;
		// tMin[2] = 110;
		// int[] tMax = new int[3];
		// tMax[0] = 220;
		// tMax[1] = 220;
		// tMax[2] = 220;
		// int[] newColors = new int[3];
		// newColors[0] = 255;
		// newColors[1] = 255;
		// newColors[2] = 255;
		// hpmp.replaceColor(new ColorPixel(tMin), new ColorPixel(tMax), new ColorPixel(newColors));

		
		// PixelMapPlus gpmp = new PixelMapPlus( pmp );
		// gpmp.zoomIn(0, gpmp.height, 2);
		// gpmp.resize(gpmp.width/2, gpmp.height/2);
		// gpmp.convertToGrayImage();


		// PixelMapPlus bwpmp = new PixelMapPlus( pmp );
		// bwpmp.zoomIn(pmp.getWidth(), 0, 2);
		// bwpmp.resize(bwpmp.width/2, bwpmp.height/2);
		// bwpmp.convertToBWImage();
		

		// PixelMapPlus npmp = new PixelMapPlus( pmp );
		// npmp.zoomIn(npmp.getWidth(), npmp.getHeight(), 2);
		// npmp.resize(npmp.width/2, npmp.height/2);
		// npmp.negate();

		// pmp.inset(hpmp, 0, 0);
		// pmp.inset(gpmp, pmp.getHeight()/2, 0);
		// pmp.inset(bwpmp, 0, pmp.getWidth()/2);
		// pmp.inset(npmp, pmp.getHeight()/2, pmp.getWidth()/2);
		// pmp.inverser();
		
		// wName = "Edsger Dijkstra";
		// new DisplayImageWindow(wName, pmp);
		// new DisplayImageWindow(wName, hpmp);
		
	}
}
