import org.omg.Messaging.SYNC_WITH_TRANSPORT;

// import java.awt.PageAttributes.ColorType;

// import sun.nio.ch.EPollSelectorProvider;

/**
 * Classe PixelMapPlus
 * Image de type noir et blanc, tons de gris ou couleurs
 * Peut lire et ecrire des fichiers PNM
 * Implemente les methodes de ImageOperations
 * @author : 
 * @date   : 
 */

public class PixelMapPlus extends PixelMap implements ImageOperations {
	/**
	 * Constructeur creant l'image a partir d'un fichier
	 * @param fileName : Nom du fichier image
	 */
	PixelMapPlus(String fileName) {
		super(fileName);
	}

	/**
	 * Constructeur copie
	 * @param type : type de l'image a creer (BW/Gray/Color/Transparent)
	 * @param image : source
	 */
	PixelMapPlus(PixelMap image) {
		super(image);
	}

	/**
	 * Constructeur copie (sert a changer de format)
	 * @param type : type de l'image a creer (BW/Gray/Color/Transparent)
	 * @param image : source
	 */
	PixelMapPlus(ImageType type, PixelMap image) {
		super(type, image);
	}

	/**
	 * Constructeur servant a allouer la memoire de l'image
	 * @param type : type d'image (BW/Gray/Color/Transparent)
	 * @param h : hauteur (height) de l'image 
	 * @param w : largeur (width) de l'image
	 */
	PixelMapPlus(ImageType type, int h, int w) {
		super(type, h, w);
	}

	/**
	 * Genere le negatif d'une image
	 */
	public void negate() {
		// compl�ter
		for (int row = 0; row < this.height; row++)
			for (int col = 0; col < this.width; col++)
				imageData[row][col] = imageData[row][col].Negative();

	}

	/**
	 * Convertit l'image vers une image en noir et blanc
	 */
	public void convertToBWImage() {
		// compl�ter
		for (int row = 0; row < this.height; row++)
			for (int col = 0; col < this.width; col++)
				imageData[row][col] = imageData[row][col].toBWPixel();
	}

	/**
	 * Convertit l'image vers un format de tons de gris
	 */
	public void convertToGrayImage() {
		// compl�ter
		for (int row = 0; row < this.height; row++)
			for (int col = 0; col < this.width; col++)
				imageData[row][col] = imageData[row][col].toGrayPixel();
	}

	/**
	 * Convertit l'image vers une image en couleurs
	 */
	public void convertToColorImage() {
		// compl�ter
		for (int row = 0; row < this.height; row++)
			for (int col = 0; col < this.width; col++)
				imageData[row][col] = imageData[row][col].toColorPixel();

	}

	public void convertToTransparentImage() {
		// compl�ter
		for (int row = 0; row < this.height; row++)
			for (int col = 0; col < this.width; col++)
				imageData[row][col] = imageData[row][col].toTransparentPixel();

	}

	/**
	 * Fait pivoter l'image de 10 degres autour du pixel (row,col)=(0, 0)
	 * dans le sens des aiguilles d'une montre (clockWise == true)
	 * ou dans le sens inverse des aiguilles d'une montre (clockWise == false).
	 * Les pixels vides sont blancs.
	 * @param clockWise : Direction de la rotation 
	 */
	public void rotate(int x, int y, double angleRadian) {
		AbstractPixel[][] rotatedMat = new AbstractPixel[height][width];
		int xInit, yInit;
		double cosThetha = Math.cos(angleRadian);
		double sinThetha = Math.sin(angleRadian);

		for (int row = 0; row < this.height; row++)
			for (int col = 0; col < this.width; col++) {
				xInit = (int) Math.round(cosThetha * row + sinThetha * col - cosThetha * x - sinThetha * y + x);
				yInit = (int) Math.round(-sinThetha * row + cosThetha * col + sinThetha * x - cosThetha * y + y);
				if (xInit < this.height && xInit > 0 && yInit < this.width && yInit > 0)
					rotatedMat[row][col] = imageData[xInit][yInit];
				else if (xInit == this.height && yInit < this.width && yInit > 0)
					rotatedMat[row][col] = imageData[xInit - 1][yInit];
				else if (yInit == this.width && xInit < this.height && xInit > 0)
					rotatedMat[row][col] = imageData[xInit][yInit - 1];
				else
					rotatedMat[row][col] = new BWPixel(true);
			}

		this.imageData = rotatedMat;
	}

	/**
	 * Modifie la longueur et la largeur de l'image 
	 * @param w : nouvelle largeur
	 * @param h : nouvelle hauteur
	 */
	public void resize(int h, int w) throws IllegalArgumentException {
		if (w < 0 || h < 0)
			throw new IllegalArgumentException();

		double ratioWidth = this.width * 1.0 / w;
		double ratioHeight = this.height * 1.0 / h;
		int scaledRow, scaledCol;
		AbstractPixel[][] scaledMat = new AbstractPixel[h][w];

		for (int row = 0; row < h; row++)
			for (int col = 0; col < w; col++) {
				scaledRow = (int) (row * ratioHeight);
				scaledCol = (int) (col * ratioWidth);
				scaledMat[row][col] = imageData[scaledRow][scaledCol];
			}

		this.height = h;
		this.width = w;
		imageData = scaledMat;
	}

	/**
	 * Insert pm dans l'image a la position row0 col0
	 */
	public void inset(PixelMap pm, int row0, int col0) {
		for (int row = row0; row < this.height; row++)
			for (int col = col0; col < this.width; col++)
				if ((row - row0) < pm.height && (col - col0) < pm.width && row >= 0 && col >= 0)
					imageData[row][col] = pm.getPixel(row - row0, col - col0);
	}

	/**
	 * Decoupe l'image 
	 */
	public void crop(int h, int w) {
		AbstractPixel[][] croppedMat = new AbstractPixel[h][w];

		for (int row = 0; row < h; row++)
			for (int col = 0; col < w; col++)
				if (row >= this.height || col >= this.width)
					croppedMat[row][col] = new BWPixel(true);
				else
					croppedMat[row][col] = imageData[row][col];

		this.height = h;
		this.width = w;
		imageData = croppedMat;
	}

	/**
	 * Effectue une translation de l'image 
	 */
	public void translate(int rowOffset, int colOffset) {
		AbstractPixel[][] translatedMat = new AbstractPixel[this.height][this.width];
		int rowDecal, colDecal;
		for (int row = 0; row < this.height; row++)
			for (int col = 0; col < this.width; col++) {
				rowDecal = row - rowOffset;
				colDecal = col - colOffset;
				if (rowDecal < this.height && colDecal < this.width && rowDecal >= 0 && colDecal >= 0)
					translatedMat[row][col] = imageData[rowDecal][colDecal];
				else
					translatedMat[row][col] = new BWPixel(true);
			}

		imageData = translatedMat;
	}

	/**
	 * Effectue un zoom autour du pixel (x,y) d'un facteur zoomFactor 
	 * @param x : colonne autour de laquelle le zoom sera effectue
	 * @param y : rangee autour de laquelle le zoom sera effectue  
	 * @param zoomFactor : facteur du zoom a effectuer. Doit etre superieur a 1
	 */
	public void zoomIn(int x, int y, double zoomFactor) throws IllegalArgumentException {
		if (zoomFactor < 1.0)
			throw new IllegalArgumentException();

		// Version Test Mehdi
		int originalHeight = height;
		int originalWidth = width;

		int rowOffset = (int) (-1 * (x - height / (2.0 * zoomFactor)));
		int colOffset = (int) (-1 * (y - width / (2.0 * zoomFactor)));
		translate(rowOffset, colOffset);

		int newHeight = (int) (height * 1.0 / zoomFactor);
		int newWidth = (int) (width * 1.0 / zoomFactor);
		crop(newHeight, newWidth);

		resize(originalHeight, originalWidth);

		// A COMPLETER (SUR LES BORDS)
	}

	/**
	 * Effectue un remplacement de tout les pixels dont la valeur est entre min et max 
	 * avec newPixel
	 * @param min : La valeur miniale d'un pixel
	 * @param max : La valeur maximale d'un pixel  
	 * @param newPixel : Le pixel qui remplacera l'ancienne couleur 
	 * (sa valeur est entre min et max)
	 */
	public void replaceColor(AbstractPixel min, AbstractPixel max, AbstractPixel newPixel) {
		// compl�ter

	}

	public void inverser() {
		// Version Test Medhi
		AbstractPixel[][] tempPixels = new AbstractPixel[this.height][this.width];

		for (int row = 0; row < this.height; row++)
			for (int col = 0; col < this.width; col++) {
				tempPixels[row][col] = imageData[this.height -1 - row][col];
			}

		imageData = tempPixels;
	}
}
