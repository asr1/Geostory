package com.geoteam.geostory;

//NOTE: CURRENTLY THIS HAS NO USES (OR SINGLETON DATA)


//This is a temporary singleton until we get the real on in place.
//Placeholder for code that will end up in the singleton eventually.
public class PrimarySingleton {

   private static PrimarySingleton instance = null;
   protected PrimarySingleton() {
      // Exists only to defeat instantiation.
   }
   
   
   /**
    * get the only instance of the singleton
    * @return
    */
   @SuppressWarnings("static-access")
public static synchronized PrimarySingleton getInstance() {
      if(instance == null) {
         instance = new PrimarySingleton();
         //NOTE: if the init ever needs arguments, the responsibly of init will be moved to the first caller!
         instance.init();
      }
      return instance;
   }
   
   


/**
    * init the singleton
    */
   public static void init(){

	   
	   
   }
   

   
   @Override
   protected Object clone() throws CloneNotSupportedException {
       throw new CloneNotSupportedException("Clone is not allowed.");
   } 
   





   
   //Location Manager Code 
   //NOTE: getSystemService() requires appcontext
//   LocationManager locMan = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//   LocationListener locList = new LocationListener_custom();
//   locMan.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0, 0, locList);
//   locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0 , 0, locList);

   
}

