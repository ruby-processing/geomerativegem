/**
    Copyright 2004-2008 Ricard Marxer  <email@ricardmarxer.com>

    This file is part of Geomerative.

    Geomerative is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Geomerative is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    
    You should have received a copy of the GNU General Public License
    along with Geomerative.  If not, see <http://www.gnu.org/licenses/>.
*/

package geomerative ;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;

/**
 * @extended
 */  
public class RStyle{
  public PImage texture = null;
  
  public boolean fillDef = false;
  public boolean fill = false;
  public int fillColor = 0xff000000;

  public boolean fillAlphaDef = false;
  public int fillAlpha = 0xff000000;

  public boolean strokeDef = false;
  public boolean stroke = false;
  public int strokeColor = 0xff000000;

  public boolean strokeAlphaDef = false;
  public int strokeAlpha = 0xff000000;
  
  public boolean strokeWeightDef = false;
  public float strokeWeight = 1F;

  public boolean strokeCapDef = false;
  public int strokeCap = PConstants.PROJECT;

  public boolean strokeJoinDef = false;
  public int strokeJoin = PConstants.MITER;

  private boolean oldFill = false;
  private int oldFillColor = 0;
  
  private boolean oldStroke = false;
  private int oldStrokeColor = 0;
  private float oldStrokeWeight = 1F;
  private int oldStrokeCap = PConstants.PROJECT;
  private int oldStrokeJoin = PConstants.MITER;

  public RStyle(){}

  public RStyle(RStyle p){
    texture = p.texture;
    
    fillDef = p.fillDef;
    fill = p.fill;
    fillColor = p.fillColor;
    fillAlphaDef = p.fillAlphaDef;
    fillAlpha = p.fillAlpha;
    
    strokeDef = p.strokeDef;
    stroke = p.stroke;
    strokeColor = p.strokeColor;
    strokeAlphaDef = p.strokeAlphaDef;
    strokeAlpha = p.strokeAlpha;

    strokeWeightDef = p.strokeWeightDef;
    strokeWeight = p.strokeWeight;

    strokeCapDef = p.strokeCapDef;
    strokeCap = p.strokeCap;

    strokeJoinDef = p.strokeJoinDef;
    strokeJoin = p.strokeJoin;
  }

  protected void setStyle(String styleString){
    //RG.parent().println("Style parsing: " + styleString);
    String[] styleTokens = PApplet.splitTokens(styleString, ";");
    
      for (String styleToken : styleTokens) {
          String[] tokens = PApplet.splitTokens(styleToken, ":");
          tokens[0] = PApplet.trim(tokens[0]);
        switch (tokens[0]) {
            case "fill":
                setFill(tokens[1]);
                break;
            case "fill-opacity":
                setFillAlpha(tokens[1]);
                break;
            case "stroke":
                setStroke(tokens[1]);
                break;
            case "stroke-width":
                setStrokeWeight(tokens[1]);
                break;
            case "stroke-linecap":
                setStrokeCap(tokens[1]);
                break;
            case "stroke-linejoin":
                setStrokeJoin(tokens[1]);
                break;
            case "stroke-opacity":
                setStrokeAlpha(tokens[1]);
                break;
            case "opacity":
                setAlpha(tokens[1]);
                break;
            default:
                PApplet.println("Attribute '" + tokens[0] + "' not known.  Ignoring it.");
                break;
        }
      }
  }

  public void setFill(boolean _fill){
    fillDef = true;
    fill = _fill;
  }

  public void setFill(int _fillColor){
    //RG.parent().println("Setting fill by int: " + RG.parent().hex(_fillColor));
    setFill(true);
    fillColor = (fillColor & 0xff000000) | (_fillColor & 0x00ffffff);
  }

  public void setFill(String str){
    //RG.parent().println("id: " + id);
    //RG.parent().println("  set fill: " + str);
    if(str.equals("none")){
      setFill(false);

    }else{
      setFill(getColor(str));

    }
    //RG.parent().println("  fillColor after: " + RG.parent().hex(fillColor));
  }

  public void setStroke(boolean _stroke){
    strokeDef = true;
    stroke = _stroke;
  }

  public void setStroke(int _strokeColor){
    setStroke(true);
    strokeColor = (strokeColor & 0xff000000) | (_strokeColor & 0x00ffffff);
  }

  public void setStroke(String str){
    //RG.parent().println("  set stroke: " + str);
    if(str.equals("none")){
      setStroke(false);

    }else{
      setStroke(getColor(str));
      
    }
  }

  public void setStrokeWeight(float value){
    //RG.parent().println("  set strokeWeight by float: " + value);
    strokeWeightDef = true;
    strokeWeight = value;
  }

  public void setStrokeWeight(String str){
    //RG.parent().println("  set strokeWeight by String: " + str);
    if(str.endsWith("px")){
      setStrokeWeight(PApplet.parseFloat(str.substring(0, str.length() - 2)));
    }else{
      setStrokeWeight(PApplet.parseFloat(str));
    }

    
  }

  public void setStrokeCap(String str){
    //RG.parent().println("  set stroke-cap: " + str);
    strokeCapDef = true;

      switch (str) {
          case "butt":
              strokeCap = PConstants.PROJECT;
              break;
          case "round":
              strokeCap = PConstants.ROUND;
              break;
          case "square":
              strokeCap = PConstants.SQUARE;
              break;
      }
  }

  public void setStrokeJoin(String str){
    //RG.parent().println("  set stroke-cap: " + str);
    strokeJoinDef = true;

      switch (str) {
          case "miter":
              strokeJoin = PConstants.MITER;
              break;
          case "round":
              strokeJoin = PConstants.ROUND;
              break;
          case "bevel":
              strokeJoin = PConstants.BEVEL;
              break;
      }
  }

  public void setStrokeAlpha(int opacity){
    strokeAlphaDef = true;
    strokeAlpha = opacity;
  }

  public void setStrokeAlpha(String str){
    setStrokeAlpha((int)(PApplet.parseFloat(str) * 255F));
  }

  public void setFillAlpha(int opacity){
    fillAlphaDef = true;
    fillAlpha = opacity;
  }

  public void setFillAlpha(String str){
    //RG.parent().println("  set fillOpacity: " + str);
    setFillAlpha((int)(PApplet.parseFloat(str) * 255F));
    //RG.parent().println("  fillColor after: " + RG.parent().hex(fillColor));
  }  

  public void setAlpha(float opacity){
    //RG.parent().println("Setting float opacity: " + opacity);
    setAlpha((int)(opacity * 100F));
  }

  public void setAlpha(int opacity){
    /*
    RG.parent().println("setting opacity: " + RG.parent().hex(opacity));    
    
    RG.parent().println("  fillColor before: " + RG.parent().hex(fillColor));
    RG.parent().println("  strokeColor before: " + RG.parent().hex(fillColor));
    */

    setFillAlpha(opacity);
    setStrokeAlpha(opacity);

    //fillColor = ((opacity << 24) & 0xff000000) | (fillColor & 0x00ffffff);
    //strokeColor = ((opacity << 24) & 0xff000000) | (strokeColor & 0x00ffffff);

    /*    
    RG.parent().println("  fillColor now: " + RG.parent().hex(fillColor));
    RG.parent().println("  strokeColor now: " + RG.parent().hex(fillColor));
    */
  }

  public void setAlpha(String str){
    //RG.parent().println("Setting string opacity: " + str);
    setAlpha(PApplet.parseFloat(str));
  }

  protected void saveContext(PGraphics g){
    oldFill = g.fill;
    oldFillColor = g.fillColor;
    oldStroke = g.stroke;
    oldStrokeColor = g.strokeColor;
    oldStrokeWeight = g.strokeWeight;
    oldStrokeCap = g.strokeCap;
    oldStrokeJoin = g.strokeJoin;
  }

  protected void saveContext(PApplet p){
    oldFill = p.g.fill;
    oldFillColor = p.g.fillColor;
    oldStroke = p.g.stroke;
    oldStrokeColor = p.g.strokeColor;
    oldStrokeWeight = p.g.strokeWeight;    
    oldStrokeCap = p.g.strokeCap;
    oldStrokeJoin = p.g.strokeJoin;
  }

  protected void saveContext(){
    saveContext(RG.parent());
  }

  protected void restoreContext(PGraphics g){
    g.fill(oldFillColor);
    if(!oldFill){
      g.noFill();
    }

    g.stroke(oldStrokeColor);
    g.strokeWeight(oldStrokeWeight);

    try{
      g.strokeCap(oldStrokeCap);
      g.strokeJoin(oldStrokeJoin);
    }catch(RuntimeException e){}

    if(!oldStroke){
      g.noStroke();
    }
  }

  protected void restoreContext(PApplet p){
    p.fill(oldFillColor);
    if(!oldFill){
      p.noFill();
    }

    p.stroke(oldStrokeColor);
    p.strokeWeight(oldStrokeWeight);

    try{
      p.strokeCap(oldStrokeCap);
      p.strokeJoin(oldStrokeJoin);
    }catch(RuntimeException e){}

    if(!oldStroke){
      p.noStroke();
    }    
  }

  protected void restoreContext(){
    restoreContext(RG.parent());
  }

  protected void setContext(PGraphics g){
    if(fillAlphaDef){
      if(fillDef){
        fillColor = ((fillAlpha << 24) & 0xff000000) | (fillColor & 0x00ffffff);
      }else{
        if(g.fill){
          g.fill(((fillAlpha << 24) & 0xff000000) | (g.fillColor & 0x00ffffff));
        }
      }
    }

    if(fillDef){
      g.fill(fillColor);
      if(!fill){
        g.noFill();
      }
    }

    if(strokeWeightDef){      
      g.strokeWeight(strokeWeight);
    }

    try{
      if(strokeCapDef){      
        g.strokeCap(strokeCap);
      }
      
      if(strokeJoinDef){      
        g.strokeJoin(strokeJoin);
      }
    }catch(RuntimeException e){}

    if(strokeAlphaDef){
      if(strokeDef){
        strokeColor = ((strokeAlpha << 24) & 0xff000000) | (strokeColor & 0x00ffffff);
      }else{
        if(g.stroke){
          g.stroke(((strokeAlpha << 24) & 0xff000000) | (g.strokeColor & 0x00ffffff));
        }
      }
    }
    
    if(strokeDef){      
      g.stroke(strokeColor);
      if(!stroke){
        g.noStroke();
      }
    }
  }

  protected void setContext(PApplet p){
    
    if(fillAlphaDef){
      if(fillDef){
        fillColor = ((fillAlpha << 24) & 0xff000000) | (fillColor & 0x00ffffff);
      }else{
        if(p.g.fill){
          p.fill(((fillAlpha << 24) & 0xff000000) | (p.g.fillColor & 0x00ffffff));
        }
      }
    }
    
    if(fillDef){
      p.fill(fillColor);
      if(!fill){
        p.noFill();
      }
    }

    if(strokeWeightDef){      
      p.strokeWeight(strokeWeight);
    }

    try{
      if(strokeCapDef){      
        p.strokeCap(strokeCap);
      }
      
      if(strokeJoinDef){      
        p.strokeJoin(strokeJoin);
      }
    }catch(RuntimeException e){}

    if(strokeAlphaDef){
      if(strokeDef){
        strokeColor = ((strokeAlpha << 24) & 0xff000000) | (strokeColor & 0x00ffffff);
      }else{
        if(p.g.stroke){
          p.stroke(((strokeAlpha << 24) & 0xff000000) | (p.g.strokeColor & 0x00ffffff));
        }
      }
    }

    if(strokeDef){
      p.stroke(strokeColor);
      if(!stroke){
        p.noStroke();
      }
    }
  }

  protected void setContext(){
    setContext(RG.parent());
  }


  private int getColor(String colorString){
    colorString = PApplet.trim(colorString);
    
    if(colorString.startsWith("#")){
      return PApplet.unhex("FF"+colorString.substring(1));
    }else if(colorString.startsWith("rgb")){
      String[] rgb = PApplet.splitTokens(colorString, "rgb( , )");
      return (int)RG.parent().color(PApplet.parseInt(rgb[0]), PApplet.parseInt(rgb[1]), PApplet.parseInt(rgb[2]));
    }else{
        switch (colorString) {
            case "black":
                return 0;
            case "red":
                return RG.parent().color(255, 0, 0);
            case "green":
                return RG.parent().color(0, 255, 0);
            case "blue":
                return RG.parent().color(0, 0, 255);
            case "yellow":
                return RG.parent().color(0, 255, 255);
        }
    }
    return 0;
  }
}
