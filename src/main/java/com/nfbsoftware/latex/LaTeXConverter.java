package com.nfbsoftware.latex;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

/**
 * The LaTeXConverter is used to convert a given LaTeX formula into various output objects.
 * 
 * @author brendan clemenzi
 * @email brendan@clemenzi.com
 */
public class LaTeXConverter
{
    public LaTeXConverter()
    {
    }
    
    /**
     * The convertToImage method is used to convert a LaTeX string to an PNG image file.
     * 
     * @param latexString
     * @return
     * @throws Exception
     */
    public File convertToImage(String latexString) throws Exception
    {
        File tempFile = File.createTempFile("latex", ".png");
        
        convertToImage(tempFile, latexString);
        
        return tempFile;
    }
    
    /**
     * The convertToImage method is used to convert a LaTeX string to an PNG image file.
     * 
     * @param imageFile
     * @param latexString
     * @throws Exception
     */
    public void convertToImage(File imageFile, String latexString) throws Exception
    {
        TeXFormula formula = new TeXFormula(latexString);
        
        TeXIcon icon = formula.new TeXIconBuilder().setStyle(TeXConstants.STYLE_DISPLAY).setSize(20).build();
        icon.setInsets(new Insets(5, 5, 5, 5));
        
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g2 = image.createGraphics();
        g2.setColor(Color.white);
        g2.fillRect(0,0,icon.getIconWidth(),icon.getIconHeight());
        
        JLabel jl = new JLabel();
        jl.setForeground(new Color(0, 0, 0));
        icon.paintIcon(jl, g2, 0, 0);
        
        ImageIO.write(image, "png", imageFile.getAbsoluteFile());
    }
}
