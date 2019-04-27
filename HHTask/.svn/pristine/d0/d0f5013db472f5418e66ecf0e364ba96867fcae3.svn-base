package com.task.util.qrUtil;

//Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
//Jad home page: http://www.kpdus.com/jad.html
//Decompiler options: packimports(3) ansi lnc radix(10) lradix(10) 
//Source File Name:   com/idautomation/datamatrix/IDAImageCreator


import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class IDAImageCreator
{

 public IDAImageCreator()
 {
 }

 public Image getImage(int i, int j)
 {
     int k = i;
     if(j > i)
         k = j;
     im = new BufferedImage(k, k, 13);
     g = ((BufferedImage)im).createGraphics();
     return im;
 }

 public Graphics getGraphics()
 {
     return g;
 }

 private Image im;
 public Graphics g;
}


/*
	DECOMPILATION REPORT

	Decompiled from: D:\workpase\TestD\lib\IDADataMatrix.jar
	Total time: 89 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/