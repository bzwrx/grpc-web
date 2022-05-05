package io.grpcweb;

import java.util.Map;
import java.util.logging.Logger;
import java.lang.invoke.MethodHandles;
import java.util.HashMap;

public class ServiceToClassMappingHelper {
	
	private static final Logger LOG = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
	private static Map<String, Class<?>> nameToClassMap = new HashMap<>();  
  
    public static void put(String nameOfService, Class<?> rpcClass) {
      nameToClassMap.put(nameOfService, rpcClass);
    }

    /**
     * Get the actual class attributed to the name
     * 
     * @param nameOfService
				class name of service in string form
	 
     * @return
     */
    public Class<?> getClassObject(String nameOfService) {
      Class<?> rpcClass = nameToClassMap.get(nameOfService);
      if(rpcClass != null) {
        return rpcClass;
      }
      
      // original code, just in case there is a problem
      try {
        rpcClass = Class.forName(nameOfService + "Grpc");
      } catch (ClassNotFoundException e) {
        LOG.info("no such class " + nameOfService);
      }
	  
      return rpcClass;
    }
}

