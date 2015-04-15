package services

import java.util.ArrayList
import java.net.URI

import scala.util.Random
import collection.JavaConversions._


object Utils {
  
  import java.util.Date
  import java.text.SimpleDateFormat
  def formatDateTime(dt: Date): String = {
    if(dt==null) { return "" }
    
    //val sdf = new SimpleDateFormat("d MMM yyyy h:mm:ssa")
    val sdf = new SimpleDateFormat("d/M/yyyy h:mm:ssa")
    sdf.format(dt)
  }
  
  def formatDate(dt: Date): String = {
    if(dt==null) { return "" }
    
    val sdf = new SimpleDateFormat("d/M/yyyy")
    sdf.format(dt)
  }
  
}